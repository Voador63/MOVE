package com.example.move.data;

import java.util.List;

public class PointDAO {

    public void afficherPoint(Point p){

    }

    //Retourne une liste de tous les succès
    public static List<Point> selectAll() {
        return Point.listAll(Point.class);
    }

    //Retourne le nombre de succès
    public static int getNbPoint(){
        List<Point> list = selectAll();
        return list.size();
    }
    public static boolean estVide(){
        boolean test = false;

        if(getNbPoint()==0){
            return true;
        }
        return test;
    }

    public static  boolean dejaPresent(String nom){
        boolean present = false;

        List<Succes> test = Succes.find(Succes.class, "nom = ?", nom);
        if(test.size() != 0){
            present = true;
        }

        return present;
    }
}
