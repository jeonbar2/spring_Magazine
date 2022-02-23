package com.spring_magazine.dto;

import com.spring_magazine.model.Likes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeDto {
    private Long likeId;
    private Long postId;
    private String username;

    public static LikeDto from(Likes likes){
        return LikeDto.builder()
                .likeId(likes.getId())
                .username(likes.getUser().getUsername())
                .postId(likes.getPost().getId())
                .build();
    }
}
