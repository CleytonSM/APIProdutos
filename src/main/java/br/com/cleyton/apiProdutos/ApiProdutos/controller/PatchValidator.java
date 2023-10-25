package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.EmptyRequestBodyException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.repository.product.ProductRepository;

import java.util.Optional;

public class PatchValidator {

    private ProductRepository repository;
    private Integer id;
    private ProductDto data;
    private Long barCode;
    public PatchValidator(ProductRepository repository, Integer id, ProductDto data) {
        this.repository = repository;
        this.id = id;
        this.data = data;
    }

    public PatchValidator(ProductRepository repository, Long barCode, ProductDto data) {
        this.repository = repository;
        this.data = data;
        this.barCode = barCode;
    }

    public ProductModel patchProductByIdValidator() {
        Optional<ProductModel> optionalProductModel = repository.findById(id);
        return getProductModel(optionalProductModel);
    }

    public ProductModel patchProductByBarCodeValidator() {
        Optional<ProductModel> optionalProductModel = repository.findByBarCode(barCode);
        return getProductModel(optionalProductModel);
    }

    private ProductModel getProductModel(Optional<ProductModel> optionalProductModel) {
        if(optionalProductModel.isEmpty()) {
            throw new EntityNotFoundException("Esse produto não existe");
        }
        ProductModel product = optionalProductModel.get();
        if(data.getName() != null) {
            product.setName(data.getName());
        }
        if(data.getDescription() != null) {
            product.setDescription(data.getDescription());
        }
        if(data.getPrice() != null) {
            product.setPrice(data.getPrice());
        }
        if(data.getQuantity() != null) {
            product.setQuantity(data.getQuantity());
        }
        if(data.getBarCode() != null) {
            product.setBarCode(data.getBarCode());
        }
        if(data.getManufacturingDate() != null) {
            product.setManufacturingDate(data.getManufacturingDate());
        }
        if(data.getExpirationDate() != null) {
            product.setExpirationDate(data.getExpirationDate());
        }
        if(data.getName() == null && data.getManufacturingDate() == null && data.getExpirationDate() == null && data.getDescription() == null
                && data.getBarCode() == null && data.getPrice() == null && data.getQuantity() == null ) {
            throw new EmptyRequestBodyException("Corpo da requisição vazio");
        }
        return repository.save(product);
    }
}
