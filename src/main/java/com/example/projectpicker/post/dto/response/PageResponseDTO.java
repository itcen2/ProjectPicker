package com.example.projectpicker.post.dto.response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

// 클라이언트에게 응답할 페이지 정보.
/* 예시
    pageInfo : {
        "startPage" : 41,
        "endPage" : 50,
        "currentPage" : 49,
        "prev" : true,
        "next" : false,
        "totalCount": 500
    }
 */
@Setter @Getter @ToString
public class PageResponseDTO<T> {
    private int startPage; // 시작 페이지
    private int endPage; // 마지막 페이지
    private int currentPage; // 현재 페이지
    private int sizePerPage;
    private boolean prev; // 이전
    private boolean next; // 다음
    private int totalCount; // 전체 페이지 수


    //페이지 개수 설정
    private static final int PAGE_COUNT = 10;

    public PageResponseDTO(Page<T> pageData){
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage = (int) (Math.ceil(currentPage) / (PAGE_COUNT) + PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        // 페이지 마지막 구간에 endPage 값 보정
        // 실제 끝 페이지 숫자를 구함

        int realEnd = pageData.getTotalPages();

        // 마지막 구간에서만 페이지 보정하면 됨.
        if(realEnd  < this.endPage) this.endPage = realEnd;
        this.prev = startPage > 1;
        this.next = endPage < realEnd;

    }
}