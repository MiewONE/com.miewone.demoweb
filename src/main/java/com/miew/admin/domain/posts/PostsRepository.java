package com.miew.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Entity클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없습니다.
public interface PostsRepository extends JpaRepository<Posts,Long> // JpaRepository<Entity클래스,PK타입>을 상속하면 기본적은 CRUD 메소드가 자동으로 생성된다.
{

}
