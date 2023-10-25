package br.com.cleyton.apiProdutos.ApiProdutos.service;

import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.repository.product.ProductRepository;
import br.com.cleyton.apiProdutos.ApiProdutos.service.product.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;
    private ProductModel product;
    private ProductDto productDto;

    @BeforeEach
    public void init() {
        product = ProductModel.builder().name("computador").description("computador bacana")
                .price(2599.99).quantity(20).barCode(123456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
        productDto = ProductDto.builder().name("computador").description("computador bacana")
                .price(2599.99).quantity(20).barCode(123456634123424L).manufacturingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now()).build();
    }
    @Test
    public void ProductService_CreateProduct_ReturnsProduct() {
        when(repository.save(Mockito.any(ProductModel.class))).thenReturn(product);
        ProductModel savedProduct = service.createProduct(productDto);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductService_GetById_ReturnProduct() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(product));

        ProductModel foundProduct = service.getProductById(1);

        Assertions.assertThat(foundProduct).isNotNull();
    }

    @Test
    public void ProductService_GetAllPageable_ReturnsProducts() {
        Page<ProductModel> allProducts = Mockito.mock(Page.class);

        when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(allProducts);

        Stream<ProductModel> savedProducts = service.getAllProductsPageable(0);

        Assertions.assertThat(savedProducts).isNotNull();
    }

    @Test
    public void ProductService_GetAllProducts_ReturnsProducts() {
        List<ProductModel> productsList = Mockito.mock(List.class);

        when(repository.findAll()).thenReturn(productsList);

        List<ProductModel> savedProducts = service.getAllProducts();

        Assertions.assertThat(savedProducts).isNotNull();
    }

    @Test
    public void ProductService_FullUpdateById_ReturnsUpdatedProduct() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(product));
        when(repository.save(Mockito.any(ProductModel.class))).thenReturn(product);

        ProductModel updatedProduct = service.fullProductUpdateById(1, productDto);

        Assertions.assertThat(updatedProduct).isNotNull();
    }

    @Test
    public void ProductService_PartialUpdateById_ReturnsUpdatedProduct() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(product));
        when(repository.save(Mockito.any(ProductModel.class))).thenReturn(product);
        ProductModel updatedProduct = service.partialProductUpdateById(1, productDto);

        Assertions.assertThat(updatedProduct).isNotNull();
    }

    @Test
    public void ProductService_FullUpdateByBarCode_ReturnsUpdatedProduct() {
        when(repository.findByBarCode(123456634123424L)).thenReturn(Optional.ofNullable(product));
        when(repository.save(Mockito.any(ProductModel.class))).thenReturn(product);

        ProductModel updatedProduct = service.fullProductUpdateByBarCode(123456634123424L, productDto);

        Assertions.assertThat(updatedProduct).isNotNull();
    }

    @Test
    public void ProductService_PartialUpdateByBarCode_ReturnsUpdatedProduct() {
        when(repository.findByBarCode(123456634123424L)).thenReturn(Optional.ofNullable(product));
        when(repository.save(Mockito.any(ProductModel.class))).thenReturn(product);

        ProductModel updatedProduct = service.partialProductUpdateByBarCode(123456634123424L, productDto);

        Assertions.assertThat(updatedProduct).isNotNull();
    }

    @Test
    public void ProductService_DeleteById_ReturnsProduct() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(product));

        assertAll(() -> service.deleteProductById(1));
    }

    @Test
    public void ProductService_DeleteByBarCode_ReturnsProduct() {
        when(repository.findByBarCode(123456634123424L)).thenReturn(Optional.ofNullable(product));

        assertAll(() -> service.deleteProductByBarCode(123456634123424L));
    }
}
