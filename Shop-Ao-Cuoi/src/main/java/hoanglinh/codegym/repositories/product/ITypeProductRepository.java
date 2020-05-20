package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITypeProductRepository extends JpaRepository<TypeProduct,Long> {
}
