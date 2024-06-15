package com.whilabel_renewal.whilabel_backend.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TasteFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "body_rate")
    private int bodyRate = 0;

    @Column(name = "flavor_rate")
    private int flavorRate = 0;

    @Column(name = "peat_rate")
    private int peatRate = 0;
}
