package com.whilabel_renewal.whilabel_backend.domain;


import com.whilabel_renewal.whilabel_backend.enums.Gender;
import com.whilabel_renewal.whilabel_backend.enums.SnsLoginType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @Column(name = "sns_id")
    private String snsId;

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

