package com.example.jarvis.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayInstructorsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayInstructorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayInstructorsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseDataManager databaseDataManager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DisplayInstructorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayInstructorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayInstructorsFragment newInstance(String param1, String param2) {
        DisplayInstructorsFragment fragment = new DisplayInstructorsFragment();
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
        View view= inflater.inflate(R.layout.fragment_display_instructors, container, false);
        databaseDataManager = new DatabaseDataManager(getActivity());
        RecyclerView rcv = (RecyclerView) view.findViewById(R.id.rcvdisplayinstructors);
        final LinearLayoutManager mLayoutManager =  new LinearLayoutManager(getActivity());
        final int[] credit = {0};
        rcv.setLayoutManager(mLayoutManager);
        User user = mListener.getUserforInstrcutors();
        ArrayList<Instructor> instructors= databaseDataManager.getInstructorsforUser(user.getId());
        ArrayList<Course> courses = databaseDataManager.getAllCoursesforUser((int) user.getId());
        // specify an adapter (see also next example)
        if(instructors.size()!=0) {
            RecyclerView.Adapter mAdapter = new MyAdapter3(courses, getActivity(),instructors);
            rcv.setAdapter(mAdapter);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        User getUserforInstrcutors();
    }
}
