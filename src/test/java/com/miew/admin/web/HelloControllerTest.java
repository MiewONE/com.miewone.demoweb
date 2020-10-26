package com.miew.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

// RunWith
// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다. , 여기서는 SpringRunner라는 스프링 실행자를 사용
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 합니다.

@RunWith(SpringRunner.class)
@WebMvcTest                     //WebMvcTest 여러 스프링 테스트 어노테이션 중, Web에 집중할 수 있는 어노테이션, 선언시 @Controller,@ControllerAdvice등을 사용 가능
public class HelloControllerTest { // 하지만 Service,Component,Repository는 사용할 수 없음. 컨트롤러만 사용하기 때문에 선언
    @Autowired          // 스프링이 관리하는 빈을 주입받습니다.
    private MockMvc mvc;    // 웹 API를 테스트할 때 사용합니다.
                            // 스프링 MVX테스트의 시작점 , 이 클래스를 통해 HTTP GET,POST등에 대한 API테스트를 할 수 있습니다.
    @Test
    public void return_hello() throws Exception
    {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello주소로 HTTP GET요청을 합니다. , 체이닝이 지원되어 여러 검증 기능을 이서서 선언할 수 있다.
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        
        // .andExpect(status().isOk()) ~ mvc.perform의 결과를 검증 , HTTP Header의 Status를 검증, 200,404,500등의 상태를 검증 , 여기선 ok인지 200인지 검증
        // .andExpect(content().string(hello)); mvc.perform의 결과를 검증, 응답 본문의 내용을 검증 Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }
    @Test
    public void return_helloDto() throws Exception
    {
        String name= "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name",name) //param , API 테스트할 때 사용될 요청파라미터를 설정, 단 값이 String만 허용됨, 그래서 숫자/날씨 등의 데이터도 문자열로 변경해야함
                    .param("amount",String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name",is(name))) //jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드입니다. $를 기준으로 필드명을 명시
                        .andExpect(jsonPath("$.amount",is(amount)));
    }
}

