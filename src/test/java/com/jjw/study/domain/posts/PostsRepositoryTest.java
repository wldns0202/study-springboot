package com.jjw.study.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // 단위테스트가 끝날때마다 수행되는 메소드를 저장. 배포전 전체 테스트 수행 시 테스트간 데이터 침범을 막기위해 사용. 여러 테스트 동시에하면 테스트 데이터베이스에 데이터가 남아 실패할 수 있음
    public void CleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void save_load(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title)
                                            .content(content)
                                            .author("wldns0202@naver.com")
                                            .build()); // 테이블 posts에 insert/update 쿼리 실행. id 값이 있다면 update, 없으면 insert가 실행됨

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
