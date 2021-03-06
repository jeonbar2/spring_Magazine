package com.spring_magazine.service;


import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.SignupRequestDto;
import com.spring_magazine.model.User;
import com.spring_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String realName = requestDto.getRealName();

// 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new NotFoundException("중복된 사용자 ID 가 존재합니다.");
        }
        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword()); // 들어온 패스워드를 암호화해서 저장장

        User user = new User(username,realName, password);
        userRepository.save(user);
    }
}