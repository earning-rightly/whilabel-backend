package com.whilabel_renewal.whilabel_backend;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whilabel_renewal.whilabel_backend.domain.*;
import com.whilabel_renewal.whilabel_backend.enums.TasteTag;
import com.whilabel_renewal.whilabel_backend.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hibernate.annotations.SQLJoinTableRestriction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.logging.Logger;

@SpringBootTest
@Rollback(value = false)
public class WhiskyServiceTests {

    @Autowired
    private WhiskyPostRepository whiskyPostRepository;

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private DistilleryRepository distilleryRepository;

    @Autowired
    private WbWhiskyRepository wbWhiskyRepository;

    @Autowired
    private WbDistilleryRepository wbDistilleryRepository;

    @Autowired
    private TasteVoteRepository tasteVoteRepository;

    @Test
    public void whiskyTest() throws JsonProcessingException {
        Whisky whisky = new Whisky();
        whisky.setName("dummyWhiskyName");

        WbWhisky wbWhisky = wbWhiskyRepository.findById(2L).get();

        whisky.setWbWhisky(wbWhisky);


        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("1" + objectMapper.writeValueAsString(wbWhisky));
        System.out.println("tasteVote" + objectMapper.writeValueAsString(wbWhisky.getTasteVoteList()));
        System.out.println("1" + whisky.toString());

        whiskyRepository.save(whisky);

        Whisky result = whiskyRepository.findById(whisky.getId()).get();
        WbWhisky wbResult = result.getWbWhisky();

        System.out.println(result.toString());

        Assertions.assertThat(whisky.getName()).isEqualTo(result.getName());
        Assertions.assertThat(wbWhisky.getName()).isEqualTo(wbResult.getName());

    }


    @Test
    public void wbWhiskySaveTest() {

        WbWhisky whisky = new WbWhisky();
        whisky.setName("dummyname" + whisky.getId());

        wbWhiskyRepository.save(whisky);

        WbWhisky result = wbWhiskyRepository.findById(whisky.getId()).get();

        Assertions.assertThat(whisky.getName()).isEqualTo(result.getName());
    }

    @Test
    public void wbDistillerySaveTest() {

        WbDistillery distillery = new WbDistillery();
        distillery.setCountry("dummyCountry" + distillery.getId());
        distillery.setAddress("dummyAddress" + distillery.getId());
        distillery.setWhiskyCount(10);
        distillery.setVoteCount(10);
        distillery.setRating(10.0);
        distillery.setClosed(false);

        wbDistilleryRepository.save(distillery);

        WbDistillery result = wbDistilleryRepository.findById(distillery.getId()).get();

        Assertions.assertThat(distillery.getAddress()).isEqualTo(result.getAddress());

    }


//    @Test
//    public void saveTasteVote() {
//        WbWhisky wbWhisky = wbWhiskyRepository.findById(2L).get();
//
//        TasteVote tasteVote = new TasteVote();
//        tasteVote.setTasteTag(TasteTag.CHOCOLATE);
//        tasteVote.setWbWhisky(wbWhisky);
//        tasteVote.setVoteCount(10);
//
//        tasteVoteRepository.save(tasteVote);
//
//        TasteVote tasteVote1 = new TasteVote();
//        tasteVote1.setTasteTag(TasteTag.CITRIC);
//        tasteVote1.setWbWhisky(wbWhisky);
//        tasteVote1.setVoteCount(10);
//
//        tasteVoteRepository.save(tasteVote1);
//
//        TasteVote tasteVote3 = new TasteVote();
//        tasteVote3.setTasteTag(TasteTag.DRIED_FRUIT);
//        tasteVote3.setWbWhisky(wbWhisky);
//        tasteVote3.setVoteCount(10);
//
//        tasteVoteRepository.save(tasteVote3);
//
//
//    }


}
