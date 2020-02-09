package dev.bug.bankapp.integrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    public <T> List<T> getListApi(final String url) {
        final ResponseEntity<List<T>> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>() {
                });
        return response.getBody();
    }

    public <T> T getEntityApi(final String url) {
        final ResponseEntity<T> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<T>() {
                });
        return response.getBody();
    }
}
