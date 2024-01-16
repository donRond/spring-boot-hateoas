package br.com.rest.api.repositories;

import br.com.rest.api.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositry extends JpaRepository<ProductModel, UUID> {
}
