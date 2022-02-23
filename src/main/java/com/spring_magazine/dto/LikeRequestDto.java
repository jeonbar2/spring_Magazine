package com.spring_magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class LikeRequestDto {
    private Long postId;
}
