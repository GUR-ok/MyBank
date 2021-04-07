package mybankapp.controller;

import mybankapp.domain.dto.AuthenticationRequestDTO;
import mybankapp.domain.dto.PersonDTO;
import mybankapp.domain.model.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
        //Login User
        HttpHeaders headers = new HttpHeaders();
        AuthenticationRequestDTO dto = new AuthenticationRequestDTO();
        dto.setUsername("Admin");
        dto.setPassword("test");
        HttpEntity<Object> entity = new HttpEntity<>(dto, headers);
        String s = postEntityMethod(LOGIN_USER, entity, String.class).getBody();
            //Parsing accessToken
        JSONObject obj = new JSONObject(s);
        String token = obj.getString("AccessToken");
        System.out.println(token+"\n");

        //Create Person
        String bearerToken = "Bearer " + token;
        HttpHeaders headersCreate = new HttpHeaders();
        headersCreate.set("Authorization", bearerToken);
        Person person = new Person();
        person.setName("RestClientNew");
        person.setPassword("passNew");
        HttpEntity<Object> entityCreate = new HttpEntity<>(person, headersCreate);
        String createdUuid = postEntityMethod(CREATE_PERSON_API, entityCreate, String.class).getBody();
        System.out.println(createdUuid+"\n");

        //Get all persons
        HttpHeaders headersGetAll = new HttpHeaders();
        headersGetAll.set("Authorization", bearerToken);
        headersGetAll.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entityGetAll = new HttpEntity<>("parametrs", headersGetAll);
        List<PersonDTO> list = Arrays.asList(exchangeGetMethod(GET_ALL_PERSONS_API, entityGetAll, PersonDTO[].class).getBody());
        System.out.println(list+"\n");

        //Get personById
        HttpHeaders headersById = new HttpHeaders();
        headersById.set("Authorization", bearerToken);
        UUID uuid = UUID.fromString("5b5ea1a4-b863-416b-a641-a879deb7c770");
        HttpEntity<Object> entityById = new HttpEntity<>("parametrs", headersById);
        PersonDTO dtoById = exchangeGetMethodWithParams(GET_PERSON_API, entityById, PersonDTO.class, uuid).getBody();
        System.out.println(dtoById+"\n");

        //UpdatePerson
        HttpHeaders headersupd = new HttpHeaders();
        headersupd.set("Authorization", bearerToken);

        Person personUpd = new Person();
        personUpd.setName("RestClientUpdated");
        personUpd.setPassword("pass2");
        HttpEntity<Object> entityUpd = new HttpEntity<>(personUpd, headersupd);
        String upd = exchangeUpdateMethodWithParams(UPDATE_PERSON_API, entityUpd, String.class,createdUuid).getBody();
        System.out.println(upd+"\n");

        //Delete Person by uuid
        HttpHeaders headersDelete = new HttpHeaders();
        headersDelete.set("Authorization", bearerToken);
        HttpEntity<Object> entityDel = new HttpEntity<>("parametrs", headersDelete);
        exchangeDeleteMethodWithParams(DELETE_PERSON_API, entityDel, String.class, createdUuid);

    }

    public static <T> ResponseEntity<T> postEntityMethod(String url, HttpEntity<Object> entity, Class<T> clazz) {
        ResponseEntity<T> result = restTemplate.postForEntity(url, entity, clazz);
        return result;
    }

    public static <T> ResponseEntity<T> getEntityMethod(String url, Class<T> clazz) {
        ResponseEntity<T> result = restTemplate.getForEntity(url, clazz);
        return result;
    }

    public static <T> ResponseEntity<T> exchangeGetMethod(String url, HttpEntity<Object> entity, Class<T> clazz) {
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.GET, entity, clazz);
        return result;
    }

    public static <T> ResponseEntity<T> exchangeGetMethodWithParams(String url, HttpEntity<Object> entity, Class<T> clazz, Object params) {
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.GET, entity, clazz, params);
        return result;
    }

    public static <T> ResponseEntity<T> exchangeDeleteMethodWithParams(String url, HttpEntity<Object> entity, Class<T> clazz, Object params) {
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, clazz, params);
        return result;
    }

    public static <T> ResponseEntity<T> exchangeUpdateMethodWithParams(String url, HttpEntity<Object> entity, Class<T> clazz, Object params) {
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.PUT, entity, clazz, params);
        return result;
    }

    public static <T> ResponseEntity<T> exchangePostMethodWithParams(String url, HttpEntity<Object> entity, Class<T> clazz, Object params) {
        ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.POST, entity, clazz, params);
        return result;
    }

}
