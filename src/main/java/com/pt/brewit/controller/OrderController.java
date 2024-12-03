package com.pt.brewit.controller;

import com.pt.brewit.dto.OrderDTO;
import com.pt.brewit.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/save")
    @ResponseBody
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // 요청 데이터 확인
            log.info("받은 데이터: {}", orderDTO);

            // 주문 처리 로직 실행
            orderService.saveOrder(orderDTO);
            return ResponseEntity.ok("주문이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            // 오류 로그 출력
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("/auth/check")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                !"anonymousUser".equals(authentication.getPrincipal());
    }
}
