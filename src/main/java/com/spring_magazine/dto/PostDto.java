package com.spring_magazine.dto;

import com.spring_magazine.model.Post;
import com.spring_magazine.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDto {
    private Long postId;
    private String username;
    private String image;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static PostDto from(Post post){
        return PostDto.builder()
                .postId(post.getId())
                .username(post.getUser().getUsername())
                .image(post.getImage())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }
}
