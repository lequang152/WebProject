package productionmove.btl.entity;

import jakarta.persistence.*;

@Entity
@Table(name="service_center")
public class ServiceCenter {
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
    private String manager;
    @Column
    private String phone;


    public ServiceCenter() {
    }

    public ServiceCenter(Long id, String code, String name, String address, String manager, String phone) {
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
