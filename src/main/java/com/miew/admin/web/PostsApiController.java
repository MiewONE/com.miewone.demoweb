package com.miew.admin.web;

import com.miew.admin.service.posts.PostsService;
import com.miew.admin.web.dto.PostsResponseDto;
import com.miew.admin.web.dto.PostsSaveRequestsDto;
import com.miew.admin.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts/post")
    public Long save(@RequestBody PostsSaveRequestsDto requestsDto)
    {
        return postsService.save(requestsDto);
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestsDto requestDto){ return postsService.update(id,requestDto);}
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){return postsService.findById(id);}
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
