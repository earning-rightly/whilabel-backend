package com.whilabel_renewal.whilabel_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whilabel_renewal.whilabel_backend.domain.User;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Optional;

public class UserDTO {
    @JsonFormat
    private Long id;
    @JsonFormat
    private String snsLoginType;
    @JsonFormat
    private String nickname;
    @JsonFormat
    private boolean isPushAllowed;
    @JsonFormat
    private boolean isMarketingAllowed;
    @JsonFormat
    private String pushToken;
    @JsonFormat
    private boolean isResigned;
    @JsonFormat
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDay;
    @JsonFormat
    private String message;


    public UserDTO(User user) {
        id = user.getId();
        switch (user.getSnsLoginType()) {
            case GOOGLE -> snsLoginType = "google";
            case KAKAO -> snsLoginType = "kakao";
            case APPLE -> snsLoginType = "apple";
            case INSTAGRAM -> snsLoginType = "instagram";
        }
        nickname = user.getNickname();
        isPushAllowed = user.isPushAllowed();
        isMarketingAllowed = user.isMarketingPushAllowed();
        pushToken = user.getPushToken();
        isResigned = user.isResigned();
        switch (user.getGender()) {
            case MALE -> gender = "male";
            case FEMALE -> gender = "female";
        }
        birthDay = user.getBirth_day();

    }

    public UserDTO(String message) {
        this.message = message;
    }

}
