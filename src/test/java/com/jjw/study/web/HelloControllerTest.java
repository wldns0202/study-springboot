package com.jjw.study.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // 테스트 진행 시 JUnit 내장 실행자외 다른 실행자를 실행. 여기서는 SpringRunner라는 스프링 실행자 사용. 스프링부트 내장 테스트와 JUnit의 연결자 역할
@WebMvcTest(controllers = HelloController.class) // 여러 테스트 어노테이션중 Web(Spring MVC)에 집중하는 것. @Controller, @ControllerAdvice 등사용 가능하나, @Service, @Component, @Repository 등은 사용 불가
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 Bean을 주입받음
    private MockMvc mvc; // 웹 API 테스트 시 사용. 스프링 MVC 테스트의 시작점. 이 클래스를 통해 Get, Post등 API 테스트 진행

    @Test
    public void hello_return() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello로 get 요청. 체이닝 지원되어 여러 검증 기능 이어서 선언 가능
                .andExpect(status().isOk()) // mvc.perform의 결과 검증. HTTP header의 status 검증. 200,404, 500 등. 여기서는 isOK 즉 200 인지 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증. 응답 본문의 내용 검증. Controller에서 hello를 리턴하는지 검증
    }
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;
        mvc.perform(get("/hello/dto")
                    .param("name", name) // param은 API 테스트 시 사용될 요청 파라미터 설정. 값은 String만 허용. 날짜/숫자등을 String으로 변환해야함
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증. $를 기준오르 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
