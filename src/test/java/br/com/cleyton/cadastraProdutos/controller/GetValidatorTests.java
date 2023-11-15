package br.com.cleyton.cadastraProdutos.controller;

import br.com.cleyton.cadastraProdutos.dto.product.ProductDto;
import br.com.cleyton.cadastraProdutos.exception.EmptyPageException;
import br.com.cleyton.cadastraProdutos.exception.EntityNotFoundException;
import br.com.cleyton.cadastraProdutos.model.product.ProductModel;
import br.com.cleyton.cadastraProdutos.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class GetValidatorTests {

    @Mock
    private ProductRepository repository;

    private ProductModel productModel;
    private ProductDto productDto;
    private GetValidator getValidator;

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
    public void GetValidator_ThrowsEntityNotFoundException() {
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        getValidator = new GetValidator(1,repository);

        assertThrows(EntityNotFoundException.class, () -> getValidator.getProductValidator()).printStackTrace();
    }

    @Test
    public void GetValidator_ThrowsEntityNotFoundException_NoEntitiesCreatedYet() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        getValidator = new GetValidator(repository);

        assertThrows(EntityNotFoundException.class, () -> getValidator.getAllProductsValidator()).printStackTrace();
    }

    @Test
    public void GetValidator_ThrowsEmptyPageException() {
        when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        getValidator = new GetValidator(repository, 0);

        assertThrows(EmptyPageException.class, () -> getValidator.getAllProductsPageableValidator()).printStackTrace();
    }
}
