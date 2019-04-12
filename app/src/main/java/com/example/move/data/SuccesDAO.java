package com.example.move.data;

import java.util.List;

public class SuccesDAO {

    //Retourne une liste de tous les succès
    public static List<Succes> selectAll() {
        return Succes.listAll(Succes.class);
    }

    //Retourne le nombre de succès
    public static int getNbSucces(){
        List<Succes> list = selectAll();
        return list.size();
    }
    public static boolean estVide(){
        boolean test = false;

        if(getNbSucces()==0){
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
