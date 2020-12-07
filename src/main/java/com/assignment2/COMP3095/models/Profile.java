/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Model that creates the Profile entity for the database, providing
 * the getters and setters for its values
 *********************************************************************************/

package com.assignment2.COMP3095.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@SequenceGenerator(name = "profile_id_seq", initialValue = 4, allocationSize = 100)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    private int id;

    @NotEmpty(message = "Field should not be empty")
    private int clientId;

    @NotEmpty(message = "Field should not be empty")

    @Pattern(regexp = "^[A-z]+$", message = "First Name must contain only letters (a-Z)")
    private String clientFirstName;
    @Size(min = 2, max = 32, message = "First Name should be more than 2 characters and less than 32.")
    @NotEmpty(message = "Field should not be empty")

    @Pattern(regexp = "^[A-z]+$", message = "Last Name must contain only letters (a-Z)")
    private String clientLastName;
    @Size(min = 2, max = 32, message = "Last Name should be more than 2 characters and less than 32.")
    @NotEmpty(message = "Field should not be empty")
    private String clientDateOfBirth;

    @NotEmpty(message = "Field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotEmpty(message = "Field should not be empty")
    @Pattern(regexp = "^\\d+\\s[A-z]+\\s[A-z]+$", message = "Invalid Address - (ex: 123 Example Street)")
    private String address;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, message = "City should be more than 2 characters.")
    private String city;

    @NotEmpty(message = "Field should not be empty")
    private String country;

    @NotEmpty(message = "Email field should not be empty")
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

    public String getClientDateOfBirth() {
        return clientDateOfBirth;
    }

    public void setClientDateOfBirth(String clientDateOfBirth) {
        this.clientDateOfBirth = clientDateOfBirth;
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
