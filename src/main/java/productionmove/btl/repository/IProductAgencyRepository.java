package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.ProductAgency;

import java.util.List;

public interface IProductAgencyRepository extends JpaRepository<ProductAgency, Long> {
    List<ProductAgency> findAllByCategoryproductAndCodeagency(String category, String code);
    List<ProductAgency> findAllByCategoryproductAndCodeagencyAndCodecustomer(String category, String code, String codeCustomer);
}
