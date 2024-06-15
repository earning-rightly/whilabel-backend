package com.whilabel_renewal.whilabel_backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.whilabel_renewal.whilabel_backend.domain.Whisky;
import com.whilabel_renewal.whilabel_backend.domain.WhiskyPost;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WhiskyPostListDTO {

    private Long id;

    private Long whiskyId;
    private String name;
    private String address;
    private String tasteNote;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifyDateTime;

    private Double rating;

    private String imageUrl;


    public  WhiskyPostListDTO(WhiskyPost whiskyPost){
        this.id = whiskyPost.getId();
        Whisky whisky = whiskyPost.getWhisky();
        this.whiskyId = whisky.getId();
        this.name = whisky.getName();
        this.address = whisky.getDistillery().getAddress();
        this.tasteNote = whiskyPost.getTastNote();
        this.createDateTime = whiskyPost.getCreateDateTime();
        this.modifyDateTime = whiskyPost.getModifyDateTime();
        this.rating = whiskyPost.getRating();
        this.imageUrl = whiskyPost.getImageUrl();
    }
}
