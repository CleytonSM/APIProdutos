package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.exception.EmptyRequestBodyException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductRepository;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductUpdateRecordData;

import java.util.Optional;

public class PatchValidator {

    private ProductRepository repository;
    private Integer id;
    private ProductUpdateRecordData data;
    private Long barCode;
    public PatchValidator(ProductRepository repository, Integer id, ProductUpdateRecordData data) {
        this.repository = repository;
        this.id = id;
        this.data = data;
    }

    public PatchValidator(ProductRepository repository, Long barCode, ProductUpdateRecordData data) {
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
        if(data.name() != null) {
            product.setName(data.name());
        }
        if(data.description() != null) {
            product.setDescription(data.description());
        }
        if(data.price() != null) {
            product.setPrice(data.price());
        }
        if(data.quantity() != null) {
            product.setQuantity(data.quantity());
        }
        if(data.barCode() != null) {
            product.setBarCode(data.barCode());
        }
        if(data.manufacturingDate() != null) {
            product.setManufacturingDate(data.manufacturingDate());
        }
        if(data.expirationDate() != null) {
            product.setExpirationDate(data.expirationDate());
        }
        if(data.name() == null && data.manufacturingDate() == null && data.expirationDate() == null && data.description() == null
                && data.barCode() == null && data.price() == null && data.quantity() == null ) {
            throw new EmptyRequestBodyException("Corpo da requisição vazio");
        }
        return repository.save(product);
    }
}
