package com.miew.admin.service.posts;

import com.miew.admin.domain.posts.Posts;
import com.miew.admin.domain.posts.PostsRepository;
import com.miew.admin.web.dto.PostsListResponseDto;
import com.miew.admin.web.dto.PostsResponseDto;
import com.miew.admin.web.dto.PostsSaveRequestsDto;
import com.miew.admin.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
//        posts.update(requestsDto.getTitle(),requestsDto.getContent());
//        posts.update(requestsDto.get)
        posts.update(
                requestsDto.getName(),
                requestsDto.getDate(),
                requestsDto.getPhNo(),
                requestsDto.getEmail(),
                requestsDto.getPersonID(),
                requestsDto.getSex(),
                requestsDto.getZip(),
                requestsDto.getAddress(),
                requestsDto.getDetailAddress(),
                requestsDto.getPassword(),
                requestsDto.getPosition(),
                requestsDto.getJobType()
        );
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
    @Transactional//(readOnly = true) //readOnly =true를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도가 개선됨. 등록,삭제,수전 기능이 없는 서비스메소드에 사용하는게 좋음
    public List<PostsListResponseDto> findAllDesc()
    {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // == .map(posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());  // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드.
    }
    @Transactional
    public void delete(Long id)
    {
        Posts posts =postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
