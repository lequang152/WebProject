package productionmove.btl.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private Date date;
    @Column
    private String category;
    @Column
    private String categoryProduct;
    @Column
    private int quantity;
    @Column
    private int price;
    @Column
    private int total;
    @Column
    private String codeagency;

    public String getCodeagency() {
        return codeagency;
    }

    public void setCodeagency(String codeagency) {
        this.codeagency = codeagency;
    }

    public Customer(String code, String name, String address, String phone, Date date, String category, String categoryProduct, int quantity, int price, int total) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.category = category;
        this.categoryProduct = categoryProduct;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Customer(Long id, String code, String name, String address, String phone, Date date, String category, String categoryProduct, int quantity, int price, int total) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.category = category;
        this.categoryProduct = categoryProduct;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Customer() {
    }

    public Customer(Long id, String code, String name, String address, String phone) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
