package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product_warehouse")
public class ProductWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryproduct;
    private String category;
    private String codewarehouse;
    private String code;
    private Date ngaysanxuat;
    private Date ngayxuatkho;
    private String codeagency;

    public ProductWarehouse() {
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

    public String getCodewarehouse() {
        return codewarehouse;
    }

    public void setCodewarehouse(String codewarehouse) {
        this.codewarehouse = codewarehouse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(Date ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public Date getNgayxuatkho() {
        return ngayxuatkho;
    }

    public void setNgayxuatkho(Date ngayxuatkho) {
        this.ngayxuatkho = ngayxuatkho;
    }

    public String getCodeagency() {
        return codeagency;
    }

    public void setCodeagency(String codeagency) {
        this.codeagency = codeagency;
    }
}
