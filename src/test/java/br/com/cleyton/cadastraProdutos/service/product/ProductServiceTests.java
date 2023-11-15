package br.com.cleyton.cadastraProdutos.service.product;

import br.com.cleyton.cadastraProdutos.dto.product.ProductDto;
import br.com.cleyton.cadastraProdutos.model.product.ProductModel;
import br.com.cleyton.cadastraProdutos.repository.product.ProductRepository;
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

import java.time.LocalDate;
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
        product = new ProductModel();
        product.setBarCode(112312534L);
        product.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        product.setDescription("computador bacana");
        product.setExpirationDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        product.setId(1);
        product.setManufacturingDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        product.setName("computador");
        product.setPrice(10.0);
        product.setQuantity(10);
        productDto = new ProductDto();
        productDto.setBarCode(112312534L);
        productDto.setDescription("computador bacana");
        productDto.setExpirationDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        productDto.setManufacturingDate(LocalDate.of(2023, 1, 1).atStartOfDay());
        productDto.setName("computador");
        productDto.setPrice(10.0);
        productDto.setQuantity(10);
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
