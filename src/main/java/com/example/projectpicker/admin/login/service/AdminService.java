package com.example.projectpicker.admin.login.service;

import com.example.projectpicker.admin.login.dto.AdminResponseLoginDTO;
import com.example.projectpicker.admin.login.dto.AdminSignUpDTO;
import com.example.projectpicker.security.TokenProvider;
import com.example.projectpicker.admin.login.dto.AdminPostDTO;
import com.example.projectpicker.admin.login.entity.AdminEntity;
import com.example.projectpicker.admin.login.repository.AdminRepository;
import com.example.projectpicker.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service //스프링 빈 등록
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository; // 관리자 repository 사용

    private final PasswordEncoder passwordEncoder; // 관리자 비밀번호 인코딩을 위해
    private final TokenProvider tokenProvider; // 관리자 토큰 제공을 위해


    // 관리자 로그인 로직
    public AdminResponseLoginDTO adminLogin(AdminPostDTO adminPostDTO){ //entity 로부터 받은 관리자 email와  관리자 password DTO
        // adminRepository에 관리자 이메일 을 찾는데 어떤 이메일? =>  entity로부터 받아 DTO로 변환한 이메일 값을 findEmail 에 저장
        AdminEntity findEmail = adminRepository.findByAdminEmail(adminPostDTO.getAdminEmail());

        // 만약 findEmail 값이 null 이라면? 가입된 회원이 아님.
        if(findEmail == null){
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }


        String adminPassword = findEmail.getAdminPassword(); // 관리자 비밀번호
        String inputPassword = adminPostDTO.getAdminPassword(); // 외부로부터 입력된 관리자 비밀번호

            if(!passwordEncoder.matches(inputPassword,adminPassword)){ // 만약 입력한 관리자 비밀번호가 db에 저장된 관리자 비밀번호와 다르다면
                findEmail.setMessage("비밀번호가 일치하지 않습니다."); // 비밀번호가 일치 않다는 메시지가 설정!
            }
        UserEntity user = UserEntity.builder()
                .userId(findEmail.getAdminId())
                .userEmail(findEmail.getAdminEmail())
                .userName("관리자")
                .build();
        String token = tokenProvider.createToken(user);

        return new AdminResponseLoginDTO(findEmail, token);
    }

    // 관리자 회원가입 로직
    public AdminPostDTO signUp(AdminSignUpDTO adminSignUpDTO){

        String rawPassword = adminSignUpDTO.getAdminPassword(); // 평문 암호
        String encodedPassword = passwordEncoder.encode(rawPassword); // 인코딩을 통한 비밀번호 암호화
        adminSignUpDTO.setAdminPassword(encodedPassword); // 암호화된 비밀번호를 adminSignUpDTO(dto->entity) 에 설정

        AdminEntity save = adminRepository.save(adminSignUpDTO.toEntity());

        return new AdminPostDTO(save);
    }
}
