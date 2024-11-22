package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.dto.PaymentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HistoryService {

    //전체 주문내역 조회
    public List<OrderDTO> getAllOrders();
    public List<PaymentDTO> getAllPayments();
    public void deleteOrder(int id);
}
