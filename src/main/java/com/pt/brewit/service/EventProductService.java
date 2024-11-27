package com.pt.brewit.service;


import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.UserDTO;

import java.util.List;

public interface EventProductService {
    List<EventProductDTO> getEventProducts();
    void insertEventProduct(EventProductDTO eventProduct);
    EventProductDTO getFindProductId(int term_item_id);

    //구독상품 조회
    List<EventProductDTO> getEventProductList(MemberDTO logged_member);
    //파일조회
    public String getFullPath(String filename);
}
