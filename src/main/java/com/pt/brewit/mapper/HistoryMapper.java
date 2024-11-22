package com.pt.brewit.mapper;

import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface HistoryMapper {
    public List<OrderDTO> selectAllOrders();
    public List<PaymentDTO> selectAllPayments();
    public void deleteOrderOne(int id);
}