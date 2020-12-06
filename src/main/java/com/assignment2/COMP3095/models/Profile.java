package com.assignment2.COMP3095.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@SequenceGenerator(name = "profile_id_seq", initialValue = 3, allocationSize = 100)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    private int id;

    @NotNull
    private int clientId;

    @NotNull
    private String clientFirstName;

    @NotNull
    private String clientLastName;

    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\d+\\s[A-z]+\\s[A-z]+$", message = "Invalid Address - (ex: 123 Example Street)")
    private String address;

    @NotNull
    @Size(min = 2, message = "City should be more than 2 characters.")
    private String city;

    @NotNull
    private String country;

    @NotNull
    @Pattern(regexp = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")
    private String postalCode;

    private Boolean prefShipping;

    private Boolean prefBilling;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getPrefShipping() {
        return prefShipping;
    }

    public void setPrefShipping(Boolean prefShipping) {
        this.prefShipping = prefShipping;
    }

    public Boolean getPrefBilling() {
        return prefBilling;
    }

    public void setPrefBilling(Boolean prefBilling) {
        this.prefBilling = prefBilling;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", clientId=" + clientFirstName +
                ", clientId=" + clientLastName +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", prefShipping=" + prefShipping +
                ", prefBilling=" + prefBilling +
                '}';
    }
}
