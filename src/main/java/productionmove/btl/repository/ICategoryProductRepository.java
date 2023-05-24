package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.CategoryProduct;

import java.util.List;

public interface ICategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
    List<CategoryProduct> findAll();
    CategoryProduct findCategoryProductById(Long id);
    CategoryProduct findCategoryProductByName(String name);
    CategoryProduct findByCode(String code);
}
