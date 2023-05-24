package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Account;
import productionmove.btl.entity.ServiceCenter;

import java.util.List;

public interface IServiceCenterRespository extends JpaRepository<ServiceCenter, Long> {
    List<ServiceCenter> findAll();
    ServiceCenter findServiceCenterById(Long id);
    ServiceCenter findServiceCenterByCode(String code);
    ServiceCenter findServiceCenterByName(String name);
}
