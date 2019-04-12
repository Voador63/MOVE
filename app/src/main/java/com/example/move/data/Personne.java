package com.example.move.data;

import com.orm.SugarRecord;

import java.util.List;

public class Personne extends SugarRecord {

    private String pseudo;
    private int score;
    private List<Succes> lst_succes;

    /* SUGARORM : OBLIGATOIRE */
    public Personne(){
    }

    public Personne(String pseudo, int score, List<Succes> lst_succes){
        setPseudo(pseudo);
        setScore(score);
        setLst_succes(lst_succes);
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLst_succes(List<Succes> lst_succes) {
        this.lst_succes = lst_succes;
    }

    public List<Succes> getLst_succes() {
        return lst_succes;
    }
}
