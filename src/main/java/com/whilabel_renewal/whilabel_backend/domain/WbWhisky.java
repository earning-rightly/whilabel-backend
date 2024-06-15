package com.whilabel_renewal.whilabel_backend.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class WbWhisky {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String barcode;
    private String name = "unknown";
    private Double strength;

    @Column(name = "image_url")
    private String imageUrl;
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "batch_date_time")
    private Date batchDateTime;

    @Column(name = "batch_id")
    private Long batchId;

    private String size;
    //label image i guess?
    private String label;

    private String market;
    private int voteCount;

    private double rating;

    @Column(name = "whisky_category")
    private String whiskyCategory;


    @JsonIgnore
    @OneToMany(mappedBy = "wbWhisky",fetch = FetchType.EAGER)
    private List<TasteVote> tasteVoteList = new ArrayList<>();
}
