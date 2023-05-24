package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.CategoryProduct;
import productionmove.btl.repository.ICategoryProductRepository;
import productionmove.btl.service.ICategoryProductService;

import java.util.List;

@Service
public class CategoryProductService implements ICategoryProductService {
    @Autowired
    private ICategoryProductRepository iCategoryProductRepository;

    @Override
    public List<CategoryProduct> findAll() {
        List<CategoryProduct> list = iCategoryProductRepository.findAll();
        for(CategoryProduct cp: list){
            cp.setSold(0);
            cp.setQuantity(0);
            cp.setRemaining(0);
        }
        return list;
    }

    @Override
    public CategoryProduct add(CategoryProduct categoryProduct) {
        return iCategoryProductRepository.save(categoryProduct);
    }

    @Override
    public CategoryProduct getByName(String name) {
        return iCategoryProductRepository.findCategoryProductByName(name);
    }

    @Override
    public CategoryProduct getByCode(String code) {
        return iCategoryProductRepository.findByCode(code);
    }

    @Override
    public CategoryProduct getById(Long id) {
        return iCategoryProductRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        iCategoryProductRepository.deleteById(id);
    }
}
