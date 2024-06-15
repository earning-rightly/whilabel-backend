package com.whilabel_renewal.whilabel_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whilabel_renewal.whilabel_backend.domain.TasteFeature;
import com.whilabel_renewal.whilabel_backend.domain.WhiskyPost;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WhiskyPostDetailDTO {

    private Long userId;
    private Long id;
    private String distilleryImage;
    private String whiskyImage;
    private String whiskyName;
    private String distilleryAddress;
    private String distillerCountry;
    private Double distilleryRating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifyDateTime;

    private Double starRating;
    private String tasteNote;
    private TasteFeature tasteFeature;


    public WhiskyPostDetailDTO(WhiskyPost wp) {
        this.userId = wp.getUser().getId();
        this.id = wp.getId();
        if (wp.getWhisky().getDistillery().getImageUrl() != null) {
            distilleryImage = wp.getWhisky().getDistillery().getImageUrl();
        }
        else {
            distilleryImage = wp.getWhisky().getDistillery().getWbDistillery().getImageUrl();
        }

        whiskyImage = wp.getImageUrl();
        whiskyName = wp.getWhisky().getName();
        
        if (wp.getWhisky().getDistillery().getAddress() != null) {
            distilleryAddress = wp.getWhisky().getDistillery().getAddress();
        }
        else {
            distilleryAddress = wp.getWhisky().getDistillery().getWbDistillery().getAddress();
        }

        if (wp.getWhisky().getDistillery().getCountry() != null) {
            distillerCountry = wp.getWhisky().getDistillery().getCountry();
        }
        else {
            distillerCountry = wp.getWhisky().getDistillery().getWbDistillery().getCountry();
        }

        distilleryRating = wp.getWhisky().getDistillery().getWbDistillery().getRating();

        createDateTime = wp.getCreateDateTime();
        modifyDateTime = wp.getModifyDateTime();
        starRating = wp.getRating();
        tasteNote = wp.getTastNote();
        tasteFeature = wp.getTasteFeature();
    }
}
