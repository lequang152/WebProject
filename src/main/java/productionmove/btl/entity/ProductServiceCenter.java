package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product_servicecenter")
public class ProductServiceCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryproduct;
    private String category;
    private String codeagency;
    private String codeservicecenter;
    private String codewarehouse;
    private String code;
    private String mabaohanh;
    private Date ngaynhan;
    private Date ngaytra;
    private String codecustomer;
    private String namecustomer;
    private String phonecustomer;
    private int quantity;
    private String status;
    private String nameerror;
    private String nameagency;

    public String getNameagency() {
        return nameagency;
    }

    public void setNameagency(String nameagency) {
        this.nameagency = nameagency;
    }

    public String getCodewarehouse() {
        return codewarehouse;
    }

    public void setCodewarehouse(String codewarehouse) {
        this.codewarehouse = codewarehouse;
    }

    public String getNameerror() {
        return nameerror;
    }

    public void setNameerror(String nameerror) {
        this.nameerror = nameerror;
    }

    public String getMabaohanh() {
        return mabaohanh;
    }

    public void setMabaohanh(String mabaohanh) {
        this.mabaohanh = mabaohanh;
    }

    public ProductServiceCenter() {
    }

    public String getPhonecustomer() {
        return phonecustomer;
    }

    public void setPhonecustomer(String phonecustomer) {
        this.phonecustomer = phonecustomer;
    }

    public String getNamecustomer() {
        return namecustomer;
    }

    public void setNamecustomer(String namecustomer) {
        this.namecustomer = namecustomer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCodeservicecenter() {
        return codeservicecenter;
    }

    public void setCodeservicecenter(String codeservicecenter) {
        this.codeservicecenter = codeservicecenter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(Date ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public Date getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getCodecustomer() {
        return codecustomer;
    }

    public void setCodecustomer(String codecustomer) {
        this.codecustomer = codecustomer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
