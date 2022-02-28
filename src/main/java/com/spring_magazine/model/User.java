package com.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;


@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.


@AllArgsConstructor
public class User {
    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String realName;


    @Column(nullable = false)
    private String password;


    @BatchSize(size=50)
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonBackReference //순환참조 방지
    private List<Post> postList;


    @BatchSize(size=50)
    @OneToMany(mappedBy =  "user" ,cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Likes> likesList;


    public User(String username,String realName, String password) {
        this.username = username;
        this.password = password;
        this.realName = realName;

    }


}
