package com.example.jarvis.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Coursefragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Coursefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Coursefragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseDataManager databaseDataManager;

    private OnFragmentInteractionListener mListener;

    public Coursefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Coursefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Coursefragment newInstance(String param1, String param2) {
        Coursefragment fragment = new Coursefragment();
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
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        databaseDataManager = new DatabaseDataManager(getActivity());
        RecyclerView rcv = (RecyclerView) view.findViewById(R.id.rcvcourses);
        final LinearLayoutManager mLayoutManager =  new LinearLayoutManager(getActivity());
        final int[] credit = {0};
        rcv.setLayoutManager(mLayoutManager);
        User user = mListener.getUserforCourses();
        ArrayList<Instructor> instructors= databaseDataManager.getInstructorsforUser(user.getId());
        ArrayList<Course> courses = databaseDataManager.getAllCoursesforUser((int) user.getId());
        // specify an adapter (see also next example)
        if(courses.size()!=0) {
            RecyclerView.Adapter mAdapter = new MyAdapter2(courses, getActivity(),instructors, (MainActivity) getActivity());
            rcv.setAdapter(mAdapter);
        }
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new CreateCourseFragment(),"create course").commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


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
        void gotoInstructorfragment();

        User getUserforCourses();
        // TODO: Update argument type and name
    }
}
