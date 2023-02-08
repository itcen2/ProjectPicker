package com.example.projectpicker.user.dto;


import com.example.projectpicker.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

// 회원가입 완료 후 클라이언트에게 응답할 데이터를 담는 객체 ( 클라이언트 <---- DB )

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email") // email 만 비교해라. 굳이 비번,이름까지 비교할 필요x
public class UserSignUpResponseDTO {
    private String email;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime joinDate;

    //엔티티를 DTO로 변경하는 생성자

    public UserSignUpResponseDTO(UserEntity userEntity) {
        this.email = userEntity.getUserEmail();
        this.userName = userEntity.getUserName();
        this.joinDate = userEntity.getJoinDate();
    }
}


/**
 * DTO는 단순히 계층간 데이터를 전달할 때 사용하지만, 그것이 필수는 아닙니다.
 *
 * 물론 레이어간 데이터 이동이 필요하면 DTO를 이동해도 되지만, Entity를 이용하셔도 되고, 단순히 String, Map등을 이용해도 됩니다.
 */