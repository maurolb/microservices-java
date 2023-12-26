package mauro.store.product;

import mauro.store.product.entities.Category;
import mauro.store.product.entities.Product;
import mauro.store.product.repositories.ProductRepository;
import mauro.store.product.services.ProductService;
import mauro.store.product.services.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

// Test del servicio usando datos mockeados con Mockito
@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    // Metodo utilizado para mockear todos los datos necesarios para los tests
    // BeforeEach para que se pueda ejecutar antes de los tests
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this); // Inicializa
        // Instancia el productService inyectando por constructor el productRepository mockeado
        productService = new ProductServiceImpl(productRepository);
        // Crea un producto
        Product computer = Product.builder()
                .id(1L)
                .name("master race")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        // Cuando busca un producto por ID, retorna el producto creado
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer)); // Retorna un Optional porque "findById" retorna un Optional
        // Cuando se actualiza un producto, tambien se va a actualiza el mock
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetId_ThenReturnProduct(){
        // Busca un producto con el service y devuelve el producto mockeado
        Product found = productService.getProductById(1L);
        // Compara si la propiedad name del producto encontrado conicide con "master race"
        Assertions.assertThat(found.getName()).isEqualTo("master race");
    }

    @Test
    public void whenValidUpdateStock_thenReturnNewStock(){
        // Actualiza el stock pasando el mismo id del producto mockeado y valor del stock a calcular
        Product newStock = productService.updateProductStockById(1L, Double.parseDouble("8"));
        // Compara que el stock sea igual a: el stock del producto mockeado 5 + el valor agregado 8
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
