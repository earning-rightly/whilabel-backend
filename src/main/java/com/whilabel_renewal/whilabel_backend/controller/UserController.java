package com.whilabel_renewal.whilabel_backend.controller;

import com.whilabel_renewal.whilabel_backend.domain.User;
import com.whilabel_renewal.whilabel_backend.repository.UserRepository;
import com.whilabel_renewal.whilabel_backend.service.GoogleValidateService;
import com.whilabel_renewal.whilabel_backend.service.KakaoValidateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/")
public class UserController {

    @Autowired
    private KakaoValidateService kakaoValidateService;

    @Autowired
    private GoogleValidateService googleValidateService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("login")
    public ResponseEntity<Map<String,String>> login(HttpServletRequest request) {
        String sns_token = request.getParameter("sns_token");
        String sns_type = request.getParameter("sns_type");

        Map<String, String> result = new HashMap<>();

        if (sns_token == null) {
            result.put("message","sns_token not found");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        String sns_id;
        switch (sns_type) {
            case "kakao":
                sns_id = String.valueOf(kakaoValidateService.getUserId(sns_token));
                break;
            case "google":
                sns_id = googleValidateService.getSub(sns_token);
                break;
            default:
                result.put("message","sns_token not found");
                return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findBySnsId(sns_id);

        if (user == null) {
            result.put("message","need register");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }

        result.put("token","someToken");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
