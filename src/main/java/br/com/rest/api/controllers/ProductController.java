package br.com.rest.api.controllers;

import br.com.rest.api.dto.ProductRecordDto;
import br.com.rest.api.models.ProductModel;
import br.com.rest.api.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> listProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findOneProduct(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(value = "id") UUID id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
