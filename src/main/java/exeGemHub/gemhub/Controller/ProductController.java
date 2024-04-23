package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct() {
        System.out.printf("jajajaj");
        return ResponseEntity.ok(productService.findAll());
    }
}
