package main;

import config.ProjectConfig;
import models.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.ProductRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class)){


            ProductRepository productRepository = c.getBean(ProductRepository.class);

            /*
            Product p1 = new Product();
            p1.setName("Rocket");
            p1.setPrice(120000000);
            productRepository.addProduct(p1);

             */




            productRepository.deleteProduct(1);
            //productRepository.setPrice(1,"laptop0", 10);

            List<Product> products = productRepository.getProducts();
            products.forEach(System.out::println);


        }
    }
}
