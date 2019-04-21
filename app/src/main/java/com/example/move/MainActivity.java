package com.example.move;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.move.data.Succes;
import com.example.move.data.SuccesDAO;
import com.example.move.map.MapsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAB = "MainActivity";
    private static final String LA = "AH";

    private static final int nb_succes = 1;


    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Succes.deleteAll(Succes.class);

        String[] t_succes = getResources().getStringArray(R.array.succes1);

        int id = Integer.parseInt(t_succes[0]);
        String titre = t_succes[1];
        String description = t_succes[2];

        Succes succes = new Succes(id,titre,description,false);
        if(!SuccesDAO.dejaPresent(titre)){
            succes.save();
        }

       /* for(int i=0; i<nb_succes; i++){
            //String name_succes = "succes"+Integer.toString(i);

        }*/


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.share_button:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your Body Here";
                String shareSubject = "Subject";

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share Using"));

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
