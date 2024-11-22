package com.pt.brewit.controller.admin;


import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.service.AdminService;
import com.pt.brewit.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j

@RequestMapping("/admin")
public class AdminHistoryController {
    private final HistoryService historyService;
    @GetMapping("/orders")
    public String Orders(Model model) {
        log.info("Orders Site Open !!");
        // 회원 목록
        List<OrderDTO> orders = historyService.getAllOrders();
        model.addAttribute("orders", orders);
        log.info("orders!!!!!!!!!!!!!!!!! : {}", orders);

        return "admin/orders";
    }
    // 주문 삭제 처리
    @PostMapping("orders/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        log.info("Deleting order with id: {}", id);
        HistoryService.deleteOrder(id);

        return "redirect:/admin/orders"; // 삭제 후 목록으로 리다이렉트
    }
    @GetMapping("/payments")
    public String Payments(Model model) {
        log.info("Payments Site Open !!");
        // 회원 목록
        List<PaymentDTO> payments = historyService.getAllPayments();
        model.addAttribute("orders", payments);
        log.info("payments!!!!!!!!!!!!!!!!! : {}", payments);

        return "admin/payments";
    }
}
