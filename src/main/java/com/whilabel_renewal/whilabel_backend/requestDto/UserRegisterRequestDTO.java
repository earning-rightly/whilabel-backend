package com.whilabel_renewal.whilabel_backend.requestDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequestDTO {

    private String nickname;
    private String gender;
    private String birthDay;
    private String snsToken;
    private String snsType;


}
