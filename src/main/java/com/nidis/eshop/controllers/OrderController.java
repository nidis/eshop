package com.nidis.eshop.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    /*
    todo: create order

    /cart/{cartid}/checkout


      ----after authorization
    order                       /order?id=1                         GET
        add delivery address
        choose payment method
        cart status
    confirm order               /order?id=1                         POST
    confirm payment             /payment/order?id=1                 POST
    receive email with order    /email?order=1                      POST
     */
}
