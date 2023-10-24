package br.com.cleyton.apiProdutos.ApiProdutos.model.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findByBarCode(Long barCode);
    void deleteByBarCode(Long barCode);
}
