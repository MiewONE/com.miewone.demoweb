package com.miew.admin.web.dto;

import com.miew.admin.domain.posts.Posts;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    // 기초
//    private Long id;
//    private String title;
//    private String author;
//    private LocalDateTime modifiedDate;
    // 적용
    private Long id;
    private String name;        //사원 이름
    private String date;        // 입사 일자
    private String phNo;        // 핸드폰 번호
    private String email;       // 이메일 주소
    private String personID;    // 주민등록 번호
    private String sex;        // 성별
    private String zip;         //우편번호
    private String address;     // 주소지
    private String detailAddress;
    private String password;    // 비밀번호
    private String position;    // 직급
    private String jobType;     // 직종
    public PostsListResponseDto(Posts entity)
    {
        // 기초
//        this.id=entity.getId();
//        this.title = entity.getTitle();
//        this.author = entity.getAuthor();
//        this.modifiedDate = entity.getModifiedDate();

        // 적용
        this.id = entity.getId();
        this.name = entity.getName();
        this.date = entity .getDate();
        this.phNo = entity.getPhNo();
        this.email = entity.getEmail();
        this.personID = entity.getPersonID();
        this.sex = entity.getSex();
        this.zip = entity.getZip();
        this.address = entity.getAddress();
        this.detailAddress =entity.getDetailAddress();
        this.password = entity.getPassword();
        this.position = entity.getPosition();
        this.jobType = entity.getJobType();

    }
}
