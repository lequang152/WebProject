package productionmove.btl.model;

public class Office {
    private String code;

    private String name;

    private String address;

    private String manager;

    private String phone;

    private String category;

    public Office(String code, String name, String address, String manager, String phone, String category) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.manager = manager;
        this.phone = phone;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Office() {
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
