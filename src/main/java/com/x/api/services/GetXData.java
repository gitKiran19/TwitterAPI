package com.x.api.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetXData {

    public String getDataWithAccessToken(String accessToken) {
        String url = "https://api.twitter.com/2/users/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseData = responseEntity.getBody();
            // Process the response data as needed
            System.out.println("Response from Twitter API: " + responseData);
            return responseData.toString();
        } else {
            System.out.println("Failed to get data from Twitter API. Response code: " + responseEntity.getStatusCode());
            return null;
        }
    }
}


