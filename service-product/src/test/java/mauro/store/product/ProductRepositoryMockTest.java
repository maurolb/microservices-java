package mauro.store.product;

import mauro.store.product.entities.Category;
import mauro.store.product.entities.Product;
import mauro.store.product.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

// Test del Repositorio usando base de datos en memoria h2
@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        // Crea un producto y lo guarda
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("desktop computer")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("CREATED")
                .createAt(new Date())
                .build();

        productRepository.save(product01);

        // Utiliza el metodo personalizado para obtener una lista de productos
        List<Product> founds = productRepository.findByCategory(product01.getCategory());

        // Compara que el total de productos de la lista sea igual al total 2 + el agregado anteriormente 1
        Assertions.assertThat(founds.size()).isEqualTo(3);

    }
}
