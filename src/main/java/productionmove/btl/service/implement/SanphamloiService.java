package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Sanphamloi;
import productionmove.btl.repository.ISanphamloiRepository;
import productionmove.btl.service.ISanphamloiService;

import java.util.List;

@Service
public class SanphamloiService implements ISanphamloiService {
    @Autowired
    private ISanphamloiRepository iSanphamloiRepository;
    @Override
    public List<Sanphamloi> findAll() {
        return iSanphamloiRepository.findAll();
    }

    @Override
    public List<Sanphamloi> findAllByCodewarehouse(String code) {
        return iSanphamloiRepository.findAllByCodewarehouse(code);
    }

    @Override
    public Sanphamloi save(Sanphamloi sanphamloi) {
        return iSanphamloiRepository.save(sanphamloi);
    }
}
