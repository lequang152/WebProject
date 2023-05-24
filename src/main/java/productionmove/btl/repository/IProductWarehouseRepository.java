package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.ProductWarehouse;

import java.util.List;

public interface IProductWarehouseRepository extends JpaRepository<ProductWarehouse, Long> {
    List<ProductWarehouse> findAllByCodewarehouse(String code);
    List<ProductWarehouse> findAllByCodewarehouseAndCategoryproduct(String code, String cate);
}
