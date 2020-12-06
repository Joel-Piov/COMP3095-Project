package com.assignment2.COMP3095.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int clientId;

    @NotNull
    private int adminId;

    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotNull
    @Size(min = 2, max = 32, message = "First Name should be more than 2 characters and less than 32.")
    @Pattern(regexp = "^[A-z]+$", message = "First Name must contain only letters (a-Z)")
    private String firstName;

    @NotNull
    private String subject;

    @NotNull
    @Size(min = 24, message = "Please elaborate, message too small.")
    private String message;

    private String dateAdded;
    private String caseCode;

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

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCaseCode() {
        return caseCode;
    }
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public void setId(int id) { this.id = id; }
    public int getId() {
        return id;
    }

    public String getDateAdded() { return dateAdded; }
    public void setDateAdded(String dateAdded) { this.dateAdded = dateAdded; }
}
