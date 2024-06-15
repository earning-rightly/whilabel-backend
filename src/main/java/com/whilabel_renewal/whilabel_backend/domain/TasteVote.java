package com.whilabel_renewal.whilabel_backend.domain;


import com.whilabel_renewal.whilabel_backend.enums.TasteTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TasteVote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wb_whisky_id")
    private WbWhisky wbWhisky;


    @Enumerated(EnumType.STRING)
    @Column(name = "taste_tag")
    private TasteTag tasteTag;

    @Column(name = "vote_count")
    private int voteCount = 0;
}
