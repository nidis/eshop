package com.nidis.eshop.assemblers;

import com.nidis.eshop.models.Product;
import com.nidis.eshop.resources.ProductResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductAssembler extends ResourceAssemblerSupport<List<Product>, ProductResource> {

    public ProductAssembler() {
        super(Product.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(List<Product> products) {
        ProductResource resource = new ProductResource(products);

        resource.setProducts(products);

        return resource;
    }
}
