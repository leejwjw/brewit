package com.pt.brewit.mapper;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventProductMapper {
    List<EventProductDTO> getEventProductForId();
    void insertEventProductOne(EventProductDTO eventProduct);
    EventProductDTO getFindProduct(int term_item_id);

    List<EventProductDTO> getEventProduct(MemberDTO logged_member);
}
