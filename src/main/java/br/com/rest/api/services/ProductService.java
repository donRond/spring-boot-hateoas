package br.com.rest.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.api.dto.ProductRecordDto;
import br.com.rest.api.exceptions.ProductNotFoundException;
import br.com.rest.api.factories.ProductFactory;
import br.com.rest.api.models.ProductModel;
import br.com.rest.api.repositories.ProductRepositry;

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
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        }
        return productFactory.makeProductModel(optionalProduct.get());
    }

    public ProductModel update(UUID id, ProductRecordDto productRecordDto) {
        var productModel = this.findOne(id);
        return productRepositry.save(productFactory.makeProductModel(productRecordDto, productModel));
    }

    public void delete(UUID id) {
        var productModel = this.findOne(id);
        productRepositry.delete(productModel);
    }
}
