package com.example.projectpicker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 서버가 돌아가는지 확인하기 위한 컨트롤러
 */
@RestController
@Slf4j

public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<?>check(){
        log.info("서버가 정상 작동하고 있는 중입니다...");
        return ResponseEntity
                .ok()
                .body("서버가 정상 작동하고 있는 중입니다...");
    }

}
