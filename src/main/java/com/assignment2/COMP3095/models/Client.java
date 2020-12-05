/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 07/11/2020
 * Description: Client class. Completion of registration form creates a client object that is input in the database
 *********************************************************************************/

package com.assignment2.COMP3095.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@SequenceGenerator(name = "client_id_seq", initialValue = 3, allocationSize = 100)
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    //@OneToMany(mappedBy="card", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private long id;

    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotNull
    @Size(min = 6, max = 12, message = "Password should be between 6 and 12 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password must contain at least 1 uppercase, 1 special character and 1 number")
    private String password;

    @NotNull
    @Size(min = 2, max = 32, message = "First Name should be more than 2 characters and less than 32.")
    @Pattern(regexp = "^[A-z]+$", message = "First Name must contain only letters (a-Z)")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 32, message = "Last Name should be more than 2 characters and less than 32.")
    @Pattern(regexp = "^[A-z]+$", message = "Last Name must contain only letters (a-Z)")
    private String lastName;

    @NotNull
    private String dob;

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

    private String role;

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date of birth='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
