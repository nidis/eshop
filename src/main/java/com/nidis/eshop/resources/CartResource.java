package com.nidis.eshop.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@Relation(value = "cart", collectionRelation = "carts")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CartResource extends ResourceSupport {
    private Cart cart;

    public CartResource(Optional<Cart> cart) {
        super();
    }
}
