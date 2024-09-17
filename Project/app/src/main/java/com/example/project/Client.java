package com.example.project;

public class Client extends User {
    private String id;

    public Client(){
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Client(String firstname, String lastname, String name, Address address, String password, String id, Commandes commandes){
        super(firstname, lastname, name,address, password, id,commandes, Role.client);

    }



}

