package hoanglinh.codegym.service.product.impl;


import hoanglinh.codegym.model.product.ProductProperties.ProductMaterial;
import hoanglinh.codegym.repositories.product.IProductMaterialRepository;
import hoanglinh.codegym.service.product.IProductMaterialService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMaterialServiceImpl implements IProductMaterialService {
    @Autowired
    IProductMaterialRepository iProductMaterialRepository;

    @Override
    public Iterable<ProductMaterial> findAll() {
        return iProductMaterialRepository.findAll();
    }

    @Override
    public ProductMaterial findById(Long id) {
        return iProductMaterialRepository.findOne(id);
    }

    @Override
    public void save(ProductMaterial productMaterial) {
       iProductMaterialRepository.save(productMaterial);
    }

    @Override
    public void remove(Long id) {
       iProductMaterialRepository.delete(id);
    }
}
