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
public class KakaoValidateService {

    public Long getUserId(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                KakaoUserInfo.class
        );

        return response.getBody().getId();
    }

    @Data
    static class KakaoUserInfo {
        private Long id;
        private String connected_at;
        private Properties properties;
        private KakaoAccount kakao_account;
        // Getters and setters
    }

    @Data
    static class Properties {
        private String nickname;
        // Getters and setters
    }

    @Data
    static class KakaoAccount {
        private String email;
        // Getters and setters
    }

}

