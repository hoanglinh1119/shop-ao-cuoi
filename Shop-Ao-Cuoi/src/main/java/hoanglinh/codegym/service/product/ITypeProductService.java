package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.ProductProperties.StoreLocation;
import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;

public interface ITypeProductService {
    Iterable<TypeProduct> findAll();

    TypeProduct findById(Long id);

    void save(TypeProduct typeProduct);

    void remove(Long id);
}
