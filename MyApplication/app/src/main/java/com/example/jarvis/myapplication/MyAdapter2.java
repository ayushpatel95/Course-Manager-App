package com.example.jarvis.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private ArrayList<Course> mDataset;
    private ArrayList<Instructor> mDataset2;
    MainActivity mainActivity;
    Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView tv1,tv2,tv3;
        public ImageView iv;

        public ViewHolder(View v) {
            super(v);
            view = v;
            tv1 = (TextView) v.findViewById(R.id.coursetitletv);
            tv2 = (TextView) v.findViewById(R.id.courseinstructortv);
            tv3 = (TextView) v.findViewById(R.id.coursedaytimetv);
            iv = (ImageView) v.findViewById(R.id.coourseimageview);

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete")
                            .setMessage("Are you sure you want to delete this course?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseDataManager databaseDataManager = new DatabaseDataManager(context);
                                    databaseDataManager.deleteNote(mDataset.get(getAdapterPosition()));
                                    mDataset.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                    return false;
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.course = mDataset.get(getAdapterPosition());
                    mainActivity.getFragmentManager().beginTransaction().replace(R.id.container,new DisplayCourseFragment(),"lala").addToBackStack(null).commit();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter2(ArrayList<Course> myDataset, Context context, ArrayList<Instructor> instructors,MainActivity mainActivity) {
        mDataset = myDataset; this.context = context; mDataset2 = instructors; this.mainActivity = mainActivity;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courselayout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        for(int i =0 ;i<mDataset2.size();i++){
            if(mDataset2.get(i).getId() == mDataset.get(position).getInstructor_id()){

                if(mDataset2.get(i).getProfile_image() != null){byte[] array = mDataset2.get(i).getProfile_image();
                Bitmap bitmap = BitmapFactory.decodeByteArray(array,0, array.length);
                holder.iv.setImageBitmap(bitmap);}
                else{
                    holder.iv.setImageResource(R.drawable.default_image);
                }
                holder.tv2.setText(mDataset2.get(i).getFirst_name() + " " + mDataset2.get(i).getLast_name());

            }
        }

        holder.tv1.setText(mDataset.get(position).getTitle());
        holder.tv3.setText(mDataset.get(position).getDay() + " " + mDataset.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


