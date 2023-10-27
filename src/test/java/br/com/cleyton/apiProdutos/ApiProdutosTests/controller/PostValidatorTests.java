package br.com.cleyton.apiProdutos.ApiProdutosTests.controller;

import br.com.cleyton.apiProdutos.ApiProdutosTests.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.EntityAlreadyExistsException;
import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.MissingTermsException;
import br.com.cleyton.apiProdutos.ApiProdutosTests.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutosTests.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PostValidatorTests {

    @Mock
    private ProductRepository repository;
    private ProductDto productDto;
    private PostValidator postValidator;

    @BeforeEach
    public void init() {
        productDto = new ProductDto();
        productDto.setName("name");
        productDto.setDescription("desc");
        productDto.setPrice(90.10);
        productDto.setQuantity(20);
        productDto.setBarCode(8648237L);
        productDto.setManufacturingDate(LocalDateTime.now());
        productDto.setExpirationDate(LocalDateTime.now());

    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_NameIsMissing() {
        productDto.setName(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_DescriptionIsMissing(){
        productDto.setDescription(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_PriceIsMissing(){
        productDto.setPrice(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_QuantityIsMissing(){
        productDto.setQuantity(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_BarCodeIsMissing(){
        productDto.setBarCode(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_ManufacturingDateIsMissing(){
        productDto.setManufacturingDate(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsMissingTermsException_ExpirationDateIsMissing(){
        productDto.setExpirationDate(null);
        postValidator = new PostValidator(productDto, repository);
        assertThrows(MissingTermsException.class, () -> postValidator.postProductValidator()).printStackTrace();
    }

    @Test
    public void PostValidator_ThrowsEntityAlreadyExistsException_ProductAlreadyExists() {
        when(repository.findByBarCode(Mockito.anyLong())).thenReturn(Optional.of(new ProductModel()));
        assertThrows(EntityAlreadyExistsException.class, () ->  new PostValidator(productDto, repository).postProductValidator())
                .printStackTrace();
    }
}
