package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.MissingTermsException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.repository.product.ProductRepository;

import java.util.Optional;

public class PutValidator {

    private final ProductRepository repository;
    private Integer id;
    private final ProductDto data;
    private Long barCode;
    public PutValidator(ProductRepository repository, Integer id, ProductDto data) {
        this.repository = repository;
        this.id = id;
        this.data = data;
    }

    public PutValidator(ProductRepository repository, ProductDto data, Long barCode) {
        this.repository = repository;
        this.data = data;
        this.barCode = barCode;
    }

    public ProductModel putProductByIdValidator() {
        Optional<ProductModel> optionalProductModel = repository.findById(id);
        return getProductModel(optionalProductModel);
    }

    public ProductModel putProductByBarCodeValidator() {
        Optional<ProductModel> optionalProductModel = repository.findByBarCode(barCode);
        return getProductModel(optionalProductModel);
    }

    private ProductModel getProductModel(Optional<ProductModel> optionalProductModel) {
        if(optionalProductModel.isEmpty()) {
            throw new EntityNotFoundException("Esse produto não existe");
        }
        if (data.getName() == null) {
            throw new MissingTermsException("'name' is missing");
        }

        if(data.getDescription() == null) {
            throw new MissingTermsException("'description' is missing");
        }

        if(data.getPrice() == null) {
            throw new MissingTermsException("'price' is missing");
        }

        if(data.getQuantity() == null) {
            throw new MissingTermsException("'quantity' is missing");
        }

        if(data.getBarCode() == null) {
            throw new MissingTermsException("'barCode' is missing");
        }

        if(data.getManufacturingDate() == null) {
            throw new MissingTermsException("'manufacturingDate' is missing");
        }
        if(data.getExpirationDate() == null) {
            throw new MissingTermsException("'expirationDate' is missing");
        }

        ProductModel product = optionalProductModel.get();
        product.setName(data.getName());
        product.setDescription(data.getDescription());
        product.setPrice(data.getPrice());
        product.setQuantity(data.getQuantity());
        product.setBarCode(data.getBarCode());
        product.setManufacturingDate(data.getManufacturingDate());
        product.setExpirationDate(data.getExpirationDate());

        return repository.save(product);
    }
}
