package com.whilabel_renewal.whilabel_backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import java.util.Date;


@Getter
@Setter
@Entity
public class Whisky {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wb_whisky_id")
    private WbWhisky wbWhisky;

    private String barcode;

    private String name = "unkown";
    private String normalizedName = "unknown";
    private Double strength;

    @Column(name = "image_url")
    private String imageUrl;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "price_unit_id")
    private PriceUnit priceUnit;

    @ManyToOne
    @JoinColumn(name = "distillery_id")
    private Distillery distillery;

    @Column(name = "bottler_id")
    private Long bottlerId;

    @Column(name = "whiskey_category")
    private String whiskeyCategory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time")
    private Date createDateTime = new Date();

    private String creator = "unknown";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date_time")
    private Date modifyDateTime;

    private String modifier;

}
