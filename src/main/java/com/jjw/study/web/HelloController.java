package com.jjw.study.web;

import com.jjw.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){ //@requestparam은 외부에서 API로 넘긴 파라미터를 가져옴. 외부에서 name이라는 이름으로 넘긴 파라미터를 String name에 저장
        return new HelloResponseDto(name, amount);
    }
}
