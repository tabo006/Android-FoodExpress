package com.example.project;

public class User extends Account{
    private Address useraddress;
    private Commandes commandes;
    public User(){}
    public User(String firstname, String lastname, String name, Address address, String password, String id, Commandes commandes, Role role){
        super(name, password, role, firstname, lastname, id);
        this.useraddress=address;
        this.commandes=commandes;
    }

    public Address getUseraddress() {
        return useraddress;
    }

    public Commandes getCommandes() {
        return commandes;
    }

    public void setCommandes(Commandes commandes) {
        this.commandes = commandes;
    }

    public void setUseraddress(Address useraddress) {
        this.useraddress = useraddress;
    }
}
