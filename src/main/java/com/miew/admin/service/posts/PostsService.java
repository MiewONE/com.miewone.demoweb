package com.miew.admin.service.posts;

import com.miew.admin.domain.posts.Posts;
import com.miew.admin.domain.posts.PostsRepository;
import com.miew.admin.web.dto.PostsResponseDto;
import com.miew.admin.web.dto.PostsSaveRequestsDto;
import com.miew.admin.web.dto.PostsUpdateRequestsDto;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestsDto requestsDto)
    {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return id;
    }
    // dirty checking 더티 체킹
    // update기능에서 데이터베이스에 쿼리를 보내는 부분이 없음. 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경. 일종의 논리적 개념이라고 보면됨. JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되나 안되나로 갈림.
    // JPA의 엔티티 매니저가 활성화 된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터를 영속성 컨텍스트가 유지된 상태이다.
    public PostsResponseDto findById(Long id)
    {
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
