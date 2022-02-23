package com.spring_magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;


@Getter
@Setter

public class LikeRequestDto {
    private Long postId;

}

