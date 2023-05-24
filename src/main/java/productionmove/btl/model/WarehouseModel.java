package productionmove.btl.model;

import jakarta.persistence.*;


public class WarehouseModel {

    private Long id;

    private String code;

    private String name;

    private String address;

    private String manager;

    private String phone;

    public WarehouseModel() {
    }

    public WarehouseModel(Long id, String code, String name, String address, String manager, String phone) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.manager = manager;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
