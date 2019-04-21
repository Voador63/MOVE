package com.example.move;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.move.data.Succes;
import com.example.move.data.SuccesDAO;
import com.example.move.map.MapsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAB = "MainActivity";

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
        adapter.addFragment(new Tab1Fragment(), getResources().getString(R.string.fragment1));
        adapter.addFragment(new Tab2Fragment(), getResources().getString(R.string.fragment2));
        adapter.addFragment(new Tab3Fragment(), getResources().getString(R.string.fragment3));
        viewPager.setAdapter(adapter);

    }

    private void setSuccesBD(){
        //Succes.deleteAll(Succes.class);


        try{

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //récupération d'une instance de la classe "DocumentBuilderFactory"
            final DocumentBuilder builder = factory.newDocumentBuilder(); //création d'un parseur

            Log.i("PATATE", String.valueOf(this.getFileStreamPath("succes.xml")));
            final File xml = new File("/data/user/0/com.example.move/files/succes.xml");

            final Document document = builder.parse(xml); //création d'un Document

            final NodeList racineNoeuds = document.getElementsByTagName("succes");

            final int nbSucces = racineNoeuds.getLength(); //nombre de noeuds
            
            for (int i=0; i<nbSucces; i++){
                Log.i("PATATE", racineNoeuds.item(i).getNodeName());

                NodeList nodeSucces = racineNoeuds.item(i).getChildNodes();
                int id = Integer.parseInt(nodeSucces.item(0).getNodeValue());
                String titre = nodeSucces.item(1).getNodeValue();
                String description = nodeSucces.item(2).getNodeValue();

                Succes succes = new Succes(id,titre,description,false);
                if(!SuccesDAO.dejaPresent(titre)){
                    succes.save();
                }
            }

        }
        catch (final ParserConfigurationException e){
            e.printStackTrace();
            Log.i("PATATE","parser erreur");
        }
        catch (final SAXException e){
            e.printStackTrace();
            Log.i("PATATE","sax erreur");
        }
        catch (final IOException e){
            e.printStackTrace();
            Log.i("PATATE","io erreur");
        }
    }
}
