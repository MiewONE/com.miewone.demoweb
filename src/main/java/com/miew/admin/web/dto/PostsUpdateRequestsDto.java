package com.miew.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestsDto {

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

    @Builder
    public PostsUpdateRequestsDto(String name,String date,String phNo,String email,String personID,String sex,String zip,String address,String detailAddress,String password,String position,String jobType)
    {
        this.name = name;
        this.date = date;
        this.phNo = phNo;
        this.email = email;
        this.personID = personID;
        this.sex = sex;
        this.zip = zip;
        this.address = address;
        this.detailAddress = detailAddress;
        this.password = password;
        this.position = position;
        this.jobType = jobType;
    }
}
