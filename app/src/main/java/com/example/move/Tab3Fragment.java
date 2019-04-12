package com.example.move;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.move.CustomAdapter;
import com.example.move.SuccessDataModel;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {

    ArrayList<SuccessDataModel> dataModels;
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

        dataModels.add(new SuccessDataModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
        dataModels.add(new SuccessDataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));
        dataModels.add(new SuccessDataModel("Cupcake", "Android 1.5", "3","April 27, 2009"));
        dataModels.add(new SuccessDataModel("Donut","Android 1.6","4","September 15, 2009"));
        dataModels.add(new SuccessDataModel("Eclair", "Android 2.0", "5","October 26, 2009"));
        dataModels.add(new SuccessDataModel("Froyo", "Android 2.2", "8","May 20, 2010"));
        dataModels.add(new SuccessDataModel("Gingerbread", "Android 2.3", "9","December 6, 2010"));
        dataModels.add(new SuccessDataModel("Honeycomb","Android 3.0","11","February 22, 2011"));
        dataModels.add(new SuccessDataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
        dataModels.add(new SuccessDataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
        dataModels.add(new SuccessDataModel("Kitkat", "Android 4.4", "19","October 31, 2013"));
        dataModels.add(new SuccessDataModel("Lollipop","Android 5.0","21","November 12, 2014"));
        dataModels.add(new SuccessDataModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));

        adapter= new CustomAdapter(dataModels,getActivity().getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SuccessDataModel dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });

        return view;
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
    //    return true;
    //}

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
}
