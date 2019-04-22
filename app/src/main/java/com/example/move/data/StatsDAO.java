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

    public static  boolean dejaPresent(String id){
        boolean present = false;

        List<Stats> test = Stats.find(Stats.class, "id = ?", id);
        if(test.size() != 0){
            present = true;
        }

        return present;
    }

    public static void setStats(double dist, double vit, double denivP, double denivN){
        for (Stats stat:selectAll()){
            stat.setDist_totale(stat.getDist_totale()+dist);
            if (vit > stat.getVitesse_max())
                stat.setVitesse_max(vit);

            stat.setDeniv_pos(stat.getDeniv_pos()+denivP);
            stat.setDeniv_neg(stat.getDeniv_neg()+denivN);

            stat.save();
        }
    }



}
