package com.pt.brewit.mapper;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventProductMapper {
    List<EventProductDTO> getEventProductInfo();
    void insertEventProductOne(EventProductDTO eventProduct);
    EventProductDTO getFindProductForId(int term_item_id);
    List<EventProductDTO> getEventProduct(MemberDTO logged_member);


    // 페이징
    List<EventProductDTO> selectSubscriptions(Pager pager); // 페이징된 상품 리스트
    int countSubscriptions(Pager pager); // 총 상품 개수

    //삭제
    void deleteProductById(int id);


}
