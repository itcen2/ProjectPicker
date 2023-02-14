package com.example.projectpicker.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// 접속시 보여줄 페이지 리스트 수 (클라이언트 ---> 서버)
@Getter
@AllArgsConstructor
@Builder @ToString
public class PageRequestDTO {

    private int page; // 요청한 페이지 번호
    private int sizePerPage; // 한 페이지에 보여줄 데이터 수

    /*
     *  초기 요청시에 사용할 데이터 세팅
     */
    public PageRequestDTO(){
        this.page = 1; // 들어가자 마자 페이지 1페이지가 뜨기 때문에
        this.sizePerPage = 10; // 아래 페이징은 10개씩
    }
    public void setPage(int page){
        // 만약 0 미만 또는 최대 값 초과 했을 시 --> 1페이지로 반환
        if(page < 1 || page > Integer.MAX_VALUE) {
            this.page = 1;
            return;
        }
        // 그게 아니라면 페이지 클릭시, 클릭한 페이지로~
        this.page = page;
    }

    /**
     *  한페이지에 게시글 리스트 몇개 보여줄건지 설정 part
     */
    public void setSizePerPage(int sizePerPage){
        if(sizePerPage < 10 || sizePerPage > 100){
            this.sizePerPage = 15;
            return;
        }
        this.sizePerPage = sizePerPage;
    }

}
