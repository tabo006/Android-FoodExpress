package com.example.project;

public class Cuisinier extends User{
    private Menu menu;
    private String bio;
    private Rating rating;


    public Cuisinier(){

    }

    public Cuisinier(String firstname, String lastname, String name, Address address, String password, String id, Menu menu, String bio, Rating rating,Commandes commandes){
        super(firstname, lastname, name,address, password, id,commandes, Role.cuisiner);
        this.menu=menu;
        this.bio=bio;
        this.rating=rating;

    }



    public String getBio() {
        return bio;
    }


    public Menu getMenu() {
        return menu;
    }

    public Rating getRating() {
        return rating;
    }
}