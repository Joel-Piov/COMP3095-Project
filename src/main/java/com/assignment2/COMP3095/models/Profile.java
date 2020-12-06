package com.assignment2.COMP3095.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int clientId;

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

    @NotNull
    private Boolean prefShipping;

    @NotNull
    private Boolean prefBilling;


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

    public String getPostalCode() {
        return postalCode;
    }

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

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", clientId=" + clientId +
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
