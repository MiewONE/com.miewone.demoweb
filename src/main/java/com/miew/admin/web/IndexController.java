package com.miew.admin.web;

import com.miew.admin.service.posts.PostsService;
import com.miew.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor // final로 지정된 필드를 포함한 기본생성자를 만들어줍니다.
@Controller
public class IndexController {
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model) //Model : 모델은 HashMap 형태를 가지고 있으므로 key 값과 value값처럼 사용할 수 있다.
    {
        model.addAttribute("posts",postsService.findAllDesc());
        return "main";
    }
    @GetMapping("/posts/save")
    public String postsSave()
    {
        return "posts-membersave";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model)
    {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-memberupdate";
    }
}
