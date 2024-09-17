package com.example.project;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Repa> repa_list=new ArrayList<>();

    public Menu(ArrayList listm){
        this.repa_list=listm;
    }
    public Menu(){

    }

    public ArrayList<Repa> getRepa_list() {
        return repa_list;
    }

    public void addRepa(Repa repa){
        this.repa_list.add(repa);
    }

    public void deleteRepa(Repa repa){
        this.repa_list.remove(repa);
    }
}
