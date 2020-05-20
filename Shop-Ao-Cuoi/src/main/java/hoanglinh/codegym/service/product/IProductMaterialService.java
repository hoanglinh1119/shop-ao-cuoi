package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;

public interface IProductMaterialService {
    Iterable<ProductMaterial> findAll();

    ProductMaterial findById(Long id);

    void save(ProductMaterial productMaterial);

    void remove(Long id);
}
