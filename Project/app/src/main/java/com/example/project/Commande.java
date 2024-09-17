package com.example.project;

import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Commande{

   /* private String cuisinier_id;
    private String client_id;
    private ComStatus status;
    private ArrayList<Repa> order;*/



    private String name;
    private String desc;
    private String cook;
    private String id;
    private String price;
    private Repa refAuRepas;
    private ComStatus status;
    private String cookID;
    private String clientID;
    private String clientname;
    private String commandeid;

    public Commande(String name, String desc, String cook, String id, String price, Repa refAuRepas,String cookID,String clientID, String clientname){
        this.name = name;
        this.desc = desc;
        this.cook = cook;
        this.id = id;
        this.price = price;
        this.refAuRepas = refAuRepas;
        status = ComStatus.EN_ATTENTE;
        this.cookID=cookID;
        this.clientID=clientID;
        this.clientname=clientname;
    }
    public Commande(String name, String desc, String cook, String id, String price, Repa refAuRepas,String cookID,String clientID, String clientname, String commandeid){
        this.name = name;
        this.desc = desc;
        this.cook = cook;
        this.id = id;
        this.price = price;
        this.refAuRepas = refAuRepas;
        status = ComStatus.EN_ATTENTE;
        this.cookID=cookID;
        this.clientID=clientID;
        this.clientname=clientname;
        this.commandeid=commandeid;
    }
    public Commande(){

    }

    public String getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(String commandeid) {
        this.commandeid = commandeid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Repa getRefAuRepas() {
        return refAuRepas;
    }

    public String getCook() {
        return cook;
    }

    public String getDesc() {
        return desc;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientname() {
        return clientname;
    }

    public String getStatus() {
        String stat="";
        switch(status){
            case EN_ATTENTE:
                stat = "EN_ATTENTE";
                break;
            case ACCEPTÉ:
                stat = "ACCEPTÉ";
                break;
            case REJETÉ:
                stat = "REJETÉ";
                break;
        }
        return stat;
    }

    public String getCookID() {
        return cookID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public void setRefAuRepas(Repa refAuRepas) {
        this.refAuRepas = refAuRepas;
        this.name = refAuRepas.getNom();
        this.desc = refAuRepas.getDescription();
        this.price = refAuRepas.getPrix();
    }

    public void setStatus(ComStatus status) {
        this.status = status;
    }


}