package com.x.api.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GetAccessToken {

    public String fetchAccessToken(String code) {
        String url = "https://api.twitter.com/2/oauth2/token";
        String clientId = "Z0V3bHZVbkdPSVEyMEM2T1RxeTQ6MTpjaQ";
        String authorizationHeader = "Basic WjBWM2JIWlZia2RQU1ZFeU1FTTJUMVJ4ZVRRNk1UcGphUTphM19oTF9XOVpmRTJ2bEMzOGFlLS1qWVdNdVVVWXhhZGpEclJ4N1pURGtNSnpTaVVFRQ==";
        String redirectUri = "http://localhost:8080/getData";
        String codeVerifier = "challenge";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authorizationHeader);

        String requestBody = "code=" + code +
                "&grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&code_verifier=" + codeVerifier;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String accessToken = extractAccessToken(response.getBody());
            return accessToken;
        } else {
            // Handle error response
            System.out.println("Failed to fetch access token. Response status: " + response.getStatusCode());
            return null;
        }
    }

    private String extractAccessToken(String responseBody) {
        // Find the index of "access_token" field in the JSON response
        int accessTokenIndex = responseBody.indexOf("\"access_token\"");
        if (accessTokenIndex == -1) {
            System.out.println("Access token not found in response body.");
            return null;
        }

        // Find the start index of the access token value
        int tokenValueStartIndex = responseBody.indexOf('"', accessTokenIndex + 14); // Adding 14 to skip "access_token":" part
        if (tokenValueStartIndex == -1) {
            System.out.println("Access token value not found in response body.");
            return null;
        }

        // Find the end index of the access token value
        int tokenValueEndIndex = responseBody.indexOf('"', tokenValueStartIndex + 1);
        if (tokenValueEndIndex == -1) {
            System.out.println("End of access token value not found in response body.");
            return null;
        }

        // Extract the access token value substring
        String accessToken = responseBody.substring(tokenValueStartIndex + 1, tokenValueEndIndex);
        return accessToken;
    }
}
