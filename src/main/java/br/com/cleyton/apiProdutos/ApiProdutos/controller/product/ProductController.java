package br.com.cleyton.apiProdutos.ApiProdutosTests.controller.product;

import br.com.cleyton.apiProdutos.ApiProdutosTests.dto.product.ProductDto;
import br.com.cleyton.apiProdutos.ApiProdutosTests.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto bodyProduct) {
        return new ResponseEntity<>(service.createProduct(bodyProduct), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/get-all/page={pageNumber}")
    public ResponseEntity<Object> getAllProductsPageable(@PathVariable Integer pageNumber) {
        return new ResponseEntity<>(service.getAllProductsPageable(pageNumber), HttpStatus.OK);
    }

    @PatchMapping("/partial-update/id={id}")
    @Transactional
    public ResponseEntity<Object> partialProductUpdateById(@PathVariable Integer id, @RequestBody ProductDto data) {
        return new ResponseEntity<>(service.partialProductUpdateById(id, data), HttpStatus.OK);
    }

    @PutMapping("/update/id={id}")
    @Transactional
    public ResponseEntity<Object> fullProductUpdateById(@PathVariable Integer id, @RequestBody ProductDto data) {
        return new ResponseEntity<>(service.fullProductUpdateById(id, data), HttpStatus.OK);
    }

    @PatchMapping("/partial-update/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> partialProductUpdateByBarCode(@PathVariable Long barCode, @RequestBody ProductDto data) {
        return new ResponseEntity<>(service.partialProductUpdateByBarCode(barCode, data), HttpStatus.OK);
    }

    @PutMapping("/update/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> fullProductUpdateByBarCode(@PathVariable Long barCode, @RequestBody ProductDto data) {
        return new ResponseEntity<>(service.fullProductUpdateByBarCode(barCode, data), HttpStatus.OK);
    }

    @DeleteMapping("/delete/id={id}")
    @Transactional
    public ResponseEntity<Object> deleteProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.deleteProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> deleteProductByBarCode(@PathVariable Long barCode) {
        return new ResponseEntity<>(service.deleteProductByBarCode(barCode), HttpStatus.OK);
    }
}
