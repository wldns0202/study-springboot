package com.jjw.study.web;

import com.jjw.study.service.posts.PostsService;
import com.jjw.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){ // 서버 템플릿 엔진에서 사용할수 있는 객체를 저장 가능. 여기서는 postsService.findAllDesc() 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
