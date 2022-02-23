package com.spring_magazine.repository;

import com.spring_magazine.model.Likes;
import com.spring_magazine.model.Post;
import com.spring_magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByUserAndPost(User user, Post post);

}
