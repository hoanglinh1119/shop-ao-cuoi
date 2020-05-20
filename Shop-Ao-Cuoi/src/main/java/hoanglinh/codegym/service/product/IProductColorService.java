package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductColor;

public interface IProductColorService {
    Iterable<ProductColor> findAll();

    ProductColor findById(Long id);

    void save(ProductColor productColor);

    void remove(Long id);
}
