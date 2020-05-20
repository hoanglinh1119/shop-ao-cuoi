package hoanglinh.codegym.service.product.impl;

import hoanglinh.codegym.model.product.ProductProperties.TypeProduct;
import hoanglinh.codegym.repositories.product.ITypeProductRepository;
import hoanglinh.codegym.service.product.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeProductServiceImpl implements ITypeProductService {
    @Autowired
    ITypeProductRepository typeProductRepository;

    @Override
    public Iterable<TypeProduct> findAll() {
        return typeProductRepository.findAll();
    }

    @Override
    public TypeProduct findById(Long id) {
        return typeProductRepository.findOne(id);
    }

    @Override
    public void save(TypeProduct typeProduct) {
         typeProductRepository.save(typeProduct);
    }

    @Override
    public void remove(Long id) {
       typeProductRepository.delete(id);
    }
}
