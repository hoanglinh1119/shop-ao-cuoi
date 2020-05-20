package hoanglinh.codegym.service.product;

import hoanglinh.codegym.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Product findOne(Long id);
    void save(Product product);
    void delete (Long id);
}
