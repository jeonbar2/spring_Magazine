package com.spring_magazine.service;

import com.spring_magazine.common.error.NotFoundException;
import com.spring_magazine.dto.PostDto;
import com.spring_magazine.dto.PostRequestDto;
import com.spring_magazine.model.Post;
import com.spring_magazine.model.User;
import com.spring_magazine.repository.PostRepository;
import com.spring_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)

public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostDto> getAll(){
        List<Post> all = postRepository.findAll();
        List<PostDto> dto = all.stream()
                .map(post->PostDto.from(post))
                .collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public PostDto save(PostRequestDto requestDto,String reqUsername) {
//        if(!(reqUsername.equals(requestDto.getUsername()))){
//            throw new NotFoundException("없어");
//        }

        User user = userRepository.findByUsername(reqUsername).orElseThrow(
                ()->new NotFoundException("없어.")
        );
        Post post = new Post(requestDto, user);
        Post saved = postRepository.save(post);
        return PostDto.from(saved);
    }

    @Transactional
    public PostDto delete(Long postId, String username){
        Post deleted = postRepository.findById(postId).orElseThrow(
                ()-> new NotFoundException("게시글postId가 없음")
        );
        if (!(deleted.getUser().getUsername().equals(username))){
            throw new NotFoundException("권한이 없습니다.");
        }
        postRepository.deleteById(postId);
        return PostDto.from(deleted);
    }

    @Transactional
    public PostDto update(Long postId, PostRequestDto requestDto,String username){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new NotFoundException("게시글이 없습니다.")
        );
        if(!(post.getUser().getUsername().equals(username))){
            throw new NotFoundException("권한이 없습니다.");
        }

        post.update(requestDto);
        return PostDto.from(post);
    }

}
