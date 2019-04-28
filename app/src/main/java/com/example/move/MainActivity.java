package com.example.move;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.move.data.Stats;
import com.example.move.data.StatsDAO;
import com.example.move.data.Succes;
import com.example.move.data.SuccesDAO;
import com.example.move.fragmentSucces.SectionsPageAdapter;
import com.example.move.fragmentSucces.Tab3Fragment;
import com.example.move.map.MapsFragment;
import com.example.move.statsFragment.Tab2Fragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAB = "MainActivity";

    private static final int nb_succes = 6;

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private NotificationManager nm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Create succes DataBase with succes.xml
        //Succes.deleteAll(Succes.class);

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

            idSucces = getResources().getIdentifier(t_succes[0],"mipmap",getPackageName());
            titreSucces = t_succes[1];
            descritptionSucces = t_succes[2];

            Succes succes = new Succes(idSucces,titreSucces,descritptionSucces,false,0);

            if(!SuccesDAO.dejaPresent(titreSucces)){
                succes.save();
            }
        }

        //Create Stats
        //Stats.deleteAll(Stats.class);
        if(!StatsDAO.dejaPresent("0")){
            Stats stats = new Stats("0",0,0,0,0);
            stats.save();
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

    public void sendNotif(String nom){
        createNotificationChannel();

        // Create an explicit intent for Main Activity
        Intent intent = new Intent(this, Tab3Fragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        List<Succes> s = Succes.find(Succes.class, "nom = ?", nom);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Succès dévérouillé!")
                .setContentText(s.get(0).getNom())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // set intent that will fire when user taps the notif
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel1";
            String description = "CHANNEL1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
