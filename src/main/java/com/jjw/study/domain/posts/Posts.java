package com.jjw.study.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //Entity에는 절대 setter 안만듬. 생성자를 통해 최종값을 채운 후 DB로. 값 변경이 필요하면 해당 이벤트에 맞는 public 메소드를 호출해 변경하자
@NoArgsConstructor // 기본 생성자 추가 public Posts(){}
@Entity // 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 언더스코어 네이밍으로 테이블 이름을 매칭 [SalesManager -> sales_manager table]
public class Posts extends BaseTimeEntity{
    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙. IDENTITY를 추가해야만 auto_increment가 됨. 웬만하면 PK는 Long 타입 Auto_Increment로
    private Long id;

    @Column(length = 500, nullable = false) // 테이블컬럼을 나타내며 굳이 선언안해도 해당 클래스의 필드는 모두 컬럼이됨. 기본값 외에 변경이 필요한 옵션이 있을 때 선언.
    private String title;                 // 문자열 기본은 varchar(255)인데 500으로 늘리고 싶거나, 타입을 text로 변경하거나등에 사용

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
