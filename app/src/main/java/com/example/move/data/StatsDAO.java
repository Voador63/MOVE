package com.example.move.data;

import java.util.List;

public class StatsDAO {

    //Retourne une liste de tous les succès
    public static List<Stats> selectAll() {
        return Stats.listAll(Stats.class);
    }

    //Retourne le nombre de succès
    public static int getNbStats(){
        List<Stats> list = selectAll();
        return list.size();
    }
    public static boolean estVide(){
        boolean test = false;

        if(getNbStats()==0){
            return true;
        }
        return test;
    }



}
