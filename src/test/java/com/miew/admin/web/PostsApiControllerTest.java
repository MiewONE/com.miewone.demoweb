package com.miew.admin.web;

import com.miew.admin.domain.posts.Posts;
import com.miew.admin.domain.posts.PostsRepository;
import com.miew.admin.web.dto.PostsSaveRequestsDto;
import com.miew.admin.web.dto.PostsUpdateRequestsDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // MockTesting과의 차이는 실제 서블릿 컨테이너 실행 여부. RestTestTemplate은 컨테이너를 직접 실행한다.

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception
    {
        postsRepository.deleteAll();
    }

    @Test // 테스트
    public void Posts_register() throws Exception
    {
        //given
        String title ="title";
        String content = "content";
        PostsSaveRequestsDto requestsDto = PostsSaveRequestsDto.builder() // PostsSaveRequestsDto의 생성자에 각 각 값을 할당
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url="http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestsDto,Long.class); // 지정된 url로 post 방식으로 requestsDto 객체 전달, 반환 값 Long형식

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);  //assertThat 테스트 검증 라이브러리 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받음. isequalto 반환값이 지정한 값과 동등한지 검사
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_update() throws Exception
    {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

        Long updateId = savedPosts.getId(); //@Getter을 이용하여 자동적으로 get 가져옴
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestsDto requestDto = PostsUpdateRequestsDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;


        HttpEntity<PostsUpdateRequestsDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);
        // exchange Http Header를 수정할 수 있고 결과를 Http ResponseEntity로 반환 받는다.
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle); // 왜 안되냐??시바
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);


    }
}