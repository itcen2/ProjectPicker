package com.example.projectpicker.user.service;

import com.example.projectpicker.security.TokenProvider;
import com.example.projectpicker.user.dto.LoginResponseDTO;
import com.example.projectpicker.user.dto.UserInfoUpdateRequestDTO;
import com.example.projectpicker.user.dto.UserSignUpDTO;
import com.example.projectpicker.user.dto.UserSignUpResponseDTO;
import com.example.projectpicker.user.entity.UserEntity;
import com.example.projectpicker.user.exception.DuplicatedEmailException;
import com.example.projectpicker.user.exception.NoRegisteredArgumentsException;
import com.example.projectpicker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    // 회원가입 처리 PART
    public UserSignUpResponseDTO create(final UserSignUpDTO userSignUpDTO){ //final 키워드: 클래스나 변수에 final을 붙이면 처음 정의된 상태가 변하지 않는 것을 보장한다는 의미

        // 가입정보가 null 일때
        if(userSignUpDTO == null){
            throw new NoRegisteredArgumentsException("가입정보가 없습니다.");
        }

        final String email = userSignUpDTO.getEmail(); // 클라이언트가 보낸 데이터(이메일)

        // 이미 존재하는 이메일인 경우
        if(userRepository.existsByUserEmail(email)){
            log.warn("이미 존재하는 이메일 입니다. - {}",email);
            throw new DuplicatedEmailException("이미 존재하는 이메일입니다.");
        }

        //패스워드 인코딩 (암호화처리)
        String rawPassword = userSignUpDTO.getPassword(); // 평문 비밀번호
        String encodedPassword = passwordEncoder.encode(rawPassword); //passwordEncoder 외부 라이브러리를 이용해 비밀번호 암호화 처리
        userSignUpDTO.setPassword(encodedPassword); // 암호화된 비밀번호로 설정!

        UserEntity savedUser = userRepository.save(userSignUpDTO.toEntity()); // 위 과정을 저장!

        log.info("회원 가입 성공!! - user_id: {} ", savedUser.getUserId());
        return new UserSignUpResponseDTO(savedUser); // 클라이언트에게 응답결과에는 savedUSer(암호처리된) 정보 반환
    }

    public String updateUserInfo(final String userId, final UserInfoUpdateRequestDTO userInfoUpdateRequestDTO)
            throws RuntimeException{

        UserEntity entity = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("수정할 유저가 존재하지 않습니다."));
        //패스워드 인코딩 (암호화처리)
        String rawUpPassword = userInfoUpdateRequestDTO.getPassword(); // 평문 비밀번호
        if(rawUpPassword != null && !passwordEncoder.matches(rawUpPassword,entity.getUserPassword())) {
            String encodedPassword = passwordEncoder.encode(rawUpPassword); //passwordEncoder 외부 라이브러리를 이용해 비밀번호 암호화 처리
            entity.setUserPassword(encodedPassword);

            UserEntity savedUser = userRepository.save(entity); // 위 과정을 저장!
            return "Success";
        }
        return "수정 전과 다른 비밀번호를 입력해 주세요.";
    }


    //이메일 중복확인
    public boolean isDuplicate(String email){
        if (email == null){
            throw new RuntimeException("이메일 값이 없습니다.");
        }
        return userRepository.existsByUserEmail(email);
    }


    // 로그인 검증(이메일,비밀번호) PART
    public LoginResponseDTO getByCredentials(final String email, final String rawPassword){

        // 입력한 이메일을 통해 회원정보 조회
        UserEntity originalUser = userRepository.findByUserEmail(email); // 이메일로 조회된 회원을 originUser 에 담음.


        // email 조회된 회원이 null 일때(없을 때)
        if(originalUser == null) {
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }

        // 비밀번호 검증
        if(!passwordEncoder.matches(rawPassword,originalUser.getUserPassword())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        //로그인 성공시 로그 출력
        log.info("{}님 로그인 성공",originalUser.getUserName());

        // 토큰 발급
        String token = tokenProvider.createToken(originalUser);
        return new LoginResponseDTO(originalUser, token);
    }



}


/**
 *  Service를 이해하기 위해 큰 틀
 *
 * 1. Client가 Request를 보낸다.(Ajax, Axios, fetch등..)
 * 2. Request URL에 알맞은 Controller가 수신 받는다. (@Controller , @RestController)
 * 3. Controller 는 넘어온 요청을 처리하기 위해 Service 를 호출한다.
 * 4. Service는 알맞은 정보를 가공하여 Controller에게 데이터를 넘긴다.
 * 5. Controller 는 Service 의 결과물을 Client 에게 전달해준다.
 *
 * 정리
 * Service가 알맞은 정보를 가공하는 과정을 '비즈니스 로직을 수행한다.' 라고 합니다.
 * Service가 비즈니스 로직을 수행하고 데이터베이스에 접근하는 DAO를 이용해서 결과값을 받아 옵니다.
 */