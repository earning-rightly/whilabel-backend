package com.whilabel_renewal.whilabel_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Distillery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wb_distillery_id")
    private WbDistillery wbDistillery;

    private String name;
    private String normalizedName;

    @Column(name = "official_url")
    private String officialUrl;

    private String country;
    private String address;

    @Column(name = "whisky_count")
    private int whiskyCount;

    private boolean closed;

    @Column(name = "founded_ymd")
    private String foundedYmd;

    private String owner;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date_time")
    private Date createDateTime;

    private String creator;

    @Temporal(TemporalType.DATE)
    @Column(name = "modify_date_time")
    private Date modifyDateTime;

    private String modifier;

}
