package com.miew.admin.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResposeDtoTest {

    @Test
    public void lombok_fn_test()
    {
        //given
        String name="test";
        int amount =1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        // assertTaht
        // aseerttj라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고싶은 대상을 메소드 인자로 받습니다.
        // 메소드 체이닝이 지원되어 isEqulaTo와 같이 메소드를 이어서 사용할 수 있습니다.

        // isEqualTo
        // assertj의 동등 비교 메소드
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.

        // assert의 장점
        // - CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음, 자동완성이 좀 더 확실하게 지원됨.
    }
}
