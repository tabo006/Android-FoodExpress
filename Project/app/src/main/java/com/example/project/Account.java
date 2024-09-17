package com.example.project;

import android.widget.EditText;
import android.widget.RadioGroup;

public class Account {
    protected String firstName;
    protected String lastName;
    private String name;
    private String password;
    private Role role;
    private String id;
    private String status = "Active";
    public static String currentuserID;
    public Account( String name,String password,Role role, String  firstname, String lastname, String id) {
        this.password = password;
        this.role = role;
        this.firstName=firstname;
        this.lastName=lastname;
        this.id=id;
        this.name=name;

    }
    public Account() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static void setCurrentuserID(String currentuserID) {
        Account.currentuserID = currentuserID;
    }

    public static String getCurrentuserID() {
        return currentuserID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
