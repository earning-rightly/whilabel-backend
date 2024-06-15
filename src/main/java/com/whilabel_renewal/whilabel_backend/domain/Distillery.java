package com.whilabel_renewal.whilabel_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String name = "unknown";

    @Column(name = "normalized_name")
    private String normalizedName = "unknown";

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime = LocalDateTime.now();

    private String creator = "unknown";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date_time")
    private LocalDateTime modifyDateTime;

    private String modifier;

}
