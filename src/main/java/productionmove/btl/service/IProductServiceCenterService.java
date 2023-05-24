package productionmove.btl.service;

import productionmove.btl.entity.ProductAgency;
import productionmove.btl.entity.ProductServiceCenter;

import java.text.ParseException;
import java.util.List;

public interface IProductServiceCenterService {
    public List<ProductServiceCenter> findAll();
    public List<ProductServiceCenter> findAllByServiceCenter(String code);
    public List<ProductServiceCenter> findAllByCodeagencyAndStatus(String code, String status);
    public ProductServiceCenter save(ProductServiceCenter productServiceCenter);
    public List<ProductServiceCenter> thongkenhapvaotheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductServiceCenter> thongkenhapvaotheothang(String code, String cate, int thang) throws ParseException;
    public List<ProductServiceCenter> thongkebantheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductServiceCenter> thongkebantheothang(String code, String cate, int thang) throws ParseException;
}
