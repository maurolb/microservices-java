package mauro.store.product.services;

import mauro.store.product.entities.Category;
import mauro.store.product.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProductList();
    public Product getProductById(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProductById(Long id);
    public List<Product> getProductByCategory(Category category);
    public Product updateProductStockById(Long id, Double quantity);
}
