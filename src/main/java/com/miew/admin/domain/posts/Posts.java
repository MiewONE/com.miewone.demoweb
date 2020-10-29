package com.miew.admin.domain.posts;

import com.miew.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter // 클래스 내 모든 필드의 getter 메소드를 자동생성
@NoArgsConstructor // 기본생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냅니다. , 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭합니다.
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드를 나타냅니다.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // PK의 생성 규칙을 나타냅니다. mysql의 auto_increment와 비슷
    private Long id;

//    @Column(length=500,nullable=false)  // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다. 사용하는이유
//    private String title;               // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함.
    @Column(length=10,nullable=false)
    private String name;        //사원 이름
    @Column
    private String date;        // 입사 일자
    @Column
    private String phNo;        // 핸드폰 번호
    @Column
    private String email;       // 이메일 주소
    @Column
    private String personID;    // 주민등록 번호
    @Column
    private String sex;        // 성별
    @Column
    private String zip;         //우편번호
    @Column
    private String address;     // 주소지
    @Column
    private String detailAddress;
    @Column
    private String password;    // 비밀번호
    @Column
    private String position;    // 직급
    @Column
    private String jobType;     // 직종

    //사진 추가해야함.


//    @Column(columnDefinition="TEXT",)
//    private String content;
//
//    private String author;

//    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
//    public Posts(String title,String content,String author)
//    {
//        this.title = title;
//        this.content = content;
//        this.author=author;
//    }
    @Builder
    public Posts(String name,String date,String phNo,String email,String personID,String sex,String zip,String address,String detailAddress,String password,String position,String jobType)
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

    public void update(String name,String date,String phNo,String email,String personID,String sex,String zip,String address,String detailAddress,String password,String position,String jobType) {
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
