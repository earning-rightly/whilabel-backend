package com.whilabel_renewal.whilabel_backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.whilabel_renewal.whilabel_backend.domain.Announcement;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnnouncementListDTO {
    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


    public AnnouncementListDTO(Announcement announcement) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.createdAt = announcement.getCreatedDateTime();
    }
}
