package com.spring_magazine.controller;

import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.PostDto;
import com.spring_magazine.dto.PostRequestDto;
import com.spring_magazine.security.UserDetailsImpl;
import com.spring_magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class PostController {
    private final PostService postService;

    @GetMapping("/api/post")
    public ResponseEntity<List<PostDto>> getPost(){
        System.out.println("ss");
        List<PostDto> response = postService.getAll();
        return ResponseEntity.ok(response) ;
    }

    @PostMapping("/api/post")
    public ResponseEntity<PostDto> postPost(@Valid @RequestBody PostRequestDto requestDto,Authentication authentication) {
        if (authentication==null){
            System.out.println("로그인해라");
            System.out.println(authentication.getName());
            throw new NotFoundException("로그인해라");
        }
        String reqUsername = authentication.getName();
        PostDto response = postService.save(requestDto, reqUsername);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails==null){
            System.out.println("로그인해라");
            throw new NotFoundException("로그인해라");
        }
        PostDto response = postService.delete(postId,userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid PostRequestDto requestDto,@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails==null){
            System.out.println("로그인해라");
            throw new NotFoundException("로그인해라");
        }
        PostDto response = postService.update(postId,requestDto,userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}
