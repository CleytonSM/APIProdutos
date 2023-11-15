package br.com.cleyton.cadastraProdutos.repository.product;

import br.com.cleyton.cadastraProdutos.model.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findByBarCode(Long barCode);
}
