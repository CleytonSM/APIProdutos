package br.com.cleyton.apiProdutos.ApiProdutos.service.product;

import br.com.cleyton.apiProdutos.ApiProdutos.controller.*;
import br.com.cleyton.apiProdutos.ApiProdutos.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductModel createProduct(ProductDto bodyProduct) {
        PostValidator postValidator = new PostValidator(bodyProduct, repository);
        return postValidator.postProductValidator();
    }

    public ProductModel getProductById(Integer id) {
        GetValidator getValidator = new GetValidator(id, repository);
        return getValidator.getProductValidator();
    }

    public List<ProductModel> getAllProducts() {
        GetValidator getValidator = new GetValidator(repository);
        return getValidator.getAllProductsValidator();
    }

    public Stream<ProductModel> getAllProductsPageable(Integer pageNumber) {
        GetValidator getValidator = new GetValidator(repository, pageNumber);
        return getValidator.getAllProductsPageableValidator();
    }

    public ProductModel partialProductUpdateById(Integer id, ProductDto data) {
        PatchValidator patchValidator = new PatchValidator(repository, id, data);

        return patchValidator.patchProductByIdValidator();
    }

    public ProductModel fullProductUpdateById(Integer id, ProductDto data) {
        PutValidator putValidator = new PutValidator(repository, id, data);

        return putValidator.putProductByIdValidator();
    }

    public ProductModel partialProductUpdateByBarCode(Long barCode, ProductDto data) {
        PatchValidator patchValidator = new PatchValidator(repository, barCode, data);

        return patchValidator.patchProductByBarCodeValidator();
    }

    public ProductModel fullProductUpdateByBarCode(Long barCode, ProductDto data) {
        PutValidator putValidator = new PutValidator(repository, data, barCode);

        return putValidator.putProductByBarCodeValidator();
    }

    public ProductModel deleteProductById(Integer id) {
        DeleteValidator deleteValidator = new DeleteValidator(repository, id);
        return deleteValidator.deleteProductByIdValidator();
    }

    public ProductModel deleteProductByBarCode(Long barCode) {
        DeleteValidator deleteValidator = new DeleteValidator(repository, barCode);
        return deleteValidator.deleteProductByBarCodeValidator();
    }
}
