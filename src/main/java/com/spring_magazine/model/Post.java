package com.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring_magazine.common.Timestamped;
import com.spring_magazine.dto.PostRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "username")
    private User user;

    @BatchSize(size=50)
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Likes> likesList =new ArrayList<>();

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
