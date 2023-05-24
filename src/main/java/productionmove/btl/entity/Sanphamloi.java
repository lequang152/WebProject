package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "sanphamloi")
public class Sanphamloi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;
    private String categoryproduct;
    private String codewarehouse;
    private String codeagency;
    private String codeservicecenter;
    private String nameservicecenter;
    private Date ngaysanxuat;
    private Date ngaynhan;
    private String nameerror;

    public String getNameservicecenter() {
        return nameservicecenter;
    }

    public void setNameservicecenter(String nameservicecenter) {
        this.nameservicecenter = nameservicecenter;
    }

    public String getNameerror() {
        return nameerror;
    }

    public void setNameerror(String nameerror) {
        this.nameerror = nameerror;
    }

    public Sanphamloi() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryproduct() {
        return categoryproduct;
    }

    public void setCategoryproduct(String categoryproduct) {
        this.categoryproduct = categoryproduct;
    }

    public String getCodewarehouse() {
        return codewarehouse;
    }

    public void setCodewarehouse(String codewarehouse) {
        this.codewarehouse = codewarehouse;
    }

    public String getCodeagency() {
        return codeagency;
    }

    public void setCodeagency(String codeagency) {
        this.codeagency = codeagency;
    }

    public String getCodeservicecenter() {
        return codeservicecenter;
    }

    public void setCodeservicecenter(String codeservicecenter) {
        this.codeservicecenter = codeservicecenter;
    }

    public Date getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(Date ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public Date getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(Date ngaynhan) {
        this.ngaynhan = ngaynhan;
    }
}
