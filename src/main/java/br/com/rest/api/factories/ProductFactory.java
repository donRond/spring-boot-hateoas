package br.com.rest.api.factories;

import br.com.rest.api.controllers.ProductController;
import br.com.rest.api.dto.ProductRecordDto;
import br.com.rest.api.exceptions.ProductNotFoundException;
import br.com.rest.api.models.ProductModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

@Component
public class ProductFactory {
    public ProductModel makeProductModel(ProductRecordDto productRecordDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productModel;
    }

    public List<ProductModel> makeListProductModel(List<ProductModel> productModels){
        if(productModels.isEmpty()){
            throw new ProductNotFoundException("Products Not Found");
        }
        for(ProductModel product : productModels) {
            UUID id = product.getId();
            product.add(linkTo(methodOn(ProductController.class).findOneProduct(id)).withSelfRel());
        }
        return productModels;
    }
}
