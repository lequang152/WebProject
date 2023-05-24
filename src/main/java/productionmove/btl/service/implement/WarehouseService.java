package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Warehouse;
import productionmove.btl.repository.IWarehouseRespository;
import productionmove.btl.service.IWarehouseService;

import java.util.List;
@Service
public class WarehouseService implements IWarehouseService {
    @Autowired
    private IWarehouseRespository iWarehouseRespository;
    @Override
    public List<Warehouse> getAllWarehouse() {
        return iWarehouseRespository.findAll();
    }

    @Override
    public Warehouse getWarehouseById(Long id) throws Exception {
        return iWarehouseRespository.findById(id).get();
    }

    @Override
    public Warehouse getWarehouseByCode(String code) {
        return iWarehouseRespository.findByCode(code);
    }

    @Override
    public Warehouse getWarehouseByName(String name) {
        return iWarehouseRespository.findByName(name);
    }

    @Override
    public Warehouse addWarehouse(Warehouse acc) {
        return iWarehouseRespository.save(acc);
    }

    @Override
    public Warehouse upadteWarehouse(Warehouse acc) {
        return iWarehouseRespository.save(acc);
    }

    @Override
    public void deleteWarehouse(Long id) {
        iWarehouseRespository.deleteById(id);
    }
}
