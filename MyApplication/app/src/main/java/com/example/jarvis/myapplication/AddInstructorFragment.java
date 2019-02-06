package com.example.jarvis.myapplication;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddInstructorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddInstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddInstructorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView first_nametv,last_nametv,email,websitetv;
    DatabaseDataManager dm;
    Button register_btn, reset_btn;
    ImageView add_photo;
    Bitmap bitmap;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddInstructorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddInstructorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddInstructorFragment newInstance(String param1, String param2) {
        AddInstructorFragment fragment = new AddInstructorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        first_nametv = (TextView) view.findViewById(R.id.instructorfirstnameed);
        last_nametv = (TextView) view.findViewById(R.id.instructorlastnameed);
        websitetv = (TextView) view.findViewById(R.id.websiteed);
        email = (TextView) view.findViewById(R.id.emailed);
        add_photo = (ImageView) view.findViewById(R.id.instructorcameraiv);
        reset_btn = (Button) view.findViewById(R.id.iresetbtn);

        websitetv.setText("");
        first_nametv.setText("");
        last_nametv.setText("");
        email.setText("");
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[] {"Camera","Gallery"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle("Add image using:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){

                            mListener.clickinstructorimage();

                        }
                        else if(which == 1){
                            mListener.opengalleryforInstructor();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //the user clicked on Cancel
                    }
                });
                builder.show();
            }
        });
        register_btn = (Button) view.findViewById(R.id.icreatebtn);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new AddInstructorFragment(),"").commit();
            }
        });
        dm = new DatabaseDataManager(getActivity());
        final ArrayList<Instructor> instructors = dm.getAllinstructors();
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().matches("") && !last_nametv.getText().toString().matches("") && !websitetv.getText().toString().matches("") && !email.getText().toString().matches("")){
                    Instructor user = new Instructor();
                    user.setEmail(email.getText().toString());
                    user.setLast_name(last_nametv.getText().toString());
                    user.setPersonal_website(websitetv.getText().toString());
                    user.setFirst_name(first_nametv.getText().toString());
                    User users = mListener.getUserforAdding();
                    user.setUser_id((int) users.getId());
                    user.setId(instructors.size());
                    byte[] bytes = mListener.getByteArray();
                    user.setProfile_image(bytes);
                    mListener.saveinstructor(user);
                    getFragmentManager().beginTransaction().replace(R.id.container,new DisplayInstructorsFragment(),"fourth").commit();
                    //  mListener.gotoCourseFragment();
                }
            }
        });

        return view;}

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void clickinstructorimage();

        void saveinstructor(Instructor user);

        byte[] getByteArray();

        User getUserforAdding();

        void opengalleryforInstructor();
    }
}
