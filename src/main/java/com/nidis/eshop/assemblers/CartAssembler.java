package com.nidis.eshop.assemblers;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.resources.CartResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartAssembler extends ResourceAssemblerSupport<Optional<Cart>, CartResource> {

    public CartAssembler() {
        super(Cart.class, CartResource.class);
    }

    @Override
    public CartResource toResource(Optional<Cart> cart) {
        CartResource cartResource = new CartResource(cart);

        if(cart.isPresent()) {
            cartResource.setCustomer(cart.get().getCustomer());
            cartResource.setCartItems(cart.get().getCartItems());
            cartResource.setProducts(cart.get().getCartItems().stream().map(CartItem::getProduct).collect(Collectors.toList()));
        }

        return  cartResource;
    }
}
