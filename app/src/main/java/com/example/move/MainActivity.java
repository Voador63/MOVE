package com.example.move;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.move.data.Succes;
import com.example.move.data.SuccesDAO;
import com.example.move.fragmentSucces.SectionsPageAdapter;
import com.example.move.fragmentSucces.Tab3Fragment;
import com.example.move.map.MapsFragment;
import com.example.move.statsFragment.Tab2Fragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAB = "MainActivity";
    private static final String LA = "AH";

    private static final int nb_succes = 3;


    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Succes.deleteAll(Succes.class);

        String[] t_succes;
        int idSucces;
        int idArray;
        String titreSucces;
        String descritptionSucces;

        for(int i=0; i<nb_succes; i++){

            int noSucces = i+1;
            idArray = this.getResources().
                    getIdentifier("succes"+noSucces, "array", this.getPackageName());
            t_succes = getResources().getStringArray(idArray);

            idSucces = Integer.parseInt(t_succes[0]);
            titreSucces = t_succes[1];
            descritptionSucces = t_succes[2];

            Succes succes = new Succes(idSucces,titreSucces,descritptionSucces,false);

            if(!SuccesDAO.dejaPresent(titreSucces)){
                succes.save();
            }
        }

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //set up view pager with sections adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapsFragment(), getResources().getString(R.string.fragment1));
        adapter.addFragment(new Tab2Fragment(), getResources().getString(R.string.fragment2));
        adapter.addFragment(new Tab3Fragment(), getResources().getString(R.string.fragment3));
        viewPager.setAdapter(adapter);

    }
}
