package com.whilabel_renewal.whilabel_backend.dto;


import com.whilabel_renewal.whilabel_backend.domain.Whisky;
import lombok.Data;

@Data
public class WhiskyScanDTO {
    private Long whiskyId;
    private String whiskyName;
    private String distilleryAddress;
    private String distilleryCountry;
    private Double distilleryRating;





    public WhiskyScanDTO(Whisky whisky) {
        this.whiskyId = whisky.getId();
        this.whiskyName = whisky.getName();
        if (whisky.getDistillery().getAddress() != null) {
            distilleryAddress = whisky.getDistillery().getAddress();
        }
        else {
            distilleryAddress = whisky.getDistillery().getWbDistillery().getAddress();
        }

        if (whisky.getDistillery().getCountry() != null) {
            distilleryCountry = whisky.getDistillery().getCountry();
        }
        else {
            distilleryCountry = whisky.getDistillery().getWbDistillery().getCountry();
        }

        distilleryRating = whisky.getDistillery().getWbDistillery().getRating();
    }



}
