package com.whilabel_renewal.whilabel_backend.requestDto;


import lombok.Data;

@Data
public class WhiskyPostDetailRequestDTO {

    private Long whiskyId; //들어오면 값 끌어다가 쓰고, 없으면 그냥 공백인채로 whiskyPost 생성

    private String imageUrl;
    private Double rating;
    private String tasteNote;
    private Double bodyRate;
    private Double flavorRate;
    private Double peatRate;
}
