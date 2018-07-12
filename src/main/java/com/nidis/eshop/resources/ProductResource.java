package com.nidis.eshop.resources;

import com.nidis.eshop.models.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.List;

@Relation(value = "product", collectionRelation = "products")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ProductResource extends ResourceSupport {
    private List<Product> products;

    public ProductResource(List<Product> products) {
        super();
    }
}
