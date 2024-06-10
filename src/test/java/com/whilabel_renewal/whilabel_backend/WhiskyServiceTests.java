package com.whilabel_renewal.whilabel_backend;


import com.whilabel_renewal.whilabel_backend.domain.*;
import com.whilabel_renewal.whilabel_backend.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@Rollback(value = false)
public class WhiskyServiceTests {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private WbWhiskyRepository wbWhiskyRepository;

    @Autowired
    private WbDistilleryRepository wbDistilleryRepository;

    @Test
    public void whiskyTest()  {
        Whisky whisky = new Whisky();
        whisky.setName("dummyWhiskyName");

        WbWhisky wbWhisky = wbWhiskyRepository.findById(2L).get();

        whisky.setWbWhisky(wbWhisky);


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
        whisky.setName("dummyname");

        wbWhiskyRepository.save(whisky);

        WbWhisky result = wbWhiskyRepository.findById(whisky.getId()).get();

        Assertions.assertThat(whisky.getName()).isEqualTo(result.getName());
    }

    @Test
    public void wbDistillerySaveTest() {

        WbDistillery distillery = new WbDistillery();
        distillery.setCountry("dummyCountry");
        distillery.setAddress("dummyAddress");
        distillery.setWhiskyCount(10);
        distillery.setVoteCount(10);
        distillery.setRating(10.0);
        distillery.setClosed(false);

        wbDistilleryRepository.save(distillery);

        WbDistillery result = wbDistilleryRepository.findById(distillery.getId()).get();

        Assertions.assertThat(distillery.getAddress()).isEqualTo(result.getAddress());

    }

}
