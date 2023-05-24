package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Sanphamloi;

import java.util.List;

public interface ISanphamloiRepository extends JpaRepository<Sanphamloi, Long> {
    List<Sanphamloi> findAllByCodewarehouse(String code);
}
