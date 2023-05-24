package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Lichsu;

import java.util.List;

public interface ILichsuRepository extends JpaRepository<Lichsu, Long> {
    List<Lichsu> findAllByCodewarehouse(String code);
    List<Lichsu> findAllByCodeagency(String code);
}
