package com.pt.brewit.mapper;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventProductMapper {
    List<EventProductDTO> getEventProductInfo();
    void insertEventProductOne(EventProductDTO eventProduct);
    EventProductDTO getFindProductForId(int term_item_id);
    List<EventProductDTO> getEventProduct(MemberDTO logged_member);

    //삭제
    void deleteProductById(int id);
}
