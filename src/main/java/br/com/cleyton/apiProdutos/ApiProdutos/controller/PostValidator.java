package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityAlreadyExistsException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.TermsMissingException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.repository.product.ProductRepository;

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
            throw new TermsMissingException("'name' is missing");
        }

        if(bodyProduct.getDescription() == null) {
            throw new TermsMissingException("'description' is missing");
        }

        if(bodyProduct.getPrice() == null) {
            throw new TermsMissingException("'price' is null");
        }

        if(bodyProduct.getQuantity() == null) {
            throw new TermsMissingException("'quantity' is missing");
        }

        if(bodyProduct.getBarCode() == null) {
            throw new TermsMissingException("'barCode' is missing");
        }

        if(bodyProduct.getManufacturingDate() == null) {
            throw new TermsMissingException("'manufacturingDate' is missing");
        }

        if (bodyProduct.getExpirationDate() == null) {
            throw new TermsMissingException("'expirationDate' is missing");
        }

        Optional<ProductModel> isProductModel = repository.findByBarCode(bodyProduct.getBarCode());
        if(isProductModel.isPresent()) {
            throw new EntityAlreadyExistsException("Já existe um produto com esse código");
        }
        ProductModel product = new ProductModel(bodyProduct);
        return repository.save(product);
    }
}

