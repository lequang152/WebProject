package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.ServiceCenter;
import productionmove.btl.repository.IServiceCenterRespository;
import productionmove.btl.service.IServiceCenterService;

import java.util.List;

@Service
public class ServiceCenterService implements IServiceCenterService {
    @Autowired
    private IServiceCenterRespository iServiceCenterRespository;
    @Override
    public List<ServiceCenter> getAllServiceCenter() {
        return iServiceCenterRespository.findAll();
    }

    @Override
    public ServiceCenter getServiceCenterById(Long id) throws Exception {
        return iServiceCenterRespository.findServiceCenterById(id);
    }

    @Override
    public ServiceCenter getServiceCenterByCode(String code) {
        return iServiceCenterRespository.findServiceCenterByCode(code);
    }

    @Override
    public ServiceCenter getServiceCenterByName(String name) {
        return iServiceCenterRespository.findServiceCenterByName(name);
    }

    @Override
    public ServiceCenter addServiceCenter(ServiceCenter acc) {
        return iServiceCenterRespository.save(acc);
    }

    @Override
    public ServiceCenter upadteServiceCenter(ServiceCenter acc) {
        return iServiceCenterRespository.save(acc);
    }

    @Override
    public void deleteServiceCenter(Long id) {
        iServiceCenterRespository.deleteById(id);
    }
}
