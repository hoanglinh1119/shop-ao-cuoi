package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IStoreLocationRepository extends PagingAndSortingRepository<StoreLocation,Long> {
}
