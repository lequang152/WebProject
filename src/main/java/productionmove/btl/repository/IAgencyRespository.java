package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface IAgencyRespository extends JpaRepository<Agency, Long> {
    List<Agency> findAll();
    Optional<Agency> findById(Long id);
    Agency findByCode(String code);
    Agency findByName(String name);
}
