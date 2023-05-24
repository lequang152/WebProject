package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouseRespository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findAll();
    Optional<Warehouse> findById(Long id);
    Warehouse findByCode(String code);
    Warehouse findByName(String name);
}
