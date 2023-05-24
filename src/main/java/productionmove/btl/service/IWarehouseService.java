package productionmove.btl.service;

import productionmove.btl.entity.ServiceCenter;
import productionmove.btl.entity.Warehouse;

import java.util.List;

public interface IWarehouseService {
    public List<Warehouse> getAllWarehouse();
    public Warehouse getWarehouseById(Long id) throws Exception;
    public Warehouse getWarehouseByCode(String code);
    public Warehouse getWarehouseByName(String name);
    public Warehouse addWarehouse(Warehouse acc);
    public Warehouse upadteWarehouse(Warehouse acc);
    public void deleteWarehouse(Long id);

}
