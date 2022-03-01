package com.spring_magazine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequestDto {

    private String username;


    private String password;
}
