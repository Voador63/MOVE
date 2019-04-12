package com.example.move.data;

import com.orm.SugarRecord;

public class Succes extends SugarRecord {

    private String nom;
    private String description;
    private boolean etat;

    /* SUGARORM : OBLIGATOIRE */
    public Succes(){
    }

    public Succes(String nom, String description, boolean etat){

        setNom(nom);
        setDescription(description);
        setEtat(etat);
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setEtat(boolean etat){
        this.etat = etat;
    }

    public boolean getEtat(){
        return this.etat;
    }
}
