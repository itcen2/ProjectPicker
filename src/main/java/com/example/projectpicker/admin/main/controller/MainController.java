package com.example.projectpicker.admin.main.controller;

import com.example.projectpicker.admin.main.dto.MainPostDTO;
import com.example.projectpicker.admin.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/main")

@CrossOrigin
public class MainController {

    @Autowired
    private final MainService mainService;

    @GetMapping
    public ResponseEntity<?> AllList(){
        List<MainPostDTO> allList = mainService.findAllList();

        return ResponseEntity.ok()
                .body(allList);
    }
    /*@GetMapping("/deny")
    public ResponseEntity<?> DenyList(){
        List<MainPostDTO> allList = mainService.findDenyList();

        return ResponseEntity.ok()
                .body(allList);
    }*/
    @GetMapping("/allow")
    public ResponseEntity<?> AllowList(){
        List<MainPostDTO> allList = mainService.findAllowList();

        return ResponseEntity.ok()
                .body(allList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyItem(@PathVariable("id") String postId, @Validated@RequestBody MainPostDTO mainPostDTO){
        List<MainPostDTO> postDTO = mainService.modifyItem(postId, mainPostDTO);

        return ResponseEntity.ok()
                .body(postDTO);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") String postId){
        List<MainPostDTO> postDTOS = mainService.deleteItem(postId);

        return ResponseEntity.ok()
                .body(postDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> postDetail(@PathVariable("id") String postId){
        MainPostDTO detail = mainService.postDetail(postId);

        return ResponseEntity.ok()
                .body(detail);
    }
}