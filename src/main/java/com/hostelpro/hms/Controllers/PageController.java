package com.hostelpro.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class PageController {
//    @GetMapping
//    public String index() {
//        return "index";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("admin-dashboard")
    public String admin_dashboard() {
        return "/admin/admin-dashboard";
    }

    @GetMapping("hostel-form")
    public String home() {
        return "/admin/hostel-form";
    }

    @GetMapping("warden-form")
    public String wardenForm() {
        return "/admin/warden-form";
    }

    @GetMapping("/warden-dashboard")
    public String warden_dashboard() {
        return "/warden/warden-dashboard";
    }

    @GetMapping("/students")
    public String students() {
        return "/warden/students";
    }
}
