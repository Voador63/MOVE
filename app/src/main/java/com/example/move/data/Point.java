package com.example.move.data;

import com.orm.SugarRecord;

public class Point extends SugarRecord {

    private int id_trajet;
    private double altitude;
    private double latitude;
    private double longitude;
    private double vitesse;
    private double last_vitesse;

    /* SUGARORM : OBLIGATOIRE */
    public Point(){
    }

    public Point(int is_trajet, double altitude, double latitude, double longitude, double vitesse, double last_vitesse){
        setId_trajet(is_trajet);
        setAltitude(altitude);
        setLatitude(latitude);
        setLongitude(longitude);
        setVitesse(vitesse);
        setLast_vitesse(last_vitesse);
    }

    public void setId_trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setLast_vitesse(double last_vitesse) {
        this.last_vitesse = last_vitesse;
    }

    public int getId_trajet() {
        return id_trajet;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getVitesse() {
        return vitesse;
    }

    public double getLast_vitesse() {
        return last_vitesse;
    }
}
