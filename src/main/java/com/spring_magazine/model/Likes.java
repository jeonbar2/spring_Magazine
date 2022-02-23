package com.spring_magazine.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Likes {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "likeId")
    private Long id;

    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    @ManyToOne
    @JoinColumn(name="postId")
    private Post post;

    public Likes(User user, Post post) {
        this.user = user;
        this.post = post;
    }


}
