package productionmove.btl.service;

import productionmove.btl.entity.CategoryProduct;

import java.util.List;

public interface ICategoryProductService{
    public List<CategoryProduct> findAll();
    public CategoryProduct add(CategoryProduct categoryProduct);
    public CategoryProduct getByName(String name);
    public CategoryProduct getByCode(String code);
    public CategoryProduct getById(Long id);
    public void delete(Long id);
}
