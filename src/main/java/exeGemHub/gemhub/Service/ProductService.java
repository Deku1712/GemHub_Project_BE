package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.DTO.ProductDto;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    Optional<Product> findById(int id);


    List<Product> findAll();

    Product createProduct(ProductDto productDto);

    void deleteAllProduct();

    Product updateProductById(int id, ProductDto productDto);

    void deleteProductById(int id);

    List<Product> searchProduct(String word);

    List<Product> getProductLimtied();

//    List<Product> getProductLimtied();
}
