package com.jjw.study.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount); // assertj라는 테스트 검증 라이브러리 메소스. 검증하고픈 대상을 메소드 인자로 받음. 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용 가능
    }
}
