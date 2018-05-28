package com.example.raj.cinemaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by raj on 27/5/18.
 */

public class SliderAdapter extends PagerAdapter {

    final Context context;
    LayoutInflater l;
    private DatabaseReference mDatabase;

    ProgressDialog p;
    //Arrays
    /*
    public String[] title={
        "","","",""
    };

    public String[] date={
            "","","",""
    };

    public String[] desp={
            "","","",""
    };

    public String[] link={
            "","","",""
    };

*/
    public String[] title = new String[4];
    public String[] date = new String[4];
    public String[] link = new String[4];
    public String[] desp = new String[4];


    public SliderAdapter(final Context context){


        this.context=context;
        mDatabase= FirebaseDatabase.getInstance().getReference().child("movies");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Random r = new Random();
                int i1 = r.nextInt(2);
                //Toast.makeText(context, "" +i1, Toast.LENGTH_SHORT).show();


                title[i1]=dataSnapshot.child("1").child("Name").getValue().toString();
                date[i1]=dataSnapshot.child("1").child("Release Date").getValue().toString();
                desp[i1]=dataSnapshot.child("1").child("Description").getValue().toString();
                link[i1]=dataSnapshot.child("1").child("Link").getValue().toString();
                if(i1==0){
                    i1+=1;
                }
                else{
                    i1-=1;
                }
                title[i1]=dataSnapshot.child("10").child("Name").getValue().toString();
                date[i1]=dataSnapshot.child("10").child("Release Date").getValue().toString();
                desp[i1]=dataSnapshot.child("10").child("Description").getValue().toString();
                link[i1]=dataSnapshot.child("10").child("Link").getValue().toString();




                 i1 = r.nextInt(9-2) + 2;

                title[2]=dataSnapshot.child(""+i1).child("Name").getValue().toString();
                date[2]=dataSnapshot.child(""+i1).child("Release Date").getValue().toString();
                desp[2]=dataSnapshot.child(""+i1).child("Description").getValue().toString();
                link[2]=dataSnapshot.child(""+i1).child("Link").getValue().toString();


                int i2 = r.nextInt(9-2) + 2;

                title[3]=dataSnapshot.child(""+i2).child("Name").getValue().toString();
                date[3]=dataSnapshot.child(""+i2).child("Release Date").getValue().toString();
                desp[3]=dataSnapshot.child(""+i2).child("Description").getValue().toString();
                link[3]=dataSnapshot.child(""+i2).child("Link").getValue().toString();
                Log.e("link array",""+link[0]);
                Log.e("link array",""+link[1]);
                Log.e("link array",""+link[2]);
                Log.e("link array",""+link[3]);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void loadvalues(){


    }





    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //Toast.makeText(context,"Inside Instan",Toast.LENGTH_SHORT).show();
        l = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = l.inflate(R.layout.slider_content, container, false);




            TextView t = (TextView) view.findViewById(R.id.s_title);
            TextView rd = (TextView) view.findViewById(R.id.s_rd);
            TextView d = (TextView) view.findViewById(R.id.s_desp);
            d.setMovementMethod(new ScrollingMovementMethod());

        ImageView i = (ImageView) view.findViewById(R.id.img);


            //p=new ProgressDialog(context);
            //p.setMessage("Loading");
            //p.show();
            //Log.e("Value of r is",""+r);
            //final RelativeLayout r=(RelativeLayout)view.findViewById(R.id.layout2);
            t.setText(title[position]);
            rd.setText(date[position]);
            d.setText(desp[position]);
            //Log.e("Check",""+link[position]);

            Log.e("Check", "" + title[position]);
            Log.e("Position is", "" + position);
            Picasso.get().load(""+link[position]).into(i);

        /*Picasso.with(context).load(""+link[position]).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                r.setBackground(new BitmapDrawable(bitmap) {
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

*/


            container.addView(view);

        return view;
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);

    }
}
