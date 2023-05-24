package productionmove.btl.service;

import productionmove.btl.entity.Lichsu;
import productionmove.btl.entity.Sanphamloi;

import java.util.List;

public interface ISanphamloiService {
    public List<Sanphamloi> findAll();
    public List<Sanphamloi> findAllByCodewarehouse(String code);
    public Sanphamloi save(Sanphamloi sanphamloi);
}
