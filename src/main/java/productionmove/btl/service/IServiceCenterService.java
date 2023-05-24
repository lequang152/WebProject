package productionmove.btl.service;

import productionmove.btl.entity.ServiceCenter;

import java.util.List;

public interface IServiceCenterService  {
    public List<ServiceCenter> getAllServiceCenter();
    public ServiceCenter getServiceCenterById(Long id) throws Exception;
    public ServiceCenter getServiceCenterByCode(String code);
    public ServiceCenter getServiceCenterByName(String name);
    public ServiceCenter addServiceCenter(ServiceCenter acc);
    public ServiceCenter upadteServiceCenter(ServiceCenter acc);
    public void deleteServiceCenter(Long id);

}
