package br.com.cleyton.cadastraProdutos.controller;

import br.com.cleyton.cadastraProdutos.exception.EntityNotFoundException;
import br.com.cleyton.cadastraProdutos.repository.product.ProductRepository;
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
public class DeleteValidatorTests {

    @Mock
    private ProductRepository repository;
    private DeleteValidator deleteValidator;


    @Test
    public void DeleValitador_ThrowsEntityNotFoundException_Id() {
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        deleteValidator = new DeleteValidator(repository, 1);

        assertThrows(EntityNotFoundException.class, () -> deleteValidator.deleteProductByIdValidator());
    }

    @Test
    public void DeleValitador_ThrowsEntityNotFoundException_BarCode() {
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.empty());
        deleteValidator = new DeleteValidator(repository, 128L);

        assertThrows(EntityNotFoundException.class, () -> deleteValidator.deleteProductByBarCodeValidator());
    }
}
