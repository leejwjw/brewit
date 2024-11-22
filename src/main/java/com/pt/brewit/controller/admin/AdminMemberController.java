package com.pt.brewit.controller.admin;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {
    private final AdminService adminService;

    @GetMapping("/allMember")
    public String allMember(Model model) {
        log.info("allMember Site Open !!");
        // 회원 목록
        List<MemberDTO> members = adminService.getMemberList();
        model.addAttribute("members", members);
        log.info("GET /admin/allMember - members : {}", members);

        return "admin/allMember";
    }
    @GetMapping("/confirmSeller")
    public String getConfirmSeller(Model model) {
        log.info("confirmSeller Site Open !!");
        // 회원 목록
        List<MemberDTO> members = adminService.getSellerConfrimList();
        model.addAttribute("members", members);
        log.info("GET /admin/allMember - members : {}", members);

        return "admin/confirmSeller";
    }
    // 승인 처리
    @PostMapping("confirmSeller/confirm/{id}")
    public String confirmMember(@PathVariable("id") int id) {
        log.info("confrim member with id: {}", id);
        log.info("confrim!!!");
        adminService.confirmSeller(id);

        return "redirect:/admin/confirmSeller"; // 삭제 후 목록으로 리다이렉트
    }

    // 수정 페이지로 이동
    @GetMapping("allMember/edit/{id}")
    public String editMember(@PathVariable("id") int id, Model model) {
        log.info("Editing member with id: {}", id);
        MemberDTO member = adminService.getMemberById(id);
        model.addAttribute("member", member);
        return "admin/editMember";
    }

    @PostMapping("allMember/editMember/{id}")
    public String updateMember(@PathVariable("id") int id, @ModelAttribute MemberDTO member) {
        //member 객체에 수정된 값 저장
        log.info("Updating member with member: {}", member);
        adminService.updateMember(id, member); // 서비스 레이어에서 회원 정보 업데이트
        return "redirect:/admin/allMember"; // 수정 후 회원 목록 페이지로 리다이렉트
    }

    // 삭제 처리
    @PostMapping("allMember/delete/{id}")
    public String deleteMember(@PathVariable("id") int id) {
        log.info("Deleting member with id: {}", id);
        adminService.deleteMember(id);

        return "redirect:/admin/allMember"; // 삭제 후 목록으로 리다이렉트
    }



}
