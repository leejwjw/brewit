package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pt.brewit.mapper.AdminMapper;

import java.util.List;

@Service  // Service 기능을 하는 스프링빈이다~
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HistoryServiceImpl implements HistoryService {
    private final HistoryMapper historyMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO>  orders = historyMapper.selectAllOrders();
        return orders;
    }
    @Override
    public List<PaymentDTO> getAllPayments() {
        List<PaymentDTO>  payments = historyMapper.selectAllPayments();
        return payments;
    }
    @Override
    public void deleteOrder(int id) {
        historyMapper.deleteOrderOne(id);
    }
}