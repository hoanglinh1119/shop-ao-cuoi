package hoanglinh.codegym.service.product.impl;

import hoanglinh.codegym.model.product.Product;
import hoanglinh.codegym.repositories.product.IProductRepository;
import hoanglinh.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository iProductRepositories;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepositories.findAll(pageable);
    }

    @Override
    public Page<Product> findByForGender(String forGender,Pageable pageable) {
        return iProductRepositories.findAllByForGender(forGender,pageable);
    }

    @Override
    public Page<Product> findAllByNameStartsWith(String startWithText, Pageable pageable) {
        return iProductRepositories.findAllByNameStartsWith(startWithText,pageable);
    }

//    @Override
//    public Page<Product> allProduct(String key, int i, int i1) {
//        return (Page<Product>) iProductRepositories.findAll();
//    }


    @Override
    public Product findOne(Long id) {
        return iProductRepositories.findOne(id);
    }

    @Override
    public void save(Product product) {
   iProductRepositories.save(product);
    }

    @Override
    public void delete(Long id) {
   iProductRepositories.delete(id);
    }

}
