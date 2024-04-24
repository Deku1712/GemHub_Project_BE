package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.ProductDto;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Service.ProductService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.findAll();
    }



    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@RequestBody @Validated ProductDto productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public void updateProduct(){};

    @DeleteMapping
    public void deleteAllProduct(){
        productService.deleteAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return productService.findById(id).get();
    }

    @PostMapping("/{id}")
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public void addProductById(){}


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") int id , @RequestBody @Validated ProductDto productDto){
        return ResponseEntity.ok(productService.updateProductById(id , productDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") int id) {
        productService.deleteProductById(id);
    }
}
