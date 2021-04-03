package com.jjw.study.web.dto;

import com.jjw.study.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto { //entity의 일부 필드만 사용하므로 굳이 allargs 생성자 필요없음.
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
