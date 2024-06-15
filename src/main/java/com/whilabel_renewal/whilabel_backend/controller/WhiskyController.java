package com.whilabel_renewal.whilabel_backend.controller;

import com.whilabel_renewal.whilabel_backend.domain.TasteFeature;
import com.whilabel_renewal.whilabel_backend.domain.User;
import com.whilabel_renewal.whilabel_backend.domain.Whisky;
import com.whilabel_renewal.whilabel_backend.domain.WhiskyPost;
import com.whilabel_renewal.whilabel_backend.dto.BaseDTO;
import com.whilabel_renewal.whilabel_backend.dto.WhiskyPostDetailDTO;
import com.whilabel_renewal.whilabel_backend.dto.WhiskyPostListDTO;
import com.whilabel_renewal.whilabel_backend.jwt.JwtTokenManager;
import com.whilabel_renewal.whilabel_backend.repository.TasteFeatureRepository;
import com.whilabel_renewal.whilabel_backend.repository.UserRepository;
import com.whilabel_renewal.whilabel_backend.repository.WhiskyPostRepository;
import com.whilabel_renewal.whilabel_backend.repository.WhiskyRepository;
import com.whilabel_renewal.whilabel_backend.requestDto.WhiskyPostDetailRequestDTO;
import com.whilabel_renewal.whilabel_backend.util.UserIdExtractUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/whisky/")
public class WhiskyController {


    @Autowired
    private WhiskyPostRepository whiskyPostRepository;

    @Autowired
    private WhiskyRepository whiskyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TasteFeatureRepository tasteFeatureRepository;


    @GetMapping("list")
    public ResponseEntity<BaseDTO<List<WhiskyPostListDTO>>> list(HttpServletRequest request, @RequestParam Map<String, String> query) {
    //recent, oldest, rating-ascend, rating-descent
        String sort = query.get("sort");
        String page = query.get("page");
        if (page == null || page.isBlank() || page.isEmpty()) {
            page = "0";
        }
        Long userId = UserIdExtractUtil.extractUserIdFromHeader(request);
        List<WhiskyPost> lists;

        switch (sort) {
            case "recent" -> lists = whiskyPostRepository.getByRecent(userId, Integer.parseInt(page));
            case "oldest" -> lists = whiskyPostRepository.getByOldest(userId, Integer.parseInt(page));
            case "rating-ascend" -> lists = whiskyPostRepository.getByRatingAscend(userId, Integer.parseInt(page)); //평점 낮은순
            case "rating-descend" -> lists = whiskyPostRepository.getByRatingAscend(userId, Integer.parseInt(page)); // 평점 높은순
            default -> lists = whiskyPostRepository.getByRecent(userId, Integer.parseInt(page));
        }

        List<WhiskyPostListDTO>  result = lists.stream().map(WhiskyPostListDTO::new).toList();


        BaseDTO<List<WhiskyPostListDTO>> response = BaseDTO.<List<WhiskyPostListDTO>>builder().data(result).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("grid")
    public ResponseEntity<BaseDTO<List<WhiskyPostListDTO>>> grid(HttpServletRequest request, Map<String, String> query) {
        String page = query.get("page");
        if (page == null || page.isBlank() || page.isEmpty()) {
            page = "0";
        }
        Long userId = UserIdExtractUtil.extractUserIdFromHeader(request);
        List<WhiskyPost> lists = whiskyPostRepository.getByRecent(userId, Integer.parseInt(page));

        List<WhiskyPostListDTO> result = lists.stream().map(WhiskyPostListDTO::new).toList();


        BaseDTO<List<WhiskyPostListDTO>> response = BaseDTO.<List<WhiskyPostListDTO>>builder().data(result).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("detail")
    public ResponseEntity<BaseDTO<WhiskyPostDetailDTO>> detail(@RequestParam(name = "id") int id) {
        Optional<WhiskyPost> wp = whiskyPostRepository.findById((long) id);

        if (wp.isEmpty()) {
            return new ResponseEntity<>(BaseDTO.<WhiskyPostDetailDTO>builder().message("no data").build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(BaseDTO.<WhiskyPostDetailDTO>builder().data(new WhiskyPostDetailDTO(wp.get())).build(), HttpStatus.OK);
    }


    @PostMapping("detail")
    public ResponseEntity<BaseDTO<Object>> saveDetail(HttpServletRequest request, @RequestBody WhiskyPostDetailRequestDTO requestDTO){
        Long userId = UserIdExtractUtil.extractUserIdFromHeader(request);
        User user = userRepository.findById(userId).get();

        WhiskyPost wp = new WhiskyPost();
        wp.setUser(user);
        wp.setImageUrl(requestDTO.getImageUrl());
        wp.setRating(requestDTO.getRating());
        wp.setTastNote(requestDTO.getTasteNote());

        TasteFeature tf = new TasteFeature();
        tf.setBodyRate(requestDTO.getBodyRate().intValue());
        tf.setFlavorRate(requestDTO.getFlavorRate().intValue());
        tf.setPeatRate(requestDTO.getPeatRate().intValue());
        wp.setTasteFeature(tf);
        tasteFeatureRepository.save(tf);


        if (requestDTO.getWhiskyId() != null) {
            Whisky whisky = whiskyRepository.findById(requestDTO.getWhiskyId()).get();
            wp.setWhisky(whisky);
        }

        whiskyPostRepository.save(wp);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
