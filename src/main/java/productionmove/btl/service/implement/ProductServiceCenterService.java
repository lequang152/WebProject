package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.ProductAgency;
import productionmove.btl.entity.ProductServiceCenter;
import productionmove.btl.repository.IProductServiceCenterRepository;
import productionmove.btl.service.IProductServiceCenterService;
import productionmove.btl.utils.Utils;

import java.text.ParseException;
import java.util.List;

@Service
public class ProductServiceCenterService implements IProductServiceCenterService {
    @Autowired
    private IProductServiceCenterRepository iProductServiceCenterRepository;

    @Override
    public List<ProductServiceCenter> findAll() {
        return iProductServiceCenterRepository.findAll();
    }

    @Override
    public List<ProductServiceCenter> findAllByServiceCenter(String code) {
        List<ProductServiceCenter> list = iProductServiceCenterRepository.findAll();
        for(ProductServiceCenter p: list){
            if(!p.getCodeservicecenter().equals(code)){
                list.remove(p);
            }
        }
        return list;
    }

    @Override
    public List<ProductServiceCenter> findAllByCodeagencyAndStatus(String code, String status) {
        return iProductServiceCenterRepository.findAllByCodeagencyAndStatus(code, status);
    }

    @Override
    public ProductServiceCenter save(ProductServiceCenter productServiceCenter) {
        return iProductServiceCenterRepository.save(productServiceCenter);
    }

    @Override
    public List<ProductServiceCenter> thongkenhapvaotheoquy(String code, String cate, int quy) throws ParseException {
        int year = java.time.LocalDate.now().getYear();
        String start1 = String.valueOf(year) + "-01-01";
        String end1 = String.valueOf(year) + "-03-31";
        String start2 = String.valueOf(year) + "-04-01";
        String end2 = String.valueOf(year) + "-06-30";
        String start3 = String.valueOf(year) + "-07-01";
        String end3 = String.valueOf(year) + "-09-30";
        String start4 = String.valueOf(year) + "-10-01";
        String end4 = String.valueOf(year) + "-12-31";
        List<ProductServiceCenter> list = iProductServiceCenterRepository.findAll();
        list.removeIf(a -> !a.getCodeservicecenter().equals(code));
        list.removeIf(a -> !a.getCategoryproduct().equals(cate));
        if(quy == 1){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start1, end1, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 2){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, end2, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 3){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start3, end3, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 4) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start4, end4, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
        return list;
    }

