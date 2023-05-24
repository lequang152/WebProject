package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Agency;
import productionmove.btl.repository.IAgencyRespository;
import productionmove.btl.service.IAgencyService;

import java.util.List;

@Service
public class AgencyService implements IAgencyService {
    @Autowired
    private IAgencyRespository iAgencyRespository;

    @Override
    public List<Agency> getAllAgency() {
        return iAgencyRespository.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) throws Exception {
        return iAgencyRespository.findById(id).get();
    }

    @Override
    public Agency getAgencyByCode(String code) {
        return iAgencyRespository.findByCode(code);
    }

    @Override
    public Agency getAgencyByName(String name) {
        return iAgencyRespository.findByName(name);
    }

    @Override
    public Agency addAgency(Agency acc) {
        return iAgencyRespository.save(acc);
    }

    @Override
    public Agency upadteAgency(Agency acc) {
        return iAgencyRespository.save(acc);
    }

    @Override
    public void deleteAgency(Long id) {
        iAgencyRespository.deleteById(id);
    }
}
