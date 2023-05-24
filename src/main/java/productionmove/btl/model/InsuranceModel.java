package productionmove.btl.model;

import jakarta.persistence.*;

import java.sql.Date;


public class InsuranceModel {

    private Long id;

    private String code;

    private String code_service_center;

    private String code_product;

    private String name_error;

    private Date date_of_begin;

    private Date date_of_end;

    private String code_agency;

    private int quantity;

    public InsuranceModel() {
    }

    public InsuranceModel(Long id, String code, String code_service_center, String code_product, String name_error, Date date_of_begin, Date date_of_end, String code_agency, int quantity) {
        this.id = id;
        this.code = code;
        this.code_service_center = code_service_center;
        this.code_product = code_product;
        this.name_error = name_error;
        this.date_of_begin = date_of_begin;
        this.date_of_end = date_of_end;
        this.code_agency = code_agency;
        this.quantity = quantity;
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

    public String getCode_service_center() {
        return code_service_center;
    }

    public void setCode_service_center(String code_service_center) {
        this.code_service_center = code_service_center;
    }

    public String getCode_product() {
        return code_product;
    }

    public void setCode_product(String code_product) {
        this.code_product = code_product;
    }

    public String getName_error() {
        return name_error;
    }

    public void setName_error(String name_error) {
        this.name_error = name_error;
    }

    public Date getDate_of_begin() {
        return date_of_begin;
    }

    public void setDate_of_begin(Date date_of_begin) {
        this.date_of_begin = date_of_begin;
    }

    public Date getDate_of_end() {
        return date_of_end;
    }

    public void setDate_of_end(Date date_of_end) {
        this.date_of_end = date_of_end;
    }

    public String getCode_agency() {
        return code_agency;
    }

    public void setCode_agency(String code_agency) {
        this.code_agency = code_agency;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
