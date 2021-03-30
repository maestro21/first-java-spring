package ru.geekbrains.first.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while(run) {
            String input = sc.nextLine();
            String[] scArgs = input.split(" ");

            try {
                String cmd = scArgs[0];
                if (cmd.equals("/avg_score")) {
                    System.out.println(productService.calculateAverageCost());
                }

                if (cmd.equals("/add")) {
                    Product product = productService.add(scArgs[1], Float.parseFloat(scArgs[2]));
                    if (product != null) {
                        System.out.println("Added new product #" + product);
                    } else {
                        System.out.println("Error adding product");
                    }
                }

                if (cmd.equals("/put")) {
                    Product product = productService.put(Integer.parseInt(scArgs[1]), scArgs[2], Float.parseFloat(scArgs[2]));
                    if (product != null) {
                        System.out.println("Updated product " + product);
                    } else {
                        System.out.println("Product #" + scArgs[1] + " not found");
                    }
                }

                if (cmd.equals("/list")) {
                    List<Product> list = productService.list();
                    for (Product product : list) {
                        System.out.println(product);
                    }
                }

                if (cmd.equals("/del")) {
                    int id = Integer.parseInt(scArgs[1]);
                    if (productService.del(id)) {
                        System.out.println("Deleted product #" + id);
                    } else {
                        System.out.println("Product #" + id + " not found");
                    }
                }

                if (cmd.equals("/exit")) {
                    run = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        context.close();
    }
}
