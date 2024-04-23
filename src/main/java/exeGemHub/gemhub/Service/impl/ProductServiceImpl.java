package exeGemHub.gemhub.Service.impl;

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
}
