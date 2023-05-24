package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product_agency")
public class ProductAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryproduct;
    private String category;
    private String codeagency;
    private String code;
    private Date ngaynhap;
    private Date ngayban;
    private Date ngaysanxuat;
    private String codecustomer;
    private String codewarehouse;

    public Date getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(Date ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public String getCodewarehouse() {
        return codewarehouse;
    }

    public void setCodewarehouse(String codewarehouse) {
        this.codewarehouse = codewarehouse;
    }

    public ProductAgency() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryproduct() {
        return categoryproduct;
    }

    public void setCategoryproduct(String categoryproduct) {
        this.categoryproduct = categoryproduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCodeagency() {
        return codeagency;
    }

    public void setCodeagency(String codeagency) {
        this.codeagency = codeagency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public Date getNgayban() {
        return ngayban;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    public String getCodecustomer() {
        return codecustomer;
    }

    public void setCodecustomer(String codecustomer) {
        this.codecustomer = codecustomer;
    }
}
