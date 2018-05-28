package com.example.raj.cinemaproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by raj on 26/5/18.
 */

public class Activity extends AppCompatActivity {
    static List<HashMap<String, String>> var;
    DataParser d;
    RecyclerView list;
    RecyclerViewAdapter r;
    ProgressDialog p;
    //ProgressDialog p;
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //final ProgressDialog p =new ProgressDialog(Activity.this);
        //p.dismiss();
        p =new ProgressDialog(Activity.this);
        setContentView(R.layout.recycler_view);
        //d.final1();
        RecyclerView list = (RecyclerView) findViewById(R.id.list1);
        Log.e("Yahape", "" + list);
        list.setLayoutManager(new LinearLayoutManager(this));
        //list.setAdapter(new RecyclerViewAdapter(Activity.var));
        //Log.e("Checking in oncreate",""+Activity.var);


        list.setAdapter(new RecyclerViewAdapter(this,Activity.var, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(Activity.this, "Item Clicked", Toast.LENGTH_LONG).show();


                //p.setTitle("");



                Intent i=new Intent(Activity.this,Slider.class);

                startActivity(i);




            }
        }));
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if(p.isShowing()) {
            p.dismiss();
        }
    }



    public void func(List<HashMap<String, String>> nearbyPlaceList) {
            Log.e("Mila re baba", "" + nearbyPlaceList.get(0));

            Activity.var=nearbyPlaceList;
/*

            */
//            setContentView(R.layout.recycler_view);


        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MapsActivity.class);
        startActivity(i);
    }
}
