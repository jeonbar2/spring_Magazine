package com.spring_magazine.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@Getter
public class PostRequestDto {

    @NotEmpty(message = "이미지입력하세요")
    private String image;
    @NotEmpty(message = "내용을입력하세요")
    private String contents;
}
