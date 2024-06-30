package com.whilabel_renewal.whilabel_backend.requestDto;

import lombok.Data;

@Data
public class WhiskyPostDetailEditDTO {

    private Long id;
    private Double rating;
    private String tasteNote;
    private Double bodyRate;
    private Double flavorRate;
    private Double peatRate;
}
