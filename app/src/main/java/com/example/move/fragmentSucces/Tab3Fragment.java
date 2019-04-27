package com.example.move.fragmentSucces;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.move.MainActivity;
import com.example.move.R;
import com.example.move.data.StatsDAO;
import com.example.move.data.Succes;
import com.example.move.data.SuccesDAO;

import java.util.ArrayList;
import java.util.List;

public class Tab3Fragment extends Fragment {

    ArrayList<SuccessDataModel> dataModels;
    //List<Succes> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        listView=(ListView)view.findViewById(R.id.list);

        dataModels= new ArrayList<>();
        List<Succes> list_succes = SuccesDAO.selectAll();
        for(int i=0; i<SuccesDAO.getNbSucces(); i++){
            int id = list_succes.get(i).getId_succes();
            String nom = list_succes.get(i).getNom();
            String description = list_succes.get(i).getDescription();
            Boolean etat = list_succes.get(i).getEtat();
            String nbObt = Integer.toString(list_succes.get(i).getNbObtentions());
            dataModels.add(new SuccessDataModel(id, nom, description, etat, nbObt));
        }

        adapter= new CustomAdapter(dataModels,getActivity().getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SuccessDataModel dataModel= dataModels.get(position);

                TextView description = (TextView) view.findViewById(R.id.descript);

                if (description.getVisibility() == View.GONE)
                    description.setVisibility(View.VISIBLE);

                else if (description.getVisibility() == View.VISIBLE)
                    description.setVisibility(View.GONE);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
