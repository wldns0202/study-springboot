package com.jjw.study.service.posts;

import com.jjw.study.domain.posts.Posts;
import com.jjw.study.domain.posts.PostsRepository;
import com.jjw.study.web.dto.PostsListResponseDto;
import com.jjw.study.web.dto.PostsResponseDto;
import com.jjw.study.web.dto.PostsSaveRequestDto;
import com.jjw.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // map(posts -> new PostsListResponseDto(posts))와 동일. postsRepository 결과로 넘어온 Posts의 stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환
                .collect(Collectors.toList());
    }
}
