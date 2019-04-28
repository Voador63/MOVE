package com.example.move.data;

import android.util.Log;

import com.orm.SugarRecord;

import java.util.List;

public class Trajet extends SugarRecord {

    private List<Point> trajet;

    /* SUGARORM : OBLIGATOIRE */
    public Trajet(){
    }

    public Trajet(List<Point> trajet){
        setTrajet(trajet);
    }

    public void setTrajet(List<Point> trajet) {
        this.trajet = trajet;
    }

    public List<Point> getTrajet() {
        return trajet;
    }

    public void add_point(Point point){
        List<Point> lst_point = getTrajet();
        Log.i("monLOG", point.toString());
        lst_point.add(point);
    }
}
