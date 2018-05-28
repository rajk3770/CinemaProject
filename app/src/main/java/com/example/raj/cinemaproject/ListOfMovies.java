package com.example.raj.cinemaproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
//import com.android.volley.Response;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raj on 26/5/18.
 */

public class ListOfMovies extends AppCompatActivity implements View.OnClickListener {


    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        String url3 = new Uri.Builder()
                .scheme("https")
                .appendPath("")
                .appendPath("api-gate.movieglu.com")
                .appendPath("filmsNowShowing")
                .appendPath("")
                .appendQueryParameter("n","100")

                .build()
                .toString();
  */
        String url3="https://api-gate.movieglu.com/filmsNowShowing/?n=15";
        //String url3="https://api-gate.movieglu.com/filmLiveSearch/?query=Parmanu";
        //String url3="https://api-gate.movieglu.com/cinemasNearby/?n=10";



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url3, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success",""+response);
                        JSONArray movies= null;
                        try {
                            movies = response.getJSONArray("films");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            for(int i=0;i<20;i++){
                                JSONObject popularMovie = movies.getJSONObject(i);
                                String name=popularMovie.getString("film_name");
                                String release_date=popularMovie.getString("release_date");
                                String desp=popularMovie.getString("synopsis_long");
                                JSONObject img = popularMovie.getJSONObject("images");
                                JSONObject img1 = img.getJSONObject("poster");
                                JSONObject img2 = img1.getJSONObject("1");
                                JSONObject img3 = img2.getJSONObject("medium");
                                String link=img3.getString("film_image");
                                Log.e("Value",name+" "+release_date+" "+desp+" "+link);


                                Log.e("Name Of Movie",""+movies.getJSONObject(i));

                                mDatabase= FirebaseDatabase.getInstance().getReference().child("movies");
                                mDatabase.child("" +i).child("Name").setValue(name);
                                mDatabase.child("" +i).child("Release Date").setValue(release_date);
                                mDatabase.child("" +i).child("Description").setValue(desp);
                                mDatabase.child("" +i).child("Link").setValue(link);







                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Success Callback
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Failure","");
                        //Failure Callback
                    }
                }) {

            /**
             * Passing some request headers*
             */

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                //headers.put("Content-Type", "application/json");
                headers.put("client", "VBIH");
                headers.put("x-api-key", "kTMIzt4iKSFTHxk82wkL1WhPnfuaWhC2vmPBZ7g8");
                headers.put("Authorization", "Basic VkJJSDpyN29qMThRd2dMbmg=");
                headers.put("api-version", "v102");
                headers.put("geolocation", "19.203491;72.846208");
                //headers.put("geolocation", "52.123;0.456");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq,"headerRequest");
    }

    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
