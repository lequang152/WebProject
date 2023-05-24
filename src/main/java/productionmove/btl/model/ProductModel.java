package productionmove.btl.model;

import jakarta.persistence.*;

import java.sql.Date;


public class ProductModel {

    private Long id;

    private String code;

    private String name;

    private Long cate_product_id;

    private String image;

    private String title;

    private String description;

    private Date date_of_manufacture; //ngày sản xuất

    private int date_of_insurance; //thời gian bảo hành

    private String code_warehouse; //mã kho

    private String code_agency; //mã đại lý

    private String code_customer; //mà khách hàng

    private String code_insurance; //mã bảo hành

    public ProductModel() {
    }

    public ProductModel(Long id, String code, String name, Long cate_product_id, String image, String title, String description, Date date_of_manufacture, int date_of_insurance, String code_warehouse, String code_agency, String code_customer, String code_insurance) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.cate_product_id = cate_product_id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.date_of_manufacture = date_of_manufacture;
        this.date_of_insurance = date_of_insurance;
        this.code_warehouse = code_warehouse;
        this.code_agency = code_agency;
        this.code_customer = code_customer;
        this.code_insurance = code_insurance;
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

    public Long getCate_product_id() {
        return cate_product_id;
    }

    public void setCate_product_id(Long cate_product_id) {
        this.cate_product_id = cate_product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_of_manufacture() {
        return date_of_manufacture;
    }

    public void setDate_of_manufacture(Date date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
    }

    public int getDate_of_insurance() {
        return date_of_insurance;
    }

    public void setDate_of_insurance(int date_of_insurance) {
        this.date_of_insurance = date_of_insurance;
    }

    public String getCode_warehouse() {
        return code_warehouse;
    }

    public void setCode_warehouse(String code_warehouse) {
        this.code_warehouse = code_warehouse;
    }

    public String getCode_agency() {
        return code_agency;
    }

    public void setCode_agency(String code_agency) {
        this.code_agency = code_agency;
    }

    public String getCode_customer() {
        return code_customer;
    }

    public void setCode_customer(String code_customer) {
        this.code_customer = code_customer;
    }

    public String getCode_insurance() {
        return code_insurance;
    }

    public void setCode_insurance(String code_insurance) {
        this.code_insurance = code_insurance;
    }
}
