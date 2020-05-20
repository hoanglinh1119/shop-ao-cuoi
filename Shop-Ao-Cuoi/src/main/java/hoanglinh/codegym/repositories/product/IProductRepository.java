package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product,Long> {
}
