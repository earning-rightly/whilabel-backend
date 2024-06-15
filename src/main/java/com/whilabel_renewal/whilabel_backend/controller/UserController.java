package com.whilabel_renewal.whilabel_backend.controller;

import com.whilabel_renewal.whilabel_backend.domain.User;
import com.whilabel_renewal.whilabel_backend.dto.UserDTO;
import com.whilabel_renewal.whilabel_backend.enums.Gender;
import com.whilabel_renewal.whilabel_backend.enums.SnsLoginType;
import com.whilabel_renewal.whilabel_backend.jwt.JwtTokenManager;
import com.whilabel_renewal.whilabel_backend.repository.UserRepository;
import com.whilabel_renewal.whilabel_backend.requestDto.UserRegisterRequestDTO;
import com.whilabel_renewal.whilabel_backend.service.applevalidate.AppleValidateService;
import com.whilabel_renewal.whilabel_backend.service.GoogleValidateService;
import com.whilabel_renewal.whilabel_backend.service.KakaoValidateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


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
    private AppleValidateService appleValidateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenManager tokenManager;

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(HttpServletRequest request) {
        String sns_token = request.getParameter("snsToken");
        String sns_type = request.getParameter("snsType");

        Map<String, String> result = new HashMap<>();

        if (sns_token == null) {
            result.put("message", "sns_token not found");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        String sns_id = this.getSnsIdbySnsToken(sns_token, sns_type);
        if (sns_id.isEmpty()) {
            result.put("message", "sns_token not valid");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findBySnsId(sns_id);

        if (user == null) {
            result.put("message", "need register");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }

        JwtTokenManager tokenManager = new JwtTokenManager();
        String token = tokenManager.generateToken(user.getId());

        result.put("token", token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRegisterRequestDTO requestDTO) {
        Map<String, String> result = new HashMap<>();
        User user = new User();
        user.setNickname(requestDTO.getNickname());
        switch (requestDTO.getGender()) {
            case "male" -> user.setGender(Gender.MALE);
            case "female" -> user.setGender(Gender.FEMALE);
        }
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(requestDTO.getBirthDay());
        LocalDate ld = LocalDate.parse(requestDTO.getBirthDay(), DATEFORMATTER);

        user.setBirth_day(ld);

        String sns_id = this.getSnsIdbySnsToken(requestDTO.getSnsToken(), requestDTO.getSnsType());
        if (sns_id.isEmpty()) {
            result.put("message", "sns_token not valid");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        user.setSnsId(requestDTO.getSnsToken());

        switch (requestDTO.getSnsType()) {
            case "kakao" -> user.setSnsLoginType(SnsLoginType.KAKAO);
            case "apple" -> user.setSnsLoginType(SnsLoginType.APPLE);
            case "google" -> user.setSnsLoginType(SnsLoginType.GOOGLE);
        }

        userRepository.save(user);

        System.out.println( "user.id" + user.getId());

        String token = tokenManager.generateToken(user.getId());

        result.put("token", token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("me")
    public ResponseEntity<UserDTO> me(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        JwtTokenManager jwtTokenManager = new JwtTokenManager();
        String token = jwtTokenManager.getTokenFromHeader(header);
        Long userId = jwtTokenManager.extractUserId(token);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>(new UserDTO("no user found"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
    }

    private String getSnsIdbySnsToken(String sns_token,String sns_type) {
        String sns_id;
        switch (sns_type) {
            case "kakao":
                sns_id = String.valueOf(kakaoValidateService.getUserId(sns_token));
                break;
            case "google":
                sns_id = googleValidateService.getSub(sns_token);
                break;
            case "apple":
                sns_id = appleValidateService.getSub(sns_token);
                break;
            default:
                return "";
        }
        return sns_id;
    }
}
