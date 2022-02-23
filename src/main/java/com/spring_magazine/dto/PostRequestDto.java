package com.spring_magazine.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@Getter
public class PostRequestDto {

    @NotBlank(message = "이미지입력하세요")
    private String image;
    @NotBlank(message = "내용을입력하세요")
    private String contents;
}
