package br.com.spring.velocity.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class HelloController {
    @GetMapping()
    public ModelAndView hello() {
        final var modelAndView = new ModelAndView("hello");

        modelAndView.addObject("helloWorld", "Velocity!!!");

        return modelAndView;
    }
}
