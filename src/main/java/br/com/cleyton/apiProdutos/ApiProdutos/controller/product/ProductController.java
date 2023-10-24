package br.com.cleyton.apiProdutos.ApiProdutos.controller.product;

import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductModel;
import br.com.cleyton.apiProdutos.ApiProdutos.model.product.ProductUpdateRecordData;
import br.com.cleyton.apiProdutos.ApiProdutos.service.product.ProductService;
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

    @GetMapping("/")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("test", HttpStatus.OK);
    }


    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Object> createProduct(@RequestBody ProductModel bodyProductModel) {
        return service.createProduct(bodyProductModel);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        return service.getProductById(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/get-all/page={pageNumber}")
    public ResponseEntity<Object> getAllProductsPageable(@PathVariable Integer pageNumber) {
        return service.getAllProductsPageable(pageNumber);
    }

    @PatchMapping("/partial-update/id={id}")
    @Transactional
    public ResponseEntity<Object> partialProductUpdateById(@PathVariable Integer id, @RequestBody ProductUpdateRecordData data) {
        return service.partialProductUpdateById(id, data);
    }

    @PutMapping("/update/id={id}")
    @Transactional
    public ResponseEntity<Object> fullProductUpdateById(@PathVariable Integer id, @RequestBody ProductUpdateRecordData data) {
        return service.fullProductUpdateById(id, data);
    }

    @PatchMapping("/partial-update/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> partialProductUpdateByBarCode(@PathVariable Long barCode, @RequestBody ProductUpdateRecordData data) {
        return service.partialProductUpdateByBarCode(barCode, data);
    }

    @PutMapping("/update/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> fullProductUpdateByBarCode(@PathVariable Long barCode, @RequestBody ProductUpdateRecordData data) {
        return service.fullProductUpdateByBarCode(barCode, data);
    }

    @DeleteMapping("/delete/id={id}")
    @Transactional
    public ResponseEntity<Object> deleteProductById(@PathVariable Integer id) {
        return service.deleteProductById(id);
    }

    @DeleteMapping("/delete/barCode={barCode}")
    @Transactional
    public ResponseEntity<Object> deleteProductByBarCode(@PathVariable Long barCode) {
        return service.deleteProductByBarCode(barCode);
    }
}
