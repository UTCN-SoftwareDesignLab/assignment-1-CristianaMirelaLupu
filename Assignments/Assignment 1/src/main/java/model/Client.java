package model;

public class Client {

    private Long id;
    private String name;
    private String ssn;
    private String address;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return Long.toString(this.id) + " - " + this.name + " - " + address;
    }

}
