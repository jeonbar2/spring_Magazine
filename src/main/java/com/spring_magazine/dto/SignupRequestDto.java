package com.spring_magazine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @NotEmpty(message =  "아이디를 확인하세요")
    @Pattern(regexp = "\\w{3,15}" ,message = "영문,숫자를 포함한 3~15자로 설정하세요")
    @Size(min=3,max=15 ,message = "아이디를 3~15자로 설정하세요")
    private String username;

    @NotEmpty(message = "비밀번호를 확인하세요")
    @Size(min=4,max=20,message = "비밀먼호를 4~20자로 설정하세요")
    private String password;

    @NotEmpty(message = "재확인 비밀번호를 확인하세요")
    @Size(min=4,max=20 ,message = "잘못된 비밀번호 길이")
    private String passwordAgain;

    @NotEmpty(message = "실명을 확인하세요")
    @Size(min=3 ,max=20,message = "잘못된 실명 길이")
    private String realName;
}