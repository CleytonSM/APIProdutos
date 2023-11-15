package br.com.cleyton.cadastraProdutos.repository.product;

import br.com.cleyton.cadastraProdutos.model.product.ProductModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;
    private ProductModel product;
    private ProductModel product2;

    @BeforeEach
    public void init() {
        product = ProductModel.builder().name("computador").description("computador bacana")
                .price(2599.99).quantity(20).barCode(123456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
        product2 = ProductModel.builder().name("celular").description("celular bacana")
                .price(3599.99).quantity(30).barCode(323456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
    }
    @Test
    public void ProductRepository_SaveAll_ReturnSavedProduct() {
        ProductModel savedProduct = repository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepository_FindAll_ReturnsMoreThanOneProduct() {
        repository.save(product);
        repository.save(product2);

        List<ProductModel> productList = repository.findAll();

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(2);
    }

    @Test
    public void ProductRepository_FindById_ReturnsProduct() {
        repository.save(product);
        ProductModel foundProduct = repository.findById(product.getId()).get();

        Assertions.assertThat(foundProduct).isNotNull();
    }

    @Test
    public void ProductRepository_FindById_ReturnsEmpty() {
        double id = Math.random() * 10;
        Optional<ProductModel> foundProduct = repository.findById((int) id);

        Assertions.assertThat(foundProduct).isEmpty();
    }

    @Test
    public void ProductRepository_FindByBarCode_ReturnsProduct() {
        repository.save(product);
        ProductModel foundProduct = repository.findByBarCode(product.getBarCode()).get();

        Assertions.assertThat(foundProduct).isNotNull();
    }

    @Test
    public void ProductRepository_FindByBarCode_ReturnsEmpty() {
        Long barCode = (long) (Math.random() * 10000000L);

        Optional<ProductModel> foundProduct = repository.findByBarCode(barCode);

        Assertions.assertThat(foundProduct.isEmpty()).isTrue();
    }

    @Test
    public void ProductRepository_DeleteById_ReturnsEmptyProduct() {
        repository.save(product);
        repository.deleteById(product.getId());
        Optional<ProductModel> optionalProduct = repository.findById(product.getId());

        Assertions.assertThat(optionalProduct).isEmpty();
    }

    @Test
    public void ProductRepository_Delete_ReturnsEmptyProduct() {
        repository.save(product);
        repository.delete(product);
        Optional<ProductModel> optionalProduct = repository.findById(product.getId());

        Assertions.assertThat(optionalProduct).isEmpty();
    }

    @Test
    public void ProductRepository_Update_ReturnsUpdatedProduct() {
        repository.save(product);
        ProductModel foundProduct = repository.findById(product.getId()).get();
        foundProduct.setName("celular");
        foundProduct.setPrice(3000.00);

        ProductModel updatedProduct = repository.save(foundProduct);

        Assertions.assertThat(updatedProduct.getName()).isNotNull();
        Assertions.assertThat(updatedProduct.getPrice()).isNotNull();
        Assertions.assertThat(updatedProduct).isNotNull();
    }
}
