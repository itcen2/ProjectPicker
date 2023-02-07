package com.example.projectpicker.admin.config;

import com.example.projectpicker.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    // 패스워드 인코딩 클래스를 등록
    // <bean id=? class=? />
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 시큐리티 빌더
        http.cors() // 크로스오리진정책
                .and()
                .csrf() // CSRF정책
                .disable() // 사용 안함
                .httpBasic().disable() // 기본 시큐리티 인증 해제, 토큰인증 쓸꺼니까
                // 세션 기반 인증 안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 인증 요청중에서 '/'경로랑 '/api/auth'로 시작되는 경로는 인증하지 않고 모두 허용
                .authorizeRequests().antMatchers("/admin", "/admin/signUp").permitAll()
                // 그 외의 모든 경로는 인증을 거쳐야함.
                .anyRequest().authenticated();

        // 토큰 인증 필터 등록
        http.addFilterAfter(
                jwtAuthFilter,
                CorsFilter.class // import 주의 : 스프링껄로
        );

        return http.build();
    }

}