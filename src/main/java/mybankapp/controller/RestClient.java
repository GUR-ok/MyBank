package mybankapp.controller;

import mybankapp.domain.dto.PersonDTO;
import mybankapp.domain.model.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

public class RestClient {
    private static final String LOGIN_USER = "http://localhost:8080/bank/login";
    private static final String GET_ALL_PERSONS_API = "http://localhost:8080/admin/bank";
    private static final String GET_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";
    private static final String CREATE_PERSON_API = "http://localhost:8080/admin/bank";
    private static final String DELETE_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";
    private static final String UPDATE_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        String token = loginPerson();
        UUID createdUuid = callCreatePerson(token);
        callGetAllPersonsAPI(token);
        callGetGetPersonByIDAPI(token);
        callUpdatePerson(token, createdUuid);
        callDeletePerson(token, createdUuid);
    }


    public static String loginPerson() {
        HttpHeaders headers = new HttpHeaders();

        Map<String, String> params = new HashMap<>();
        params.put("username", "Admin");
        params.put("password", "test");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        String result = restTemplate.postForObject(LOGIN_USER, entity, String.class);
        System.out.println(result + "\n");

        //Parsing accessToken
        JSONObject obj = new JSONObject(result);
        String accessToken = obj.getString("AccessToken");
        return accessToken;
    }

    public static void callGetAllPersonsAPI(String token) {

        String bearerToken = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parametrs", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_PERSONS_API, HttpMethod.GET, entity, String.class);
        System.out.println(result + "\n");
    }

    public static void callGetGetPersonByIDAPI(String token) {

        String bearerToken = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        Map<String, UUID> params = new HashMap<>();
        params.put("uuid", UUID.fromString("5b5ea1a4-b863-416b-a641-a879deb7c770"));

        HttpEntity<Map<String, UUID>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<PersonDTO> result  = restTemplate.exchange(GET_PERSON_API,  HttpMethod.GET, entity, PersonDTO.class, params);
        System.out.println(result.getBody().getUuid() + " " + result.getBody().getName() + "\n");
    }

    public static UUID callCreatePerson(String token) {
        String bearerToken = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        Person person = new Person();
        person.setName("RestClient");
        person.setPassword("pass");

        HttpEntity<Person> entity = new HttpEntity<>(person, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(CREATE_PERSON_API, entity, String.class);
        System.out.println("Created person UUID: " + result.getBody() + "\n");
        return UUID.fromString(result.getBody());
    }

    public static void callDeletePerson(String token, UUID uuid) {
        String bearerToken = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        Map<String, UUID> params = new HashMap<>();
        params.put("uuid", uuid);

        HttpEntity<Map<String, UUID>> entity = new HttpEntity<>(params, headers);

        restTemplate.exchange(DELETE_PERSON_API,  HttpMethod.DELETE, entity, String.class, params);

    }


    public static void callUpdatePerson(String token, UUID uuid) {
        String bearerToken = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        Person person = new Person();
        person.setName("RestClientUpdated");
        person.setPassword("pass2");
       // person.setUuid(uuid);

        Map<String, UUID> params = new HashMap<>();
        params.put("uuid", uuid);

        HttpEntity<Person> entity = new HttpEntity<>(person, headers);


        ResponseEntity<String> result = restTemplate.exchange(UPDATE_PERSON_API, HttpMethod.PUT, entity, String.class, params);
        System.out.println("Updated person UUID: " + result.getBody() + "\n");
    }

}
