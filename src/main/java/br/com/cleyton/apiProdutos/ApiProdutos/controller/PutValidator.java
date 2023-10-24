package br.com.cleyton.apiProdutos.ApiProdutos.controller;

import br.com.cleyton.apiProdutos.ApiProdutos.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutos.exception.TermsMissingException;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductRepository;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductUpdateRecordData;

import java.util.Optional;

public class PutValidator {

    private ProductRepository repository;
    private Integer id;
    private ProductUpdateRecordData data;
    private Long barCode;
    public PutValidator(ProductRepository repository, Integer id, ProductUpdateRecordData data) {
        this.repository = repository;
        this.id = id;
        this.data = data;
    }

    public PutValidator(ProductRepository repository, ProductUpdateRecordData data, Long barCode) {
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
        if (data.name() == null) {
            throw new TermsMissingException("'name' is missing");
        }

        if(data.description() == null) {
            throw new TermsMissingException("'description' is missing");
        }

        if(data.price() == null) {
            throw new TermsMissingException("'price' is missing");
        }

        if(data.quantity() == null) {
            throw new TermsMissingException("'quantity' is missing");
        }

        if(data.barCode() == null) {
            throw new TermsMissingException("'barCode' is missing");
        }

        if(data.manufacturingDate() == null) {
            throw new TermsMissingException("'manufacturingDate' is missing");
        }
        if(data.expirationDate() == null) {
            throw new TermsMissingException("'expirationDate' is missing");
        }

        ProductModel product = optionalProductModel.get();
        product.setName(data.name());
        product.setDescription(data.description());
        product.setPrice(data.price());
        product.setQuantity(data.quantity());
        product.setBarCode(data.barCode());
        product.setManufacturingDate(data.manufacturingDate());
        product.setExpirationDate(data.expirationDate());

        return repository.save(product);
    }
}
