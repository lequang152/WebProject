package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Lichsu;
import productionmove.btl.repository.ILichsuRepository;
import productionmove.btl.service.ILichsuService;

import java.util.List;

@Service
public class LichsuService implements ILichsuService {
    @Autowired
    private ILichsuRepository iLichsuRepository;
    @Override
    public List<Lichsu> findAll() {
        return iLichsuRepository.findAll();
    }

    @Override
    public List<Lichsu> findAllByCodewarehouse(String code) {
        return iLichsuRepository.findAllByCodewarehouse(code);
    }

    @Override
    public List<Lichsu> findAllByCodeagency(String code) {
        return iLichsuRepository.findAllByCodeagency(code);
    }

    @Override
    public Lichsu save(Lichsu lichsu) {
        return iLichsuRepository.save(lichsu);
    }
}
