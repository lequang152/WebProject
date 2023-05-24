package productionmove.btl.service;

import productionmove.btl.entity.Agency;

import java.util.List;

public interface IAgencyService{
    public List<Agency> getAllAgency();
    public Agency getAgencyById(Long id) throws Exception;
    public Agency getAgencyByCode(String code);
    public Agency getAgencyByName(String name);
    public Agency addAgency(Agency acc);
    public Agency upadteAgency(Agency acc);
    public void deleteAgency(Long id);
}
