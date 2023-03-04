package br.tec.didiproject.queueserviceapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public ModelAndView redirectToSwagger() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}