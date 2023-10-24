package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityAlreadyExistsException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.TermsMissingException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductRepository;

import java.util.Optional;

public class PostValidator {
    private final ProductRepository repository;
    private final ProductModel bodyProductModel;

    public PostValidator(ProductModel bodyProductModel, ProductRepository repository) {
        this.repository = repository;
        this.bodyProductModel = bodyProductModel;
    }

    public ProductModel postProductValidator() {
        if(bodyProductModel.getName() == null) {
            throw new TermsMissingException("'name' is missing");
        }

        if(bodyProductModel.getDescription() == null) {
            throw new TermsMissingException("'description' is missing");
        }

        if(bodyProductModel.getPrice() == null) {
            throw new TermsMissingException("'price' is null");
        }

        if(bodyProductModel.getQuantity() == null) {
            throw new TermsMissingException("'quantity' is missing");
        }

        if(bodyProductModel.getBarCode() == null) {
            throw new TermsMissingException("'barCode' is missing");
        }

        if(bodyProductModel.getManufacturingDate() == null) {
            throw new TermsMissingException("'manufacturingDate' is missing");
        }

        if (bodyProductModel.getExpirationDate() == null) {
            throw new TermsMissingException("'expirationDate' is missing");
        }

        Optional<ProductModel> isProductModel = repository.findByBarCode(bodyProductModel.getBarCode());
        if(isProductModel.isPresent()) {
            throw new EntityAlreadyExistsException("Já existe um produto com esse código");
        }

        return repository.save(bodyProductModel);
    }
}

