package productionmove.btl.service;

import productionmove.btl.entity.ProductAgency;

import java.text.ParseException;
import java.util.List;

public interface IProductAgencyService {
    public List<ProductAgency> findAll();
    public List<ProductAgency> findAllByAgency(String code);
    public List<ProductAgency> findAllByCategoryAndAgency(String category, String code);
    public List<ProductAgency> findAllByCategoryAndAgencyAndCodecustomer(String category, String code, String codeCustomer);
    public ProductAgency findById(Long id);
    public List<ProductAgency> thongkenhapvaotheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductAgency> thongkenhapvaotheothang(String code, String cate, int thang) throws ParseException;
    public List<ProductAgency> thongkebantheoquy(String code, String cate, int quy) throws ParseException;
    public List<ProductAgency> thongkebantheothang(String code, String cate, int thang) throws ParseException;
    public ProductAgency save(ProductAgency agency);
}
