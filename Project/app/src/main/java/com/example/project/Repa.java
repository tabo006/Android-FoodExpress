package com.example.project;

public class Repa {
    private String nom;
    private String typerepas;
    private String typecuisine;
    private String ingredients;
    private String allergene;
    private String prix;
    private String description;
    private String id;
    private String offered;

    public Repa(){

    }
    public Repa(String nom,String typerepas,String typecuisine,String ingredients,String allergene,String prix,String description, String id ){
        this.allergene= allergene;
        this.description=description;
        this.nom=nom;
        this.prix=prix;
        this.ingredients=ingredients;
        this.typerepas=typerepas;
        this.typecuisine=typecuisine;
        this.id=id;

    }

    public String getAllergene() {
        return allergene;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public String getPrix() {
        return prix;
    }

    public String getTypecuisine() {
        return typecuisine;
    }

    public String getTyperepas() {
        return typerepas;
    }

    public String getOffered() {
        return offered;
    }

    public void setOffered(String offered) {
        this.offered = offered;
    }

    public void setAllergene(String allergene) {
        this.allergene = allergene;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setTypecuisine(String typecuisine) {
        this.typecuisine = typecuisine;
    }

    public void setTyperepas(String typerepas) {
        this.typerepas = typerepas;
    }

    public void setId(String id) {
        this.id = id;
    }
}

