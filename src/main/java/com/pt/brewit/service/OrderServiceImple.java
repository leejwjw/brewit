package com.pt.brewit.service;

import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImple implements OrderService {

        private final OrderMapper orderMapper;

        @Override
        public void saveOrder(OrderDTO orderDTO) {
            orderMapper.insertOrder(orderDTO);
        }

        public void savePayment(OrderDTO orderDTO) {
            orderMapper.insertPayment(orderDTO);
        }

}
