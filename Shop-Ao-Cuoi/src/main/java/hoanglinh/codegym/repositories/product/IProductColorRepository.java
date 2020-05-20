package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductColorRepository extends PagingAndSortingRepository<ProductColor,Long> {
}
