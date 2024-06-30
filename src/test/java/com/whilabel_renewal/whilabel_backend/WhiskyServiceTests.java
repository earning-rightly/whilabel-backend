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

    @Autowired
    private WhiskyPostRepository whiskyPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TasteFeatureRepository tasteFeatureRepository;


    @Test
    public void whiskyTest()  {
        Whisky whisky = new Whisky();
        whisky.setName("dummyWhiskyName2");

        WbWhisky wbWhisky = wbWhiskyRepository.findById(52L).get();

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
        whisky.setName("dummyname3");

        wbWhiskyRepository.save(whisky);

        WbWhisky result = wbWhiskyRepository.findById(whisky.getId()).get();

        Assertions.assertThat(whisky.getName()).isEqualTo(result.getName());
    }

    @Test
    public void wbDistillerySaveTest() {

        WbDistillery distillery = new WbDistillery();
        distillery.setCountry("dummyCountry3");
        distillery.setAddress("dummyAddress3");
        distillery.setWhiskyCount(10);
        distillery.setVoteCount(10);
        distillery.setRating(10.0);
        distillery.setClosed(false);

        wbDistilleryRepository.save(distillery);

        WbDistillery result = wbDistilleryRepository.findById(distillery.getId()).get();

        Assertions.assertThat(distillery.getAddress()).isEqualTo(result.getAddress());

    }


    @Test
    public void whiskyPostSaveTest() {
        User user = userRepository.findById(1L).get();
        Whisky whisky = whiskyRepository.findById(1L).get();
        TasteFeature tf = tasteFeatureRepository.findById(1L).get();


        WhiskyPost post = new WhiskyPost();

        post.setUser(user);
        post.setWhisky(whisky);
        post.setTasteFeature(tf);
        post.setTastNote("kjfa;lskjdf;askjdf;klajsdf;lkajsdf a;lsdkfj;aslkdjf");
        post.setImageUrl("https://picsum.photos/id/237/200/300");

        whiskyPostRepository.save(post);




    }



}
