package com.pt.brewit.mapper;

import com.pt.brewit.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insertOrder(OrderDTO orderDTO);
}
