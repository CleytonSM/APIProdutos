package br.com.cleyton.cadastraProdutos.controller;

import br.com.cleyton.cadastraProdutos.dto.product.ProductDto;
import br.com.cleyton.cadastraProdutos.exception.EmptyRequestBodyException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PatchValidatorTests {

    @Mock
    private ProductRepository repository;
    private ProductDto productDto;
    private PatchValidator patchValidator;

    @BeforeEach
    public void init() {
        productDto = new ProductDto();
    }

    @Test
    public void PatchValidator_EntityNotFoundException_Id() {
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        patchValidator = new PatchValidator(repository, 1, productDto);

        assertThrows(EntityNotFoundException.class, () -> patchValidator.patchProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PatchValidator_EntityNotFoundException_BarCode() {
        when(repository.findByBarCode(Mockito.anyLong())).thenReturn(Optional.empty());
        patchValidator = new PatchValidator(repository, 128L, productDto);

        assertThrows(EntityNotFoundException.class, () -> patchValidator.patchProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PatchValidator_EmptyRequestBodyException_Id() {
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.ofNullable(new ProductModel()));
        patchValidator = new PatchValidator(repository, 1, productDto);

        assertThrows(EmptyRequestBodyException.class, () -> patchValidator.patchProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PatchValidator_EmptyRequestBodyException_BarCode() {
        when(repository.findByBarCode(Mockito.anyLong())).thenReturn(Optional.ofNullable(new ProductModel()));
        patchValidator = new PatchValidator(repository, 128L, productDto);

        assertThrows(EmptyRequestBodyException.class, () -> patchValidator.patchProductByBarCodeValidator()).printStackTrace();
    }
}
