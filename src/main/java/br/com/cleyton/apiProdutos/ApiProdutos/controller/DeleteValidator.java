package br.com.cleyton.apiProdutos.ApiProdutosTests.controller;

import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutosTests.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutosTests.repository.product.ProductRepository;

import java.util.Optional;

public class DeleteValidator {

    private ProductRepository repository;
    private Integer id;
    private Long barCode;
    public DeleteValidator(ProductRepository repository, Integer id) {
        this.repository = repository;
        this.id = id;
    }

    public DeleteValidator(ProductRepository repository, Long barCode) {
        this.repository = repository;
        this.barCode = barCode;
    }

    public ProductModel deleteProductByIdValidator() {
        Optional<ProductModel> optionalProductModel = repository.findById(id);
        return getProductModel(optionalProductModel);
    }

    public ProductModel deleteProductByBarCodeValidator() {
        Optional<ProductModel> optionalProductModel = repository.findByBarCode(barCode);
        return getProductModel(optionalProductModel);
    }

    private ProductModel getProductModel(Optional<ProductModel> optionalProductModel) {
        if(optionalProductModel.isEmpty()) {
            throw new EntityNotFoundException("Esse produto não existe");
        }
        ProductModel product = optionalProductModel.get();
        repository.delete(product);

        return product;
    }
}
