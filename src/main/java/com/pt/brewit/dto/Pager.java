package com.pt.brewit.dto;

import lombok.Data;

@Data
public class Pager {
    private int page;    // 현재 페이지 번호
    private int size;    // 한페이지당 보여줄 글의 개수

    private String searchType; // 검색 타입   t, c, w, tw, tc, twc
    private String keyword;    // 검색 키워드

    public Pager(){ this(1, 12); }//한페이지에 `12`개씩
    public Pager(int page, int size) {
        this.page = page;
        this.size = size;
    }
    public int getOffset() {
        return (page - 1) * size;
    }
    public String[] getSearchTypeArr() {
        return searchType == null? new String[] {} : searchType.split("");
    }


}
