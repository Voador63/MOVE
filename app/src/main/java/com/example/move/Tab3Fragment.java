package com.example.move;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        //final LinearLayout linearLayout1 = view.findViewById(R.id.succes1);

        //succes controller
        final TextView txtInfos1 = view.findViewById(R.id.succesInfos1);
        txtInfos1.setVisibility(View.GONE);

        final Button btnInfo1 = view.findViewById(R.id.button);
        btnInfo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MoreInfo(txtInfos1);
            }
        });

        //succes2 controller
        final TextView txtInfos2 = view.findViewById(R.id.succesInfos2);
        txtInfos2.setVisibility(View.GONE);

        final Button btnInfo2 = view.findViewById(R.id.button2);
        btnInfo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MoreInfo(txtInfos2);
            }
        });

        //succes3 controller
        final TextView txtInfos3 = view.findViewById(R.id.succesInfos3);
        txtInfos3.setVisibility(View.GONE);

        final Button btnInfo3 = view.findViewById(R.id.button3);
        btnInfo3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MoreInfo(txtInfos3);
            }
        });

        return view;
    }

    private void MoreInfo(TextView text)
    {
        if (text.getVisibility() == View.GONE) {
            text.setVisibility(View.VISIBLE);
        }
        else {
            text.setVisibility(View.GONE);
        }
    }

}
