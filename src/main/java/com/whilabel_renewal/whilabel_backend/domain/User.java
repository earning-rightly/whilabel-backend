package com.whilabel_renewal.whilabel_backend.domain;


import com.whilabel_renewal.whilabel_backend.enums.Gender;
import com.whilabel_renewal.whilabel_backend.enums.SnsLoginType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//id INT PRIMARY KEY,
//sns_login_type VARCHAR(255) NOT NULL,
//nickname VARCHAR(255) NOT NULL,
//is_push_allowed BOOLEAN NOT NULL,
//is_marketing_push_allowed BOOLEAN NOT NULL,
//push_token VARCHAR(255) NOT NULL,
//sns_token VARCHAR(255) NOT NULL,
//auth_token VARCHAR(255) NOT NULL,
//is_resigned BOOLEAN NOT NULL,
//gender VARCHAR(50),
//birth_day VARCHAR(50)

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sns_login_type")
    private SnsLoginType snsLoginType = SnsLoginType.GOOGLE;

    private String nickname;

    @Column(name = "is_push_allowed")
    private boolean isPushAllowed = false;

    @Column(name = "is_marketing_push_allowed")
    private boolean isMarketingPushAllowed = false;

    @Column(name = "push_token")
    private String pushToken;

    @Column(name = "sns_token")
    private String snsToken = "";

    @Column(name = "auth_token")
    private String authToken = "";

    @Column(name = "is_resigned")
    private boolean isResigned = false;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birth_day;
}

