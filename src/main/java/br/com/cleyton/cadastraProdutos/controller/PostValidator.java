package br.com.cleyton.cadastraProdutos.controller;

import br.com.cleyton.cadastraProdutos.exception.EntityAlreadyExistsException;
import br.com.cleyton.cadastraProdutos.exception.MissingTermsException;
import br.com.cleyton.cadastraProdutos.repository.product.ProductRepository;
import br.com.cleyton.cadastraProdutos.dto.product.ProductDto;
import br.com.cleyton.cadastraProdutos.model.product.ProductModel;

import java.util.Optional;

public class PostValidator {
    private final ProductRepository repository;
    private final ProductDto bodyProduct;

    public PostValidator(ProductDto bodyProduct, ProductRepository repository) {
        this.repository = repository;
        this.bodyProduct = bodyProduct;
    }

    public ProductModel postProductValidator() {
        if(bodyProduct.getName() == null) {
            throw new MissingTermsException("'name' is missing");
        }

        if(bodyProduct.getDescription() == null) {
            throw new MissingTermsException("'description' is missing");
        }

        if(bodyProduct.getPrice() == null) {
            throw new MissingTermsException("'price' is null");
        }

        if(bodyProduct.getQuantity() == null) {
            throw new MissingTermsException("'quantity' is missing");
        }

        if(bodyProduct.getBarCode() == null) {
            throw new MissingTermsException("'barCode' is missing");
        }

        if(bodyProduct.getManufacturingDate() == null) {
            throw new MissingTermsException("'manufacturingDate' is missing");
        }

        if (bodyProduct.getExpirationDate() == null) {
            throw new MissingTermsException("'expirationDate' is missing");
        }

        Optional<ProductModel> isProductModel = repository.findByBarCode(bodyProduct.getBarCode());
        if(isProductModel.isPresent()) {
            throw new EntityAlreadyExistsException("Já existe um produto com esse código");
        }
        ProductModel product = new ProductModel(bodyProduct);
        return repository.save(product);
    }
}

