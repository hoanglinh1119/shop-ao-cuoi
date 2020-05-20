package hoanglinh.codegym.service.product.impl;

import hoanglinh.codegym.model.product.ProductProperties.ProductColor;
import hoanglinh.codegym.repositories.product.IProductColorRepository;
import hoanglinh.codegym.service.product.IProductColorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductColorServiceImpl implements IProductColorService {
    @Autowired
    private IProductColorRepository productColorRepository;
    @Override
    public Iterable<ProductColor> findAll() {
        return productColorRepository.findAll();
    }

    @Override
    public ProductColor findById(Long id) {
        return productColorRepository.findOne(id);
    }

    @Override
    public void save(ProductColor productColor) {
       productColorRepository.save(productColor);
    }

    @Override
    public void remove(Long id) {
   productColorRepository.delete(id);
    }
}
