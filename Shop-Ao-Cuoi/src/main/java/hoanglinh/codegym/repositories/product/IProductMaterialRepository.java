package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductMaterialRepository extends PagingAndSortingRepository<ProductMaterial,Long> {
}
