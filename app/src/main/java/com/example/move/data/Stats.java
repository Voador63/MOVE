package com.example.move.data;

import com.orm.SugarRecord;

import java.util.List;

public class Stats extends SugarRecord {

    private String id;
    private double dist_totale;
    private double vitesse_max;
    private double deniv_pos;
    private double deniv_neg;

    /* SUGARORM : OBLIGATOIRE */
    public Stats(){
    }

    public Stats(String id, double dist_totale, double vitesse_max, double deniv_pos, double deniv_neg){
        setId(id);
        setDist_totale(dist_totale);
        setVitesse_max(vitesse_max);
        setDeniv_pos(deniv_pos);
        setDeniv_neg(deniv_neg);
    }

    private void setId(String id) {
        this.id = id;
    }

    public void setDist_totale(double dist_totale) {
        this.dist_totale = dist_totale;
    }

    public void setVitesse_max(double vitesse_max) {
        this.vitesse_max = vitesse_max;
    }

    public void setDeniv_pos(double deniv_pos) {
        this.deniv_pos = deniv_pos;
    }

    public void setDeniv_neg(double deniv_neg) {
        this.deniv_neg = deniv_neg;
    }

    public double getDist_totale() {
        return dist_totale;
    }

    public double getVitesse_max() {
        return vitesse_max;
    }

    public double getDeniv_pos() {
        return deniv_pos;
    }

    public double getDeniv_neg() {
        return deniv_neg;
    }
}
