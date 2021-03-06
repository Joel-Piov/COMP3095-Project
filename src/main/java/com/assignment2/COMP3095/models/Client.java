/*********************************************************************************
 * Project: COMP3095 - Assignment 3
 * Assignment: Assignment 3
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 06/12/2020
 * Description: Client class. Completion of registration form creates a Client
 * object that is input in the database
 *********************************************************************************/

package com.assignment2.COMP3095.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "client_id_seq", initialValue = 4, allocationSize = 100)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    private int id;

    @NotEmpty(message = "Field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 6, max = 12, message = "Password should be between 6 and 12 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password must contain at least 1 uppercase, 1 special character and 1 number")
    private String password;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 32, message = "First Name should be more than 2 characters and less than 32.")
    @Pattern(regexp = "^[A-z]+$", message = "First Name must contain only letters (a-Z)")
    private String firstName;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 32, message = "Last Name should be more than 2 characters and less than 32.")
    @Pattern(regexp = "^[A-z]+$", message = "Last Name must contain only letters (a-Z)")
    private String lastName;

    @NotEmpty(message = "Field should not be empty")
    private String dateOfBirth;

    @NotEmpty(message = "Field should not be empty")
    @Pattern(regexp = "^\\d+\\s[A-z]+\\s[A-z]+$", message = "Invalid Address - (ex: 123 Example Street)")
    private String address;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, message = "City should be more than 2 characters.")
    private String city;

    @NotEmpty(message = "Field should not be empty")
    private String country;

    @NotEmpty(message = "Field should not be empty")
    @Pattern(regexp = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$")
    private String postalCode;

    private LocalDate registerDate;

    private LocalDate lastLoginDate;

    private LocalDate lastProfileUpdate;

    private String role;

    public int getId() {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDate getLastProfileUpdate() {
        return lastProfileUpdate;
    }

    public void setLastProfileUpdate(LocalDate lastProfileUpdate) {
        this.lastProfileUpdate = lastProfileUpdate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date of birth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
