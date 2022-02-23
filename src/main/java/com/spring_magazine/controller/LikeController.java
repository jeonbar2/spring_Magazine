package com.spring_magazine.controller;

import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.LikeDto;
import com.spring_magazine.dto.LikeRequestDto;
import com.spring_magazine.security.UserDetailsImpl;
import com.spring_magazine.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/post/like")
    public ResponseEntity<LikeDto> iLike(@RequestBody LikeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails==null){
            throw new NotFoundException("로그인해라");
        }
        System.out.println("여기는되나?1");
        String username = userDetails.getUsername();
        System.out.println("여기는되나?"+username);
        LikeDto response = likeService.save(requestDto,username);
        return ResponseEntity.ok(response);

    }
}
