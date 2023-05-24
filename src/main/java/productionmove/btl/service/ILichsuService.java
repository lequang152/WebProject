package productionmove.btl.service;

import productionmove.btl.entity.Lichsu;

import java.util.List;

public interface ILichsuService {
    public List<Lichsu> findAll();
    public List<Lichsu> findAllByCodewarehouse(String code);
    public List<Lichsu> findAllByCodeagency(String code);
    public Lichsu save(Lichsu lichsu);
}
