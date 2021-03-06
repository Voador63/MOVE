package com.example.move.statsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.move.R;
import com.example.move.data.StatsDAO;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        TextView dist = (TextView) getView().findViewById(R.id.textDistance);
        double val = (double)Math.round(StatsDAO.selectAll().get(0).getDist_totale() * 100) / 100;
        dist.setText(Double.toString(val)+"m");

        val = (double)Math.round(StatsDAO.selectAll().get(0).getVitesse_max() * 100) / 100;
        TextView vit = (TextView) getView().findViewById(R.id.textVitesse);
        vit.setText(Double.toString(val)+"km/h");

        val = (double)Math.round(StatsDAO.selectAll().get(0).getDeniv_pos() * 100) / 100;
        TextView denivPos = (TextView) getView().findViewById(R.id.textDenivPos);
        denivPos.setText(Double.toString(val)+"m");

        val = (double)Math.round(StatsDAO.selectAll().get(0).getDeniv_neg() * 100) / 100;
        TextView denivNeg = (TextView) getView().findViewById(R.id.textDenivNeg);
        denivNeg.setText(Double.toString(val) + "m");
    }

}
