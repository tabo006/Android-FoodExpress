package com.example.project;

import java.util.ArrayList;

public class Commandes {
    private ArrayList<Commande> commandes = new ArrayList<>();
    public Commandes(ArrayList commandes){this.commandes=commandes;};

    public Commandes(){

    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void addToCommandes(Commande commande) {
        this.commandes.add(commande);
    }

}