package com.example.jarvis.myapplication;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    TextView first_nametv,last_nametv,user_nametv,passwordtv;
    DatabaseDataManager dm;
    Button register_btn;
    ImageView add_photo;
    int flag = 0;
    Bitmap bitmap;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        first_nametv = (TextView) view.findViewById(R.id.firstnameed);
        last_nametv = (TextView) view.findViewById(R.id.lastnameed);
        passwordtv = (TextView) view.findViewById(R.id.passworded);
        user_nametv = (TextView) view.findViewById(R.id.usernameed);
        add_photo = (ImageView) view.findViewById(R.id.registercameraiv);


        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[] {"Camera","Gallery"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle("Select your option:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){

                            mListener.clickimage();

                        }
                        else if(which == 1){
                            mListener.opengallery();
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
        register_btn = (Button) view.findViewById(R.id.register_btn);
        dm = new DatabaseDataManager(getActivity());
        final ArrayList<User> users = (ArrayList<User>) dm.getAllNotes();
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (users.size() > 0) {
                    for (int i = 0; i < users.size(); i++) {
                        if (user_nametv.getText().toString().matches(users.get(i).getUsername())) {
                            Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                            flag = 1;
                        }
                    }
                }
                if (flag == 0 && !user_nametv.getText().toString().matches("") && !last_nametv.getText().toString().matches("") && !passwordtv.getText().toString().matches("") && !user_nametv.getText().toString().matches("")) {
                    User user = new User();
                    user.setUsername(user_nametv.getText().toString());
                    user.setLast_name(last_nametv.getText().toString());
                    user.setPassword(passwordtv.getText().toString());
                    user.setFirst_name(first_nametv.getText().toString());
                    user.setId(users.size());
                    byte[] bytes = mListener.getByteArrayforUser();
                    user.setImage(bytes);
                    mListener.saveuser(user);
                    mListener.gotoCourseFragment();
                }
            }
        });

        return view;
    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
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

        void saveuser(User user);

        void gotoCourseFragment();

        void clickimage();

        Bitmap getImage();

        byte[] getByteArrayforUser();

        void opengallery();
    }
}
