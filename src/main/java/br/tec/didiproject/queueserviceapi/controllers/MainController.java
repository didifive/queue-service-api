package br.tec.didiproject.queueserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @SuppressWarnings("SameReturnValue")
    @GetMapping
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}