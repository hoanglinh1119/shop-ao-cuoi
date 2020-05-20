package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;

public interface IStoreLocationService {
    Iterable<StoreLocation> findAll();

    StoreLocation findById(Long id);

    void save(StoreLocation storeLocation);

    void remove(Long id);
}
