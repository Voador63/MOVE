package com.example.move.data;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.example.move.MainActivity;
import com.example.move.fragmentSucces.CustomAdapter;
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

    public static void setSucces(MainActivity activity, double dist, double vit, double denivP, double denivN){

        List<Succes> listSucces = Succes.find(Succes.class, "nom = ?", "Promeneur débutant");
        Succes succes = listSucces.get(0);
        if (dist>=10 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }

        listSucces = Succes.find(Succes.class, "nom = ?", "Alpiniste en devenir");
        succes = listSucces.get(0);
        if (denivP>=10 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }

        listSucces = Succes.find(Succes.class, "nom = ?", "Coureur débutant");
        succes = listSucces.get(0);
        if (vit>=15 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }

        listSucces = Succes.find(Succes.class, "nom = ?", "Descendeur débutant");
        succes = listSucces.get(0);
        if (denivN>=10 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }

        listSucces = Succes.find(Succes.class, "nom = ?", "Promeneur régulier");
        succes = listSucces.get(0);
        if (dist>=100 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }

        listSucces = Succes.find(Succes.class, "nom = ?", "Promeneur confirmé");
        succes = listSucces.get(0);
        if (dist>=1000 && !succes.getEtat()){
            succes.setEtat(true);
            succes.setNb_obtentions(succes.getNb_obtentions()+1);
            succes.save();
            activity.sendNotif(succes.getNom());
        }
    }

    public static void resetSuccesEtat(){
        List<Succes> listSucces = selectAll();
        for(Succes succes : listSucces){
            succes.setEtat(false);
        }
    }
}
