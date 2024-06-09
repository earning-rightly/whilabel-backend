package com.whilabel_renewal.whilabel_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Whisky {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wb_whisky_id")
    private WbWhisky wbWhisky;

    private String barcode;
    private String name;
    private String normalizedName;
    private Double strength;

    @Column(name = "image_url")
    private String imageUrl;

    private Double price;

    //priceUnitId
    @OneToOne
    @JoinColumn(name = "price_unit_id")
    private PriceUnit priceUnit;

    @OneToOne
    @JoinColumn(name = "distillery_id")
    private Distillery distillery;

    @Column(name = "bottler_id")
    private Long bottlerId;

    @Column(name = "whiskey_category")
    private String whiskeyCategory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time")
    private Date createDateTime;

    private String creator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date_time")
    private Date modifyDateTime;

    private String modifier;

}
