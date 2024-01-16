package br.com.rest.api.services;

import br.com.rest.api.dto.ProductRecordDto;
import br.com.rest.api.exceptions.ProductNotFoundException;
import br.com.rest.api.factories.ProductFactory;
import br.com.rest.api.models.ProductModel;
import br.com.rest.api.repositories.ProductRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepositry productRepositry;
    @Autowired
    ProductFactory productFactory;
    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        var productModel = productFactory.makeProductModel(productRecordDto);
        return productRepositry.save(productModel);
    }

    public List<ProductModel> findAll() {
        return productFactory.makeListProductModel(productRepositry.findAll());
    }

    public ProductModel findOne(UUID id) {
        Optional<ProductModel> optionalProduct = productRepositry.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        }
        return optionalProduct.get();
    }

    public ProductModel update(UUID id, ProductRecordDto productRecordDto) {
        var productModel = this.findOne(id);
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepositry.save(productModel);
    }

    public void delete(UUID id) {
        var productModel = this.findOne(id);
        productRepositry.delete(productModel);
    }
}
