package com.pt.brewit.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j

@RequestMapping("/admin")
public class AdminMemberController {
    @GetMapping("/allMember")
    public String allMember() {
        log.info("allMember Site Open !!");
        return "../templates/admin/allMember";
    }
}
