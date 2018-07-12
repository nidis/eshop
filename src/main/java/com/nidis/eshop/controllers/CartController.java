package com.nidis.eshop.controllers;

import com.nidis.eshop.assemblers.CartAssembler;
import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.models.Product;
import com.nidis.eshop.resources.CartResource;
import com.nidis.eshop.services.CartItemService;
import com.nidis.eshop.services.CartService;
import com.nidis.eshop.utils.CartStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cart", produces = MediaTypes.HAL_JSON_VALUE)
@Slf4j
public class CartController {
    private CartService cartService;
    private CartItemService cartItemService;
    private CartAssembler cartAssembler;

    private static final String CART_SESSION_ID = "cart_session_id";

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, CartAssembler cartAssembler) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.cartAssembler = cartAssembler;
    }

    @GetMapping(value = "/{cartId}", produces = {"application/hal+json"})
    public ResponseEntity<Resource<CartResource>> getCart(@PathVariable Long cartId) {
        Optional<Cart> cart = cartService.findById(cartId);

        final Resource<CartResource> wrapped = new Resource<>(cartAssembler.toResource(cart));
        return ResponseEntity.ok(wrapped);
    }

    @PostMapping("add")
    public void addProduct(@RequestParam(value = "product_id") Long productId, HttpServletResponse res, HttpServletRequest req) {
        String cartSessionId = getCookie(req, CART_SESSION_ID);
        String ipAddress = req.getLocalAddr();

        Cart cart = cartService.findBySessionIdAndIpAddress(cartSessionId, ipAddress);

        if (cart == null) {
            cart = new Cart();
            String sessionId = UUID.randomUUID().toString();
            setCookie(res, CART_SESSION_ID, sessionId, 1800);

            cart.setSessionId(sessionId);
            cart.setStatus(CartStatus.CREATED.name());
            cart.setIpAddress(ipAddress);

            cartService.save(cart);
        }

        Product product = new Product(productId);
        CartItem cartItem = new CartItem(cart, product, 1);
        cartItemService.save(cartItem);

        log.info("new cart created with cartId: {}, productId: {}", cart.getId(), product.getId());
    }

    private String getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
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

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                res.addCookie(cookie);
            }
        }
    }
}
