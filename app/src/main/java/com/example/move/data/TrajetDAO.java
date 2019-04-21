package com.example.move.data;

import android.util.Log;

import java.util.List;

public class TrajetDAO {

    //Retourne une liste de tous les succès
    public static List<Trajet> selectAll() {
        return Trajet.listAll(Trajet.class);
    }

    public static Trajet selectTrajetById(int id){
        List<Point> points = Trajet.find(Point.class, "id_trajet = ?", String.valueOf(id));
        Trajet trajet = new Trajet(points);

        return trajet;
    }

    //Retourne le nombre de succès
    public static int getNbTrajet(){
        List<Trajet> list = selectAll();
        return list.size();
    }
    public static boolean estVide(){
        boolean test = false;

        if(getNbTrajet()==0){
            return true;
        }
        return test;
    }

    public static  boolean dejaPresent(int id){
        boolean present = false;

        Trajet test = Trajet.findById(Trajet.class, id);
        if(test != null){
            present = true;
        }

        return present;
    }

    public static int getLastId(){
        int lastid;

        boolean b = PointDAO.estVide();
        if(b){
            lastid = 0;
        }
        else {
            int nb_point = PointDAO.getNbPoint();
            Point p = Point.findById(Point.class, nb_point);

            lastid = p.getId_trajet();
        }


        return lastid;

    }
}
