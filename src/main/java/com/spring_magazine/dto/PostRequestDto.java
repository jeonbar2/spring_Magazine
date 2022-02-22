package com.spring_magazine.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
public class PostRequestDto {

    @NotNull
    private String image;
    @NotNull
    private String contents;
    @NotNull
    private String username;
}
