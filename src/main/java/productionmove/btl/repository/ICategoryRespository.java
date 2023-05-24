package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Account;
import productionmove.btl.entity.Category;

import java.util.List;

public interface ICategoryRespository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Category findCategoryById(Long id);
    Category findCategoryByCode(String code);
}
