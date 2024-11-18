package com.pt.brewit.controller.admin;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.service.AdminService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        log.info("GET /board/list - members : {}", members);


        return "../templates/admin/allMember";
    }
}
