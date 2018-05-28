package com.example.raj.cinemaproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by raj on 27/5/18.
 */

public class Slider extends AppCompatActivity {
    private ViewPager v;
    private LinearLayout l;
    ProgressDialog p;


    private DatabaseReference mDatabase;
    private SliderAdapter sliderAdapter;

    public TextView[] mDots;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);

        v=(ViewPager)findViewById(R.id.pager);
        //l=(LinearLayout)findViewById(R.id.dots);



        sliderAdapter=new SliderAdapter(this);

        p=new ProgressDialog(this);
        p.setMessage("Fetching Available Shows...");
        p.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setAdapter(sliderAdapter);
                p.dismiss();
            }
        }, 5000);

        //addDotsIndicator();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(p.isShowing()) {

        }
    }

    public void addDotsIndicator(){

        mDots=new TextView[4];

        for(int i=0;i<mDots.length;i++){

            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.cardview_dark_background));
            l.addView(mDots[i]);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
