package br.com.spring.velocity.example.controllers;

import br.com.spring.velocity.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("customers")
    public ModelAndView getCustomer() {
        final var modelAndView = new ModelAndView("customer");

        modelAndView.addObject("customers", customerService.getAllCustomers());

        return modelAndView;
    }
}
