package com.example.jarvis.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Instructor> mDataset;
    Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public  TextView tv;
        public ImageView iv;
        RadioButton radioButton;

        public ViewHolder(View v) {
            super(v);
            view = v;
            tv = (TextView) v.findViewById(R.id.textView2);
            iv = (ImageView) v.findViewById(R.id.iv);
            radioButton = (RadioButton) v.findViewById(R.id.radio);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateCourseFragment.lastCheckedPosition = getAdapterPosition();
                    //because of this blinking problem occurs so
                    //i have a suggestion to add notifyDataSetChanged();
                    //   notifyItemRangeChanged(0, list.length);//blink list problem
                    notifyDataSetChanged();

                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Instructor> myDataset,Context context) {
        mDataset = myDataset; this.context = context;
    }


    public ArrayList<Instructor> getList() {
        return mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instructorlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv.setText(mDataset.get(position).getFirst_name() + " " + mDataset.get(position).getLast_name());
        if(mDataset.get(position).getProfile_image()!=null){
        byte[] array = mDataset.get(position).getProfile_image();
        Bitmap bitmap = BitmapFactory.decodeByteArray(array,0, array.length);
        holder.iv.setImageBitmap(bitmap);}
        else{
            holder.iv.setImageResource(R.drawable.default_image);
        }
        holder.radioButton.setChecked(position == CreateCourseFragment.lastCheckedPosition);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