    @Override
    public List<ProductServiceCenter> thongkenhapvaotheothang(String code, String cate, int thang) throws ParseException {
        int year = java.time.LocalDate.now().getYear();
        boolean isLeap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    isLeap = true;
                else
                    isLeap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                isLeap = true;
        }
        else {
            isLeap = false;
        }
        String start1 = String.valueOf(year) + "-01-01";
        String end1 = String.valueOf(year) + "-01-31";
        String start2 = String.valueOf(year) + "-02-01";
        String end2 = String.valueOf(year) + "-02-28";
        if(isLeap){
            end2 = String.valueOf(year) + "-02-29";
        }
        String start3 = String.valueOf(year) + "-03-01";
        String end3 = String.valueOf(year) + "-03-31";
        String start4 = String.valueOf(year) + "-04-01";
        String end4 = String.valueOf(year) + "-04-30";
        String start5 = String.valueOf(year) + "-05-01";
        String end5 = String.valueOf(year) + "-05-31";
        String start6 = String.valueOf(year) + "-06-01";
        String end6 = String.valueOf(year) + "-06-30";
        String start7 = String.valueOf(year) + "-07-01";
        String end7 = String.valueOf(year) + "-07-31";
        String start8 = String.valueOf(year) + "-08-01";
        String end8 = String.valueOf(year) + "-08-31";
        String start9 = String.valueOf(year) + "-09-01";
        String end9 = String.valueOf(year) + "-09-30";
        String start10 = String.valueOf(year) + "-10-01";
        String end10 = String.valueOf(year) + "-10-31";
        String start11= String.valueOf(year) + "-11-01";
        String end11 = String.valueOf(year) + "-11-30";
        String start12= String.valueOf(year) + "-12-01";
        String end12 = String.valueOf(year) + "-12-31";
        List<ProductServiceCenter> list = iProductServiceCenterRepository.findAll();
        list.removeIf(a -> !a.getCodeservicecenter().equals(code));
        list.removeIf(a -> !a.getCategoryproduct().equals(cate));
        if(thang == 1){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start1, end1, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 2){
            String finalEnd = end2;
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, finalEnd, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 3){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start3, end3, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 4) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start4, end4, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 5){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start5, end5, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 6){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, end6, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 7){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start7, end7, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 8) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start8, end8, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 9){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start9, end9, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 10){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start10, end10, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 11){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start11, end11, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 12) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start12, end12, String.valueOf(a.getNgaynhan()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
        return list;
    }

    @Override
    public List<ProductServiceCenter> thongkebantheoquy(String code, String cate, int quy) throws ParseException {
        int year = java.time.LocalDate.now().getYear();
        String start1 = String.valueOf(year) + "-01-01";
        String end1 = String.valueOf(year) + "-03-31";
        String start2 = String.valueOf(year) + "-04-01";
        String end2 = String.valueOf(year) + "-06-30";
        String start3 = String.valueOf(year) + "-07-01";
        String end3 = String.valueOf(year) + "-09-30";
        String start4 = String.valueOf(year) + "-10-01";
        String end4 = String.valueOf(year) + "-12-31";
        List<ProductServiceCenter> list = iProductServiceCenterRepository.findAll();
        list.removeIf(a -> !a.getCodeservicecenter().equals(code));
        list.removeIf(a -> !a.getCategoryproduct().equals(cate));
        list.removeIf(a -> a.getNgaytra() == null);
        if(quy == 1){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start1, end1, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 2){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, end2, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 3){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start3, end3, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(quy == 4) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start4, end4, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
        return list;
    }

    @Override
    public List<ProductServiceCenter> thongkebantheothang(String code, String cate, int thang) throws ParseException {
        int year = java.time.LocalDate.now().getYear();
        boolean isLeap = false;
        if(year % 4 == 0)//chia hết cho 4 là năm nhuận
        {
            if( year % 100 == 0)
            //nếu vừa chia hết cho 4 mà vừa chia hết cho 100 thì không phải năm nhuận
            {
                if ( year % 400 == 0)//chia hết cho 400 là năm nhuận
                    isLeap = true;
                else
                    isLeap = false;//không chia hết cho 400 thì không phải năm nhuận
            }
            else//chia hết cho 4 nhưng không chia hết cho 100 là năm nhuận
                isLeap = true;
        }
        else {
            isLeap = false;
        }
        String start1 = String.valueOf(year) + "-01-01";
        String end1 = String.valueOf(year) + "-01-31";
        String start2 = String.valueOf(year) + "-02-01";
        String end2 = String.valueOf(year) + "-02-28";
        if(isLeap){
            end2 = String.valueOf(year) + "-02-29";
        }
        String start3 = String.valueOf(year) + "-03-01";
        String end3 = String.valueOf(year) + "-03-31";
        String start4 = String.valueOf(year) + "-04-01";
        String end4 = String.valueOf(year) + "-04-30";
        String start5 = String.valueOf(year) + "-05-01";
        String end5 = String.valueOf(year) + "-05-31";
        String start6 = String.valueOf(year) + "-06-01";
        String end6 = String.valueOf(year) + "-06-30";
        String start7 = String.valueOf(year) + "-07-01";
        String end7 = String.valueOf(year) + "-07-31";
        String start8 = String.valueOf(year) + "-08-01";
        String end8 = String.valueOf(year) + "-08-31";
        String start9 = String.valueOf(year) + "-09-01";
        String end9 = String.valueOf(year) + "-09-30";
        String start10 = String.valueOf(year) + "-10-01";
        String end10 = String.valueOf(year) + "-10-31";
        String start11= String.valueOf(year) + "-11-01";
        String end11 = String.valueOf(year) + "-11-30";
        String start12= String.valueOf(year) + "-12-01";
        String end12 = String.valueOf(year) + "-12-31";
        List<ProductServiceCenter> list = iProductServiceCenterRepository.findAll();
        list.removeIf(a -> !a.getCodeagency().equals(code));
        list.removeIf(a -> !a.getCategoryproduct().equals(cate));
        list.removeIf(a -> a.getNgaytra() == null);
        if(thang == 1){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start1, end1, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 2){
            String finalEnd = end2;
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, finalEnd, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 3){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start3, end3, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 4) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start4, end4, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 5){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start5, end5, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 6){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start2, end6, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 7){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start7, end7, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 8) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start8, end8, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 9){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start9, end9, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 10){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start10, end10, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 11){
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start11, end11, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } else if(thang == 12) {
            list.removeIf(a -> {
                try {
                    return !Utils.checkDate(start12, end12, String.valueOf(a.getNgaytra()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
        return list;
    }
}
