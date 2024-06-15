package com.whilabel_renewal.whilabel_backend.controller;

import com.whilabel_renewal.whilabel_backend.domain.WhiskyPost;
import com.whilabel_renewal.whilabel_backend.dto.BaseDTO;
import com.whilabel_renewal.whilabel_backend.dto.WhiskyPostListDTO;
import com.whilabel_renewal.whilabel_backend.jwt.JwtTokenManager;
import com.whilabel_renewal.whilabel_backend.repository.WhiskyPostRepository;
import com.whilabel_renewal.whilabel_backend.repository.WhiskyRepository;
import com.whilabel_renewal.whilabel_backend.util.UserIdExtractUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/whisky/")
public class WhiskyController {


    @Autowired
    private WhiskyPostRepository whiskyPostRepository;

    @Autowired
    private JwtTokenManager tokenManager;

    @GetMapping("list")
    public ResponseEntity<BaseDTO<List<WhiskyPostListDTO>>> list(HttpServletRequest request, Map<String, String> query) {
    //recent, oldest, rating-ascend, rating-descent
        String sort = query.get("sort");
        String page = query.get("page");
        Long userId = UserIdExtractUtil.extractUserIdFromHeader(request);
        List<WhiskyPost> lists = whiskyPostRepository.getByRecent(userId, 0);


        List<WhiskyPostListDTO> result = lists.stream().map(WhiskyPostListDTO::new).toList();


        BaseDTO<List<WhiskyPostListDTO>> response = BaseDTO.<List<WhiskyPostListDTO>>builder().data(result).build();
        return new ResponseEntity<>(response, HttpStatus.OK);


    };



}
