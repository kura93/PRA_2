package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=Patient.class)
@Entity
@Table(name = "PATIENTS")
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_day")
    private String birth_day;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Doctor> doctors = new ArrayList<Doctor>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;


    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Patient(String name, String surname, Date birth_day) {
        this.name = name;
        this.surname = surname;
        this.birth_day = birth_day.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String toString() {
        System.out.println(this.name + " " + this.surname);
        for(Doctor doc : doctors) {
            System.out.println(doc.getName()+ "----");
        }
        return "siabadaba";
    }
}
