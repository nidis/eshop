package com.nidis.eshop.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nidis.eshop.controllers.CartController;
import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.models.Customer;
import com.nidis.eshop.models.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Relation(value = "cart", collectionRelation = "carts")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CartResource extends ResourceSupport {
    //private Long id;
    private Customer customer;
    private List<CartItem> cartItems;

    public CartResource(Optional<Cart> cart) {
        super();

        if(cart.isPresent()) {
            //this.id = cart.get().getId();
            this.customer = cart.get().getCustomer();
            this.cartItems = cart.get().getCartItems();
        }
    }

    /*@Override
    public Resource toResource(Cart cart) {
        Resource cartResource = new Resource(cart);
        Link selfLink = linkTo(methodOn(CartController.class).getCart(cart.getId()))
                .withSelfRel();

        cartResource.add(selfLink);

        return cartResource;
    }*/
}
