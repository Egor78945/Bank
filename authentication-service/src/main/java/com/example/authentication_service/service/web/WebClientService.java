package com.example.authentication_service.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final RestTemplate restTemplate;

    public <T, R> ResponseEntity<T> postForEntity(HttpEntity<R> request, String url, Class<T> responseFormat){
        return restTemplate.postForEntity(url, request, responseFormat);
    }
}
