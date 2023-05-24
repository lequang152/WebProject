package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.ProductServiceCenter;

import java.util.List;

public interface IProductServiceCenterRepository extends JpaRepository<ProductServiceCenter, Long> {
    List<ProductServiceCenter> findAllByCodeagencyAndStatus(String code, String status);
}
