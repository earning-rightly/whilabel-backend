package com.whilabel_renewal.whilabel_backend.dto;

import com.whilabel_renewal.whilabel_backend.domain.User;
import com.whilabel_renewal.whilabel_backend.enums.Gender;
import com.whilabel_renewal.whilabel_backend.enums.SnsLoginType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class UserDTO {
    private Long id;
    private String snsLoginType;
    private String nickname;
    private boolean isPushAllowed;
    private boolean isMarketingAllowed;
    private String pushToken;
    private boolean isResigned;
    private String gender;
    private Date birthDay;


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

}
