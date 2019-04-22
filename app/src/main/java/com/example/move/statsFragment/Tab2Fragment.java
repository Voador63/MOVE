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

        TextView dist = (TextView) view.findViewById(R.id.textDistance);
        dist.setText(Double.toString(StatsDAO.selectAll().get(0).getDist_totale())+"m");

        TextView vit = (TextView) view.findViewById(R.id.textVitesse);
        vit.setText(Double.toString(StatsDAO.selectAll().get(0).getVitesse_max())+"km/h");

        TextView denivPos = (TextView) view.findViewById(R.id.textDenivPos);
        denivPos.setText(Double.toString(StatsDAO.selectAll().get(0).getDeniv_pos())+"m");

        TextView denivNeg = (TextView) view.findViewById(R.id.textDenivNeg);
        denivNeg.setText(Double.toString(StatsDAO.selectAll().get(0).getDeniv_neg()) + "m");


        return view;
    }

}
