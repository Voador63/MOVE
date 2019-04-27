package com.example.move.data;

import com.orm.SugarRecord;

public class Succes extends SugarRecord {

    private int id_succes;
    private String nom;
    private String description;
    private boolean etat;
    private int nb_obtentions;

    /* SUGARORM : OBLIGATOIRE */
    public Succes(){
    }

    public Succes(int id_succes, String nom, String description, boolean etat, int nb_obtentions){

        setId_succes(id_succes);
        setNom(nom);
        setDescription(description);
        setEtat(etat);
        setNb_obtentions(nb_obtentions);
    }

    public void setId_succes(int id_succes) {
        this.id_succes = id_succes;
    }

    public int getId_succes() {
        return id_succes;
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

    public void setNb_obtentions(int nb_obtentions) {
        this.nb_obtentions = nb_obtentions;
    }

    public int getNb_obtentions() {
        return nb_obtentions;
    }
}
