package com.pt.brewit.service;


import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.Pager;

import java.util.List;

public interface EventProductService {
    List<EventProductDTO> getEventProducts();
    void insertEventProduct(EventProductDTO eventProduct);
    EventProductDTO getFindProductId(int term_item_id);

    //구독상품 조회
    List<EventProductDTO> getEventProductList(MemberDTO logged_member);
    //파일조회
    public String getFullPath(String filename);
    // 상품 삭제
    public void deleteProduct(int id);


    //페이징 처리
    List<EventProductDTO> getSubscriptions(Pager pager); // 페이징된 구독 상품 리스트 가져오기
    int getTotalCount(Pager pager); // 총 상품 개수
}
