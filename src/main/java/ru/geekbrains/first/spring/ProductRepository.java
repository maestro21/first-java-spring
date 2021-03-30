package ru.geekbrains.first.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        if(product.getCost() < 0) {
            throw new IllegalArgumentException("Product cost cannot be negavice");
        }
        products.add(product);
    }

    public Product get(int id) {
        Product result = products.stream()
                .filter(product -> id == product.getId())
                .findAny()
                .orElse(null);
        return result;
    }


    public boolean remove(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public Product update(Product data) {
        Product product = get(data.getId());

        if(product != null) {
            product.setTitle(data.getTitle());
            product.setCost(data.getCost());
            return product;
        }
        return null;
    }

}
