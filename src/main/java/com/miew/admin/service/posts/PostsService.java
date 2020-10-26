package com.miew.admin.service.posts;

import com.miew.admin.domain.posts.PostsRepository;
import com.miew.admin.web.dto.PostsSaveRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto)
    {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }
}
