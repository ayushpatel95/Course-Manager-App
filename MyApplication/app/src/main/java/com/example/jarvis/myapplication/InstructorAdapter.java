package com.example.jarvis.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jarvis on 4/11/17.
 */

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.MyViewHolder> {

    ArrayList<Instructor> instructorlist;
    Context context;
    public InstructorAdapter(ArrayList<Instructor> instructorlist, Context context) {
        this.instructorlist = instructorlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View view) {
            super(view);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            TextView tv = (TextView) view.findViewById(R.id.textView2);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instructorlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Instructor instructor = instructorlist.get(position);

    }

    @Override
    public int getItemCount() {
        return instructorlist.size();
    }

    }

