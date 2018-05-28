package com.example.raj.cinemaproject;

/**
 * Created by raj on 26/5/18.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    private String googlePlacesData;
    private GoogleMap mMap;
    String url;
    RecyclerView list;

    @Override
    protected String doInBackground(Object... objects){
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Returning google",""+googlePlacesData);
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        Log.e("Get Nearby Places","");
        //nearbyPlaceList=parser.parse(s);
        parser.parse(s);

        //Log.e("POst Execute",""+nearbyPlaceList);
        //Log.d("nearbyplacesdata","called parse method");
        //showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {




/*
        RecyclerView list=(RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(.this));
        list.setAdapter(new RecyclerViewAdapter(nearbyPlaceList));
*/
        /*
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            /*MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));




            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);
            Log.e(""+i,"" +nearbyPlaceList.get(i));
            Log.e(""+i,"" +googlePlace.get("place_name"));
            */
        }
    }

