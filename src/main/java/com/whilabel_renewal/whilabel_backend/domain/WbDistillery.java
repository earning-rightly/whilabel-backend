package com.whilabel_renewal.whilabel_backend.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class WbDistillery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name = "unknown";
    private String link;
    private String country;
    private String address;

    @Column(name = "image_url")
    private String imageUrl;

    @Temporal(TemporalType.DATE)
    @Column(name = "batch_date_time")
    private Date backDateTime = new Date();

    @Column(name = "batch_id")
    private Long batchId = 0L;

    @Column(name = "whisky_count")
    private int whiskyCount;

    @Column(name = "vote_count")
    private int voteCount;

    private Double rating;

    @Column(name = "wb_url")
    private String wbUrl;

    private boolean closed;

    @Column(name = "founded_ymd")
    private String foundedYmd;

    private String owner;

    @Column(name = "spirits_still")
    private String spiritsStill;

    private String status;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "wb_ranking")
    private int wbRanking;

    @Column(name = "wash_stills")
    private int washStills;
}
