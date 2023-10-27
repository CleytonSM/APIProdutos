package br.com.cleyton.apiProdutos.ApiProdutosTests.controller;

import br.com.cleyton.apiProdutos.ApiProdutosTests.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.EntityNotFoundException;
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
public class PutValidatorTests {

    @Mock
    private ProductRepository repository;
    private PutValidator putValidator;
    private ProductDto productDto;
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
    public void PutValidator_ThrowsEntityNotFoundException_Id() {
        when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(EntityNotFoundException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_NameIsMissingId() {
        productDto.setName(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_DescriptionIsMissingId() {
        productDto.setDescription(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_PriceIsMissingId() {
        productDto.setPrice(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_QuantityIsMissingId() {
        productDto.setQuantity(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_BarCodeIsMissingId() {
        productDto.setBarCode(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_ManufacturingDateIsMissingId() {
        productDto.setManufacturingDate(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_ExpirationDateIsMissingId() {
        productDto.setExpirationDate(null);
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, 1, productDto);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByIdValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsEntityNotFoundException_BarCode() {
        when(repository.findByBarCode(Mockito.anyLong())).thenReturn(Optional.empty());
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(EntityNotFoundException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_NameIsMissingBarCode() {
        productDto.setName(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_DescriptionIsMissingBarCode() {
        productDto.setDescription(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_PriceIsMissingBarCode() {
        productDto.setPrice(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_QuantityIsMissingBarCode() {
        productDto.setQuantity(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_BarCodeIsMissingBarCode() {
        productDto.setBarCode(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_ManufacturingDateIsMissingBarCode() {
        productDto.setManufacturingDate(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }

    @Test
    public void PutValidator_ThrowsMissingTermsException_ExpirationDateIsMissingBarCode() {
        productDto.setExpirationDate(null);
        when(repository.findByBarCode(Mockito.any())).thenReturn(Optional.of(new ProductModel(productDto)));
        putValidator = new PutValidator(repository, productDto, 128L);
        assertThrows(MissingTermsException.class, () -> putValidator.putProductByBarCodeValidator()).printStackTrace();
    }
}
