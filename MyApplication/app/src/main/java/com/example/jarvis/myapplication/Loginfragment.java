package com.example.jarvis.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Loginfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Loginfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Loginfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseDataManager dm ;    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Loginfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Loginfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Loginfragment newInstance(String param1, String param2) {
        Loginfragment fragment = new Loginfragment();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loginfragment, container, false);
        HashMap<String, String> map = new HashMap<>();
        TextView signuptv = (TextView) view.findViewById(R.id.signuptv);
        Button logintbn = (Button) view.findViewById(R.id.loginbutton);
        final EditText username = (EditText) view.findViewById(R.id.loginusertv);
        final EditText password = (EditText) view.findViewById(R.id.loginpasstv);
        dm = new DatabaseDataManager(getActivity());
        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoRegisterFragment();

            }
        });
        logintbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> userArrayList = (ArrayList<User>) dm.getAllNotes();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.matches("")) {
                    Toast.makeText(getActivity(), "Username cannot be null", Toast.LENGTH_SHORT).show();
                } else if (pass.matches("")) {
                    Toast.makeText(getActivity(), "Password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    if (userArrayList.size() != 0) {
                        for (int i = 0; i < userArrayList.size(); i++) {
                            if (userArrayList.get(i).getUsername().matches(user) && userArrayList.get(i).getPassword().matches(pass)){
                                Toast.makeText(getActivity(),"Welcome "+ user,Toast.LENGTH_SHORT).show();
                                mListener.setUser(userArrayList.get(i));
                                getFragmentManager().beginTransaction().replace(R.id.container,new Coursefragment(),"third").commit();

                            }
                            else{
                                Toast.makeText(getActivity(),"Wrong username and/or password",Toast.LENGTH_SHORT).show();
                            }

                        }
                    } else {
                        Toast.makeText(getActivity(), "No users in the table", Toast.LENGTH_SHORT).show();
                    }

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

        void gotoRegisterFragment();

        void setUser(User user);
    }
}
