package com.spring_magazine.model;

import com.spring_magazine.common.Timestamped;
import com.spring_magazine.dto.PostRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "postId")
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String contents;


    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    public Post(PostRequestDto requestDto, User user){
        this.contents= requestDto.getContents();
        this.image= requestDto.getImage();
        this.user= user;
    }

    public void update(PostRequestDto requestDto){
        this.contents=requestDto.getContents();
        this.image = requestDto.getImage();

    }


}
