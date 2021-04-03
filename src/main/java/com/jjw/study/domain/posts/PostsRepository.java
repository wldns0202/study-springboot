package com.jjw.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // DAO랑 비슷. JPA에서는 Repository라고하며 인터페이스로 생성. 인터페이스 생성 후 <Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동생성됨
    // Entity와 repository는 항상 함께 위채해야함. 한 페키지에서 같이 관리
}
