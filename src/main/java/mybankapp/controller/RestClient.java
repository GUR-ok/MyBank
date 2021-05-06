package mybankapp.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private static final String LOGIN_USER = "http://localhost:8080/bank/login";
    private static final String GET_ALL_PERSONS_API = "http://localhost:8080/admin/bank";
    private static final String GET_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";
    private static final String CREATE_PERSON_API = "http://localhost:8080/admin/bank";
    private static final String DELETE_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";
    private static final String UPDATE_PERSON_API = "http://localhost:8080/admin/bank/{uuid}";

    static RestTemplate restTemplate = new RestTemplate();

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
