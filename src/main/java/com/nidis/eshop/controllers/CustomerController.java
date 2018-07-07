package com.nidis.eshop.controllers;

import com.nidis.eshop.models.Customer;
import com.nidis.eshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = MediaTypes.HAL_JSON_VALUE)
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public void findCustomers() {

        List<Customer> customers = customerService.findAll();
    }
}
