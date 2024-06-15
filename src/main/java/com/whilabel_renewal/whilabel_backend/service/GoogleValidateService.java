package com.whilabel_renewal.whilabel_backend.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class GoogleValidateService {

    public String getSub(String accessToken) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken,
                HttpMethod.GET,
                entity,
                GoogleUserInfo.class
        );

        return response.getBody().getSub();
    }

    @Data
    static class GoogleUserInfo {
        private String azp;
        private String aud;
        private String sub;
        private String scope;
        private String exp;
        private String expires_in;
        private String email;
        private String email_verified;
        private String access_type;
    }

}
