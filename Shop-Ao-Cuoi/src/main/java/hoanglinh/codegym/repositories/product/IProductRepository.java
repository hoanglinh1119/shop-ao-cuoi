package hoanglinh.codegym.repositories.product;

import hoanglinh.codegym.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByForGender(String forGender, Pageable pageable);
    Page<Product> findAllByNameStartsWith(String startWithText,Pageable pageable);
}
