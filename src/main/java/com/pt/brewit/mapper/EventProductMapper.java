package com.pt.brewit.mapper;

import com.pt.brewit.dto.EventProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventProductMapper {
    List<EventProductDTO> getEventProductForId();
}
