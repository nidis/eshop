package com.nidis.eshop.assemblers;

import com.nidis.eshop.controllers.CartController;
import com.nidis.eshop.models.Cart;
import com.nidis.eshop.resources.CartResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CartAssembler extends ResourceAssemblerSupport<Optional<Cart>, CartResource> {

    public CartAssembler() {
        super(Cart.class, CartResource.class);
    }

    @Override
    public CartResource toResource(Optional<Cart> cart) {
        CartResource cartResource = new CartResource(cart);

        cart.ifPresent(cartResource::setCart);

        Link selfLink = linkTo(methodOn(CartController.class).getCart(cart.get().getId(), null)).withSelfRel();

        cartResource.add(selfLink);

        return  cartResource;
    }
}
