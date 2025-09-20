package com.kida.kidastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KidaStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(KidaStoreApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(ProductService productService) {
//
//        return args -> {
//        if(productService.getAllProducts().isEmpty())
//            for (int i = 0; i < 10; i++) {
//                Product product = Product.builder()
//                        .name("Product " + i)
//                        .description("Product " + i)
//                        .price(new Random().nextDouble(10.0, 100.0))
//                        .quantityStock(new Random().nextInt(0, 100))
//                        .build();
//                productService.createProduct(product);
//            }
//        };
//    }
}
