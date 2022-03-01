package com.spring_magazine.controller;


import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.LoginRequestDto;
import com.spring_magazine.dto.SignupRequestDto;
import com.spring_magazine.dto.TokenDto;
import com.spring_magazine.model.User;
import com.spring_magazine.repository.UserRepository;
import com.spring_magazine.security.JwtTokenProvider;
import com.spring_magazine.security.UserDetailsImpl;
import com.spring_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    // 회원 로그인 페이지
    @PostMapping("/user/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto requestDto) {
        System.out.println("sss");
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(()->new NotFoundException("가입되지않은 아이디입니다") );
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new NotFoundException("잘못된 비밀번호입니다.");
        }
        TokenDto tokendto= new TokenDto();
        tokendto.setAccessToken(jwtTokenProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(tokendto);
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
