package com.whilabel_renewal.whilabel_backend.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class WhiskyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne
    @JoinColumn(name = "whisky_id")
    private Whisky whisky;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time")
    private Date createDateTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date_time")
    private Date modifyDateTime;

    private Double rating = 0.0;

    @OneToOne
    @JoinColumn(name = "taste_feature_id")
    private TasteFeature tasteFeature = new TasteFeature();

    @Column(name = "taste_note")
    private String tastNote;

    @Column(name = "image_url")
    private String imageUrl;



}
