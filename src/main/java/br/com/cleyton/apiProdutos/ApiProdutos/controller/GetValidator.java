package br.com.cleyton.apiProdutos.ApiProdutosTests.controller;

import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.EmptyPageException;
import br.com.cleyton.apiProdutos.ApiProdutosTests.exception.EntityNotFoundException;
import br.com.cleyton.apiProdutos.ApiProdutosTests.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutosTests.repository.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GetValidator {

    private ProductRepository repository;
    private Integer id;
    private Integer pageNumber;

    public GetValidator(Integer id, ProductRepository repository ) {
        this.repository = repository;
        this.id = id;
    }

    public GetValidator(ProductRepository repository) {
        this.repository = repository;
    }

    public GetValidator(ProductRepository repository, Integer pageNumber) {
        this.repository = repository;
        this.pageNumber = pageNumber;
    }

    public ProductModel getProductValidator() {
        Optional<ProductModel> optionalProductModel = repository.findById(id);
        if(optionalProductModel.isEmpty()) {
            throw new EntityNotFoundException("Esse produto não existe");
        }
        return optionalProductModel.get();
    }

    public List<ProductModel> getAllProductsValidator() {
        List<ProductModel> allProducts = repository.findAll();

        if(allProducts.isEmpty()) {
            throw new EntityNotFoundException("Nenhum produto foi cadastrado ainda");
        }

        return repository.findAll();
    }

    public Stream<ProductModel> getAllProductsPageableValidator() {
        final int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ProductModel> productsPage = repository.findAll(pageable);

        if(productsPage.isEmpty()) {
            throw new EmptyPageException("Essa página está vazia");
        }

        return productsPage.stream();
    }
}
