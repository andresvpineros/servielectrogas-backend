package co.com.msservielectrogas.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import co.com.msservielectrogas.enums.EClientType;

@Entity
@Table(name = "clients")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String document;

    @Column(nullable = false, unique = false)
    private String names;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = true)
    private String phone1;

    @Column(nullable = true)
    private String phone2;

    @Column(nullable = true, unique = false)
    private String email;

    @Column(nullable = false, unique = false)
    private String address;

    @Column(nullable = false, unique = false)
    private Integer type;

    @Column(nullable = true)
    private String numberOrderVendor;

    @Column(nullable = true)
    private String nameOfApplicant;

    public Clients() {
    }

    public Clients(Long id, String document, String names, String phone, String phone1, String phone2, String email, String address, Integer type, String numberOrderVendor, String nameOfApplicant) {
        this.id = id;
        this.document = document;
        this.names = names;
        this.phone = phone;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.type = type;
        this.numberOrderVendor = numberOrderVendor;
        this.nameOfApplicant = nameOfApplicant;
    }

    // Getters y setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNumberOrderVendor() {
        return numberOrderVendor;
    }

    public void setNumberOrderVendor(String numberOrderVendor) {
        this.numberOrderVendor = numberOrderVendor;
    }

    public String getNameOfApplicant() {
        return nameOfApplicant;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }
}