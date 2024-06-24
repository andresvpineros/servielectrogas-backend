package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.EClientType;

public class ClientDTO {
	
    private Long id;
    private String document;
    private String names;
    private String phone;
    private String phone1;
    private String phone2;
    private String email;
    private String address;
    private String nameOfApplicant;
    private String numberOrderVendor;
    private EClientType typeName;
    private Integer type;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String document, String names, String phone, String phone1, String phone2, String email,
                     String address, String nameOfApplicant, String numberOrderVendor, EClientType typeName, Integer type) {
        this.id = id;
        this.document = document;
        this.names = names;
        this.phone = phone;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.nameOfApplicant = nameOfApplicant;
        this.numberOrderVendor = numberOrderVendor;
        this.typeName = typeName;
        this.type = type;
    }

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

    public String getNameOfApplicant() {
        return nameOfApplicant;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }

    public String getNumberOrderVendor() {
        return numberOrderVendor;
    }

    public void setNumberOrderVendor(String numberOrderVendor) {
        this.numberOrderVendor = numberOrderVendor;
    }

    public EClientType getTypeName() {
        return typeName;
    }

    public void setTypeName(EClientType typeName) {
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}