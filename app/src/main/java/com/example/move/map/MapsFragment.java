package com.example.move.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.move.R;
import com.example.move.data.Point;
import com.example.move.data.PointDAO;
import com.example.move.data.Trajet;
import com.example.move.data.TrajetDAO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnGetPos;
    Location loc;
    Location lastLoc;
    LatLng userPos;
    LatLng lastUserPos;
    double altitude;
    double latitude;
    double longitude;
    double speed;
    double lastSpeed;
    float lowSpeed;
    float highSpeed;
    int lineColor;
    GPStracker gpsTracker;
    Trajet trajet;
    int id_trajet;

    boolean recording;
    private Timer timer;
    private TimerTask recordTask;

    public MapsFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.maps_fragment,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        altitude = 0;
        latitude = 0;
        longitude = 0;
        lastSpeed = 0;
        speed = 0;

        id_trajet = TrajetDAO.getLastId();
        Log.i("monLOG", String.valueOf(id_trajet));

        lowSpeed = 0;
        highSpeed = 15;

        recording = false;
        timer = new Timer();

        btnGetPos = (Button) view.findViewById(R.id.btnGetPos);
        btnGetPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!recording){
                    id_trajet++;
                    startRecordTimer();
                    recording = true;
                    btnGetPos.setText(getString(R.string.stopPosbutton));
                    Toast.makeText(getActivity().getApplicationContext(),"RECORDING", Toast.LENGTH_LONG).show();
                } else {
                    stopRecordTimer();
                    recording = false;
                    btnGetPos.setText(getString(R.string.posbutton));
                    Toast.makeText(getActivity().getApplicationContext(),"NOT RECORDING", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //To stop timer
    private void stopRecordTimer(){
        if(timer != null){
            lastLoc = null;
            timer.cancel();
            timer.purge();
        }
    }

    //To start timer
    private void startRecordTimer(){
        timer = new Timer();
        recordTask = new TimerTask() {
            public void run() {
                btnGetPos.post(new Runnable() {
                    public void run(){
                        recordNewPoint();
                    }
                });
            }
        };
        timer.schedule(recordTask, 0, 1000);
    }

    public void recordNewPoint(){
        if (gpsTracker == null) {
            gpsTracker = new GPStracker(getActivity().getApplicationContext());
        }
        loc = gpsTracker.getLocation();
        if(loc != null){
            altitude = loc.getAltitude();
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            lastSpeed = speed;
            speed = loc.getSpeed() * 3.6;

            //Toast.makeText(getActivity().getApplicationContext(),"ALT : " + altitude + "\nLAT : " + latitude + "\nLNG : " + longitude + "\nSPD : " + speed, Toast.LENGTH_LONG).show();

            Point point = new Point(id_trajet, altitude, latitude, longitude, speed, lastSpeed);
            point.save();

            //Log.i("monLOG", String.valueOf(PointDAO.getNbPoint()-1));
            //Log.i("monLOG", String.valueOf(PointDAO.selectAll().get(PointDAO.getNbPoint()-1).getAltitude()));

            if(lastLoc != null && loc.getTime() != lastLoc.getTime()) { // Cas lastLoc et loc initialises
                userPos = new LatLng(loc.getLatitude(), loc.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(userPos).title("User pos"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(userPos));

                if (lastUserPos != null) {
                    double evgSpeed = (lastSpeed + speed) / 2;

                    if (evgSpeed <= (highSpeed - lowSpeed) / 3 + lowSpeed){ lineColor = Color.GREEN; }
                    else if (evgSpeed >= ((highSpeed - lowSpeed) / (3/2) + lowSpeed)){ lineColor = Color.RED; }
                    else { lineColor = Color.YELLOW; }
                    mMap.addPolyline(
                            new PolylineOptions()
                                    .add(userPos)
                                    .add(lastUserPos)
                                    .width(8f)
                                    .color(lineColor)
                    );
                }

            } else if (lastLoc == null && loc != null){ // Cas juste loc initialise
                userPos = new LatLng(loc.getLatitude(), loc.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userPos).title("Point de départ courant"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userPos, 19) );
            }
            lastLoc = loc;
            lastUserPos = userPos;
            //Toast.makeText(getApplicationContext(), loc.getProvider(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity().getApplicationContext(),"Permission non accordée", Toast.LENGTH_SHORT).show();
        }
        else{
            mMap.setMyLocationEnabled(true);
            gpsTracker = new GPStracker(getActivity().getApplicationContext());
            loc = gpsTracker.getLocation();
            userPos = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLng(userPos) );
        }

        Trajet t;
        List<Point> listePoints;
        int nbTrajets = TrajetDAO.getLastId();
        for(int i = 1; i <= nbTrajets; ++i){
            t = TrajetDAO.selectTrajetById(i);
            listePoints = t.getTrajet();

            for(int j = 0; j < listePoints.size(); ++j){
                altitude = listePoints.get(j).getAltitude();
                latitude = listePoints.get(j).getLatitude();
                longitude = listePoints.get(j).getLongitude();
                lastSpeed = listePoints.get(j).getLast_vitesse();
                speed = listePoints.get(j).getVitesse();

                if (j > 0){
                    userPos = new LatLng(listePoints.get(j).getLatitude(), listePoints.get(j).getLongitude());
                    lastUserPos = new LatLng(listePoints.get(j-1).getLatitude(), listePoints.get(j-1).getLongitude());
                    double evgSpeed = (lastSpeed + speed) / 2;
                    if (evgSpeed <= (highSpeed - lowSpeed) / 3 + lowSpeed){ lineColor = Color.GREEN; }
                    else if (evgSpeed >= ((highSpeed - lowSpeed) / (3/2) + lowSpeed)){ lineColor = Color.RED; }
                    else { lineColor = Color.YELLOW; }
                    mMap.addPolyline(
                            new PolylineOptions()
                                    .add(userPos)
                                    .add(lastUserPos)
                                    .width(8f)
                                    .color(lineColor)
                    );
                } else {
                    userPos = new LatLng(listePoints.get(j).getLatitude(), listePoints.get(j).getLongitude());
                    mMap.addMarker(new MarkerOptions().position(userPos).title("Point départ " + i));
                }
            }
        }
    }
}
