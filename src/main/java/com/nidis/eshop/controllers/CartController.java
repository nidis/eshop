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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<?> getCart(@PathVariable Long cartId, HttpServletRequest req) {
        String cartSessionId = getCookie(req, CART_SESSION_ID);
        String ipAddress = req.getLocalAddr();

        Optional<Cart> cart = cartService.findByIdAndSessionIdAndIpAddress(cartId, cartSessionId, ipAddress);

        if(!cart.isPresent()) {
            log.info("cart is not found");
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        final CartResource cartResource = cartAssembler.toResource(cart);

        log.info("cart with id: {} is found", cart.get().getId());
        return ResponseEntity.ok(cartResource);
    }

    @PostMapping("add")
    public ResponseEntity<?> addProduct(@RequestParam(value = "product_id") Long productId,
                                        HttpServletResponse res, HttpServletRequest req) {
        String cartSessionId = getCookie(req, CART_SESSION_ID);
        String ipAddress = req.getLocalAddr();

        Optional<Cart> cart = cartService.findBySessionIdAndIpAddress(cartSessionId, ipAddress);

        if (!cart.isPresent()) {
            cart = Optional.of(new Cart());
            String sessionId = UUID.randomUUID().toString();
            setCookie(res, CART_SESSION_ID, sessionId, 7);

            cart.get().setSessionId(sessionId);
            cart.get().setStatus(CartStatus.CREATED.name());
            cart.get().setIpAddress(ipAddress);

            cartService.save(cart.get());
        }

        Product product = new Product(productId);
        CartItem cartItem = new CartItem(cart.get().getId(), product, 1);
        cartItemService.save(cartItem);

        log.info("new cart created with cartId: {}, productId: {}", cart.get().getId(), product.getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cartId}/", produces = {"application/hal+json"})
    public ResponseEntity<?> clearCart(@PathVariable Long cartId, @RequestParam(value = "item_id") Optional<Long> itemId, HttpServletRequest req) {
        String cartSessionId = getCookie(req, CART_SESSION_ID);
        String ipAddress = req.getLocalAddr();

        Optional<Cart> cart = cartService.findByIdAndSessionIdAndIpAddress(cartId, cartSessionId, ipAddress);

        if (cartItemService.clearAllItems(cart) || cartItemService.clearItem(itemId, cartId)) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
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

    private void setCookie(HttpServletResponse res, String name, String value, int maxDays) {
        Cookie cookieUuid = new Cookie(name, value);
        cookieUuid.setMaxAge(maxDays * 24 * 3_600_000);
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

    //todo: create discovert ms-service and add client/server logic
}
