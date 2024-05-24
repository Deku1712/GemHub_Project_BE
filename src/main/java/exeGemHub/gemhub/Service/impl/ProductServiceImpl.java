package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.ProductDto;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Repository.ProductRepo;
import exeGemHub.gemhub.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepo productRepo;

    @Override
    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product p = new Product();
        p.setProductName(productDto.getProductName());
        p.setProductPrice(productDto.getProductPrice());
        p.setProductDescription(productDto.getProductDescription());
        p.setProductQuantity(productDto.getProductQuantity());
        p.setProductType(productDto.getProductType());
        p.setCreateTime(productDto.getCreateTime());
        p.setUpdateTime(productDto.getUpdateTime());

        return productRepo.save(p);
    }

    @Override
    public void deleteAllProduct() {
        productRepo.deleteAll();

    }

    @Override
    public Product updateProductById(int id, ProductDto productDto) {
        Product p = productRepo.findById(id).get();
        p.setProductPrice(productDto.getProductPrice());
        p.setProductDescription(productDto.getProductDescription());
        p.setProductQuantity(productDto.getProductQuantity());
        p.setProductType(productDto.getProductType());
        p.setCreateTime(productDto.getCreateTime());
        p.setUpdateTime(productDto.getUpdateTime());
        p.setProductName(productDto.getProductName());

        return productRepo.save(p);

    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> searchProduct(String word) {
        return productRepo.findByProductNameContainingIgnoreCase(word);
    }
}
