package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;


public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByForGender(String forGender,Pageable pageable);
//    Page<Product> allProduct(String key, int i, int i1);
    Page<Product> findAllByNameStartsWith(String startWithText,Pageable pageable);
    Product findOne(Long id);
    void save(Product product);
    void delete (Long id);

}
