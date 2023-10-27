package br.com.cleyton.apiProdutos.ApiProdutosTests.repository.product;

import br.com.cleyton.apiProdutos.ApiProdutosTests.model.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findByBarCode(Long barCode);
}
