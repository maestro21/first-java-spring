package ru.geekbrains.first.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Value("12234-42342-234234")
    private String identifier;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private int ai = 0;

    public Product add(String title, double cost) {
        ai++;
        Product product = new Product(ai, title, cost);
        try {
            this.productRepository.add(product);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return product;
    }

    public boolean del(int id) {
        return this.productRepository.remove(id);
    }

    public Product put(int id, String title, double cost) {
        return productRepository.update(new Product(id, title, cost));
    }

    public List<Product> list() {
        return productRepository.getAll();
    }

    public int getProductAmount() {
        List<Product> products = productRepository.getAll();
        return products.size();
    }

    public int calculateAverageCost() {
        List<Product> products = productRepository.getAll();
        if (products.size() == 0) {
            return 0;
        }

        int avg = 0;
        for (Product s : products) {
            avg += s.getCost();
        }
        avg /= getProductAmount();
        return avg;
    }
}
