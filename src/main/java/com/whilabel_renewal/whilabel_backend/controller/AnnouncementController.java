package com.whilabel_renewal.whilabel_backend.controller;


import com.whilabel_renewal.whilabel_backend.domain.Announcement;
import com.whilabel_renewal.whilabel_backend.dto.AnnouncementListDTO;
import com.whilabel_renewal.whilabel_backend.dto.BaseDTO;
import com.whilabel_renewal.whilabel_backend.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementRepository announcementRepository;

    @GetMapping("list")
    public ResponseEntity<BaseDTO<List<AnnouncementListDTO>>> list(@RequestParam("page") int page) {
        List<Announcement> list = announcementRepository.getAnnouncementList(page);

        return new ResponseEntity<>(BaseDTO.<List<AnnouncementListDTO>>builder().data(list.stream().map(AnnouncementListDTO::new).toList()).build(), HttpStatus.OK);
    }


    @GetMapping("detail")
    public ResponseEntity<BaseDTO<Announcement>> detail(@RequestParam("id") int id) {
        Optional<Announcement> result = announcementRepository.findById((long) id);
        if (result.isPresent()) {
            return new ResponseEntity<>(BaseDTO.<Announcement>builder().data(result.get()).build(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
