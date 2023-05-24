package productionmove.btl.service;

import productionmove.btl.entity.ProductAgency;
import productionmove.btl.entity.ProductWarehouse;

import java.text.ParseException;
import java.util.List;

public interface IProductWarehouseService {
    public List<ProductWarehouse> findAll();
    public List<ProductWarehouse> findAllByWarehouse(String code);
    public ProductWarehouse findById(Long id);
    List<ProductWarehouse> findAllByCodewarehouseAndCategoryproduct(String code, String cate);
    ProductWarehouse save(ProductWarehouse warehouse);
    public List<ProductWarehouse> thongkenhapvaotheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductWarehouse> thongkenhapvaotheothang(String code, String cate, int thang) throws ParseException;
    public List<ProductWarehouse> thongkebantheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductWarehouse> thongkebantheothang(String code, String cate, int thang) throws ParseException;
}
