package com.example.raj.cinemaproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by raj on 26/5/18.
 */



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;



    Activity a;

    private static final String TAG = "RecyclerViewAdapter";


    private List<HashMap<String, String>> data;
    String[] title;
    String[] details;
    RecyclerView recyclerView;




    Context context;
    public RecyclerViewAdapter(Context context,List<HashMap<String, String>> data,OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
        this.data=data;
        Log.e("Data",""+data);
        title = new String[data.size()];
        details = new String[data.size()];
        Log.e("array size",""+data.size());
        for(int i = 0; i < data.size(); i++)
        {

            HashMap<String, String> googlePlace = data.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            title[i]=placeName;
            details[i]=vicinity;

        }
        Log.e("title size",""+title.length);
    }




    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);



        //view.setOnClickListener(new MyOnClickListener());
        return new RecyclerViewHolder(view);
    }








    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position)  {

            Log.e("counting",""+position);
            holder.title1.setText(title[position]);
            holder.vic.setText(details[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view,position);
                }
            });


    }




    @Override
    public int getItemCount() {
        Log.e("length hai",""+title.length);
        return title.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {



        TextView title1;
        TextView vic;
        public LinearLayout l;

        public RecyclerViewHolder(View itemView) {

            super(itemView);
            title1 = itemView.findViewById(R.id.cinema_title);
            vic = itemView.findViewById(R.id.cinema_vicinty);
            l = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }


    }
}

