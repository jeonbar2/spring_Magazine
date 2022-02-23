package com.spring_magazine.service;

import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.LikeDto;
import com.spring_magazine.dto.LikeRequestDto;
import com.spring_magazine.model.Likes;
import com.spring_magazine.model.Post;
import com.spring_magazine.model.User;
import com.spring_magazine.repository.LikeRepository;
import com.spring_magazine.repository.PostRepository;
import com.spring_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeDto save(LikeRequestDto requestDto, String username) {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("없어")
        );

        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new NotFoundException("없어")
        );

        Optional<Likes> is = likeRepository.findByUserAndPost(user, post);

        if (is.isPresent()) {//이미 좋아요 누름
            Long likeId = is.get().getId();
            likeRepository.deleteById(likeId);
            Likes deleted = is.get();
            return LikeDto.from(deleted);

        } else {
            Likes likes = new Likes(user, post);
            Likes saved = likeRepository.save(likes);
            return LikeDto.from(saved);
        }


    }
}
