package br.com.cleyton.apiProdutos.ApiProdutos.service.product;

import br.com.cleyton.apiProdutos.ApiProdutos.controller.*;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductRepository;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductUpdateRecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ResponseEntity<Object> createProduct(ProductModel bodyProductModel) {
        PostValidator postValidator = new PostValidator(bodyProductModel, repository);
        ProductModel product = postValidator.postProductValidator();

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getProductById(Integer id) {
        GetValidator getValidator = new GetValidator(id, repository);
        ProductModel product = getValidator.getProductValidator();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllProducts() {
        GetValidator getValidator = new GetValidator(repository);
        List<ProductModel> allProducts = getValidator.getAllProductsValidator();

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllProductsPageable(Integer pageNumber) {
        GetValidator getValidator = new GetValidator(repository, pageNumber);
        Stream<ProductModel> allProductsPageable = getValidator.getAllProductsPageableValidator();

        return new ResponseEntity<>(allProductsPageable, HttpStatus.OK);
    }

    public ResponseEntity<Object> partialProductUpdateById(Integer id, ProductUpdateRecordData data) {
        PatchValidator patchValidator = new PatchValidator(repository, id, data);
        ProductModel product = patchValidator.patchProductByIdValidator();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> fullProductUpdateById(Integer id, ProductUpdateRecordData data) {
        PutValidator putValidator = new PutValidator(repository, id, data);
        ProductModel product = putValidator.putProductByIdValidator();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> partialProductUpdateByBarCode(Long barCode, ProductUpdateRecordData data) {
        PatchValidator patchValidator = new PatchValidator(repository, barCode, data);
        ProductModel product = patchValidator.patchProductByBarCodeValidator();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> fullProductUpdateByBarCode(Long barCode, ProductUpdateRecordData data) {
        PutValidator putValidator = new PutValidator(repository, data, barCode);
        ProductModel product = putValidator.putProductByBarCodeValidator();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteProductById(Integer id) {
        DeleteValidator deleteValidator = new DeleteValidator(repository, id);
        ProductModel product = deleteValidator.deleteProductByIdValidator();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteProductByBarCode(Long barCode) {
        DeleteValidator deleteValidator = new DeleteValidator(repository, barCode);
        ProductModel product = deleteValidator.deleteProductByBarCodeValidator();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
