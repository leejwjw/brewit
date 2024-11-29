package com.pt.brewit.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == 404) {
                return "error/404";
            } else if (statusCode == 500) {
                return "error/500";
            } else if (statusCode == 403) {
                return "error/403";
            } else if (statusCode == 405) {
                return "error/405";
            }
        }
            return "error/default"; // 기타 에러 페이지
    }
}