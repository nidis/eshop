package com.nidis.eshop.assemblers;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.resources.CartResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartAssembler extends ResourceAssemblerSupport<Optional<Cart>, CartResource> {

    public CartAssembler() {
        super(Cart.class, CartResource.class);
    }

    @Override
    public CartResource toResource(Optional<Cart> cart) {
        CartResource cartResource = new CartResource(cart);

        if(cart.isPresent()) {
            cartResource.setCart(cart.get());
        }

        return  cartResource;
    }
}
