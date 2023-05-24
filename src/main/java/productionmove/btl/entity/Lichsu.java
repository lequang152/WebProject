package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="lichsu")
public class Lichsu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String codewarehouse;
    private String namewarehouse;
    private String codeagency;
    private String nameagency;
    private Date date;
    private String categoryproduct;
    private int quantity;
    private String status;

    public String getNamewarehouse() {
        return namewarehouse;
    }

    public void setNamewarehouse(String namewarehouse) {
        this.namewarehouse = namewarehouse;
    }

    public String getNameagency() {
        return nameagency;
    }

    public void setNameagency(String nameagency) {
        this.nameagency = nameagency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Lichsu() {
    }

    public Lichsu(Long id, String codewarehouse, String codeagency, Date date, String categoryproduct, int quantity) {
        this.id = id;
        this.codewarehouse = codewarehouse;
        this.codeagency = codeagency;
        this.date = date;
        this.categoryproduct = categoryproduct;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategoryproduct() {
        return categoryproduct;
    }

    public void setCategoryproduct(String categoryproduct) {
        this.categoryproduct = categoryproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
