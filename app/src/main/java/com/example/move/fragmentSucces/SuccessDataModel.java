package com.example.move.fragmentSucces;

public class SuccessDataModel {
    int id_succes;
    String nom_succes;
    String description_succes;
    boolean etat_succes;

    public SuccessDataModel(int id, String nom, String description, boolean etat ) {
        this.id_succes=id;
        this.nom_succes=nom;
        this.description_succes=description;
        this.etat_succes=etat;
    }
    public int getId() {
        return id_succes;
    }
    public String getNom() {
        return nom_succes;
    }
    public String getDescription() {
        return description_succes;
    }
    public Boolean getEtat() {
        return etat_succes;
    }
}
