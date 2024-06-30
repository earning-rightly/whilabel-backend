package com.whilabel_renewal.whilabel_backend.domain;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.ToOne;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class WhiskyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "whisky_post_seq")
    @SequenceGenerator(name = "whisky_post_seq", sequenceName = "whisky_post_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "whisky_id")
    private Whisky whisky;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date_time")
    @Nullable
    private LocalDateTime modifyDateTime;

    private Double rating = 0.0;

    @OneToOne
    @JoinColumn(name = "taste_feature_id")
    private TasteFeature tasteFeature = new TasteFeature();

    @Column(name = "taste_note")
    private String tastNote;

    @Column(name = "image_url")
    private String imageUrl;



}
