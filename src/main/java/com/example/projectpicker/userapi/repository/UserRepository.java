package com.example.projectpicker.userapi.repository;

import com.example.projectpicker.userapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
1. JpaRepository 인터페이스를 상속하기만 하면 인터페이스에 따로 @Repository등의 어노테이션을 추가할 필요 없다.
+기본적인 Create, Read, Update, Delete가 자동으로 생성된다.
2. JpaRepository를 상속받을때는 사용될 Entity 클래스와 기본 키 타입 이 들어가게된다.
<여기서 나는 UserEntity를 상속받을것이고, 기본키인 ID의 타입은 String 이다> ==> JpaRepository<T, ID>
*/

public interface UserRepository extends JpaRepository <UserEntity,String>{

    // 이메일로 회원을 조회
    // 아래 코드는 sql문으로 다음과 값다. (select * from tbl_user where email = ? )
    UserEntity findByEmail(String email);

    // 이메일 중복 검사
    // select count(*) from tbl_user where email = ?
    boolean existsByEmail(String email);
}
