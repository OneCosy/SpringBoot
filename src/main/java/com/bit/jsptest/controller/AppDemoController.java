package com.bit.jsptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.jsptest.model.service.AppDemoService;

@Controller
@RequestMapping("/app")
public class AppDemoController {
    @Autowired
    private AppDemoService appDemoService;

    //getmapping
    @GetMapping("emp")
    public String empViewApp(Model model) {
        model.addAttribute("empList", appDemoService.selectEmpAll());
        return "appViews/empView";
    }

    @GetMapping("dept")
    public String deptViewApp(Model model) {
        model.addAttribute("deptList", appDemoService.selectDeptAll());
        return "appViews/deptView";
    }

}