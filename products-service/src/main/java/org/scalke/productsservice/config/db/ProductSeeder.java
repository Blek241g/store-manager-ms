package org.scalke.productsservice.config.db;

import org.scalke.productsservice.constants.ProductState;
import org.scalke.productsservice.entities.Product;
import org.scalke.productsservice.repositories.ProductRepository;
import org.scalke.productsservice.services.ProductService;
import org.scalke.productsservice.web.requests.AddProductRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;

@Configuration
public class ProductSeeder {

    @Bean
    CommandLineRunner initDatabase(ProductService productService, ProductRepository productRepository) {
        return args -> {
            for(int i = 1; i<6; i++){
               for(int j=1; j<=5; j++){
                   productService.addNewProduct(AddProductRequest.builder()
                           .designation("designation"+i+""+j)
                           .description("Description"+i+""+j)
                           .photo("https://localhost/product"+j+"-"+i+".jpeg")
                           .ref("ref"+ i+""+j)
                           .state(ProductState.NEW)
                           .uprice(Math.random()*77)
                           .vat(Math.random())
                           .ownerId((long)i)
                           .build());


//                   productRepository.save(Product.builder()
//                           .createdAt(LocalDateTime.now())
//                           .deleted(false)
//                           .designation("designation" + (Math.random() > 0.3 ? j:i))
//                           .description("Description "+(Math.random() > 0.3 ? j:i))
//                           .photo("https://localhost/product"+j+"-"+i+".jpeg")
//                           .ref("ref"+ i+""+j)
//                           .state(ProductState.NEW)
//                           .uPrice(Math.random()*77)
//                           .vat(Math.random())
//                           .ownerId((i + 1)*2L)
//                           .build());
               }
            }
        };
    }
}
