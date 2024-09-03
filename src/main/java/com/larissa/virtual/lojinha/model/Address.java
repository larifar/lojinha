package com.larissa.virtual.lojinha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larissa.virtual.lojinha.enums.AddressType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    private String complement;

    @Column(nullable = false)
    private String neighbor;
    @Column(nullable = false)
    private String UF;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String zipcode;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType;

    //Mapeamento "muitos para um"
    @JsonIgnore //evita recursividade
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Address() {
    }

    public Address(String street, String number, String complement, String neighbor, String UF, String city, String zipcode, AddressType addressType) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighbor = neighbor;
        this.UF = UF;
        this.city = city;
        this.zipcode = zipcode;
        this.addressType = addressType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id == address.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
