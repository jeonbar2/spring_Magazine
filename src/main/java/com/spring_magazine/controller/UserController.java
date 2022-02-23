package com.spring_magazine.controller;


import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.SignupRequestDto;
import com.spring_magazine.security.UserDetailsImpl;
import com.spring_magazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails!=null){
            throw new NotFoundException("이미 로그인됨");
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails!=null){
            throw new NotFoundException("이미 로그인됨");
        }
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        if(requestDto.getPassword().equals(requestDto.getPasswordAgain())) {
            if ((requestDto.getPassword()).contains(requestDto.getUsername())){
                throw new NotFoundException("비밀번호에 아이디가 포함되었습니다.");
            }
            userService.registerUser(requestDto);
        }else{
            throw new NotFoundException("비밀번호 다시 확인");
        }
        return "redirect:/user/login";
    }
}
