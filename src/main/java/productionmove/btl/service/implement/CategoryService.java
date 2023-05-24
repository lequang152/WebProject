package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Category;
import productionmove.btl.repository.ICategoryRespository;
import productionmove.btl.service.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRespository iCategoryRespository;
    @Override
    public List<Category> getAllCategory() {
        return iCategoryRespository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        return iCategoryRespository.findCategoryById(id);
    }

    @Override
    public Category addCategory(Category acc) {
        return iCategoryRespository.save(acc);
    }

    @Override
    public Category upadteCategory(Category acc) {
        return iCategoryRespository.save(acc);
    }

    @Override
    public void deleteCategory(Long id) {
        iCategoryRespository.deleteById(id);
    }
}
