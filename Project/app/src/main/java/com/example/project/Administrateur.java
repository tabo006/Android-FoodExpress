package com.example.project;

import java.util.ArrayList;

public class Administrateur extends Account {

    private static ArrayList<Complaint> inbox;
    public Administrateur(String name, String firstname, String lastname, String password, String id){
        super(name,password,Role.administrateur,firstname,lastname,id);
    }

    public static ArrayList<Complaint> getInbox() {
        return inbox;
    }

    public static void setInbox(ArrayList<Complaint> inbox) {
        Administrateur.inbox = inbox;
    }
    public void addtoInbox(Complaint complaint){
        inbox.add(complaint);
    }
}
