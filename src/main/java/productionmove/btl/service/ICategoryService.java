package productionmove.btl.service;

import productionmove.btl.entity.Category;

import java.util.List;

public interface ICategoryService  {
    public List<Category> getAllCategory();
    public Category getCategoryById(Long id) throws Exception;
    public Category addCategory(Category acc);
    public Category upadteCategory(Category acc);
    public void deleteCategory(Long id);
}
