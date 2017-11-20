package model;


import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name= "city")
    private String city;

    @Column(name= "street")
    private String street;

    @Column(name= "home_number")
    private int homeNumber;

    @Column(name= "zipcode")
    private String zipcode;

    public Address(){}
    public Address(String city, String street, int homeNumber, String zipcode) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
