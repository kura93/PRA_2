package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=Doc.class)
@Entity
@Table(name = "DOCTORS")
public class Doctor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name= "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients = new ArrayList<Patient>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;



    public Doctor() {}
    public Doctor(String name, String surname, String specialization) {
        this.name =  name;
        this.surname = surname;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient obj) {
        this.patients.add(obj);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
