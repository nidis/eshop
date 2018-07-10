package com.nidis.eshop.controllers;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.models.Product;
import com.nidis.eshop.services.CartItemService;
import com.nidis.eshop.services.CartService;
import com.nidis.eshop.utils.CartStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cart", produces = MediaTypes.HAL_JSON_VALUE)
@Slf4j
public class CartController {
    private CartService cartService;
    private CartItemService cartItemService;
    private static final String CART_UUID = "cart_uuid";

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }


    @PostMapping("add")
    public void addProduct(@RequestParam(value = "product_id", required = true) Long productId, HttpServletResponse res, HttpServletRequest req) {
        clearCookies(req, res);

        Cart cart = new Cart();
        Product product = new Product(productId);

        String uuid = UUID.randomUUID().toString();

        if (getCookie(req, CART_UUID) == null) {
            setCookie(res, CART_UUID, uuid, 1800);

            cart.setSessionId(uuid);
            cart.setStatus(CartStatus.CREATED.name());
            cartService.save(cart);
        } else {
            cart = cartService.findBySessionId(getCookie(req, CART_UUID));
        }

        CartItem cartItem = new CartItem(cart, product, 1);
        cartItemService.save(cartItem);
        log.info("new cart created with cartId: {}, productId: {}", cart.getId(), product.getId());
    }

    private String getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    private void setCookie(HttpServletResponse res, String name, String value, int maxAge) {
        Cookie cookieUuid = new Cookie(name, value);
        cookieUuid.setMaxAge(maxAge);
        res.addCookie(cookieUuid);
    }

    private void clearCookies(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();

        for(Cookie cookie: cookies) {
            cookie.setValue(null);
            res.addCookie(cookie);
        }
    }
}
