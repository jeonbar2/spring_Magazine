package com.spring_magazine.controller;

import com.spring_magazine.dto.PostDto;
import com.spring_magazine.dto.PostRequestDto;
import com.spring_magazine.security.UserDetailsImpl;
import com.spring_magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class PostController {
    private final PostService postService;

    @GetMapping("/api/post")
    public ResponseEntity<List<PostDto>> getPost(){
        List<PostDto> response = postService.getAll();
        return ResponseEntity.ok(response) ;
    }

    @PostMapping("/api/post")
    public ResponseEntity<PostDto> postPost(PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String reqUsername = userDetails.getUser().getRealName();
        PostDto response = postService.save(requestDto, reqUsername);
        return ResponseEntity.ok(response);
    }
}