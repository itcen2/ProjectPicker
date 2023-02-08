package com.example.projectpicker.postapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Setter @Getter @ToString
public class PageResponseDTO<T> {

    private int startPage;
    private int endPage;
    private int currentPage;
    private boolean prev;
    private boolean next;
    private int totalCount;

    // 페이지 개수
    private static final int PAGE_COUNT = 10;
    public PageResponseDTO(Page<T> pageData) {
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage = (int) (Math.ceil((double)currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        // 페이지 마지막 구간에서 endPage값 보정
        // 실제 끝페이지 숫자를 구함
        int realEnd = pageData.getTotalPages();

        // 언제보정? 마지막 구간에서만
        if (realEnd < endPage) this.endPage = realEnd;
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
