package com.nidis.eshop.resources;

import com.nidis.eshop.models.Cart;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

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
