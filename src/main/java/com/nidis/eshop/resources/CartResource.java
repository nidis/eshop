package com.nidis.eshop.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.models.Customer;
import com.nidis.eshop.models.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Relation(value = "cart", collectionRelation = "carts")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CartResource extends ResourceSupport {
    private Customer customer;
    private List<CartItem> cartItems;
    private List<Product> products;

    @JsonCreator
    public CartResource(Optional<Cart> cart) {
        super();

        if(cart.isPresent()) {
            this.customer = cart.get().getCustomer();
            this.cartItems = cart.get().getCartItems();
            this.products = cart.get().getCartItems().stream().map(CartItem::getProduct).collect(Collectors.toList());
        }
    }
}
