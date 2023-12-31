package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.bouncycastle.asn1.cmp.Challenge;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                productRepository.saveAll(List.of(
                        Product.builder()
                                .name("Computer" + i)
                                .price(180 + Math.random() * 10000)
                                .quantity(1 + random.nextInt(200))
                                .build()
                ));
            }

            productRepository.findAll().forEach(
                    customer -> System.out.println(customer.toString()));
        };
    }

}
