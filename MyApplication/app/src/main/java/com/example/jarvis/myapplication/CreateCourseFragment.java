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
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateCourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateCourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int flag = 0;
    int flag1=0;
    DatabaseDataManager databaseDataManager;
    public static int lastCheckedPosition = 0 ;
    private OnFragmentInteractionListener mListener;
    Button create;
    Spinner dayspin,timespin,semspin;

    public CreateCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateCourseFragment newInstance(String param1, String param2) {
        CreateCourseFragment fragment = new CreateCourseFragment();
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
        databaseDataManager = new DatabaseDataManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_course, container, false);
        RecyclerView rcv = (RecyclerView) view.findViewById(R.id.rcvinstructor);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        final int[] credit = {1};
        rcv.setLayoutManager(mLayoutManager);
        flag = 0;
        final ArrayList<Instructor> instructors = databaseDataManager.getInstructorsforUser(mListener.getuserforinstructor().getId());
        create = (Button) view.findViewById(R.id.createbtn);

        // specify an adapter (see also next example)
        if(instructors.size()!=0) {
            RecyclerView.Adapter mAdapter = new MyAdapter(instructors, getActivity());
            rcv.setAdapter(mAdapter);
            flag= 1;
        }
        else {
            TextView textView = (TextView) view.findViewById(R.id.noinstructors);
            textView.setText("You haven't added any instructor yet.");
            create.setEnabled(false);
        }
        dayspin = (Spinner) view.findViewById(R.id.dayspinner);
        timespin = (Spinner) view.findViewById(R.id.timespinner);
        semspin = (Spinner) view.findViewById(R.id.semspinner);
        final ArrayList<Course> courseArrayList= databaseDataManager.getAllCoursesforUser((int) mListener.getuserforinstructor().getId());

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.instructorrg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.instructorrb1){
                    credit[0] = 1;
                }
                if(i==R.id.instructorrb2){
                    credit[0] = 2;

                }
                if(i==R.id.instructorrb3){
                    credit[0] = 3;
                }
            }
        });
        final TextView textView4 = (TextView) view.findViewById(R.id.titleinstructored);
        final TextView hourView = (TextView) view.findViewById(R.id.hourinstructored);
        final TextView minView = (TextView) view.findViewById(R.id.minuteinstructored);

        final ArrayList<Course> courselist = databaseDataManager.getAllCourses();
        Button reset = (Button) view.findViewById(R.id.resetbtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    //            Toast.makeText(getActivity(), "You clicked" + lastCheckedPosition, Toast.LENGTH_SHORT).show();
                flag1=0;
                if(textView4.getText().toString().matches("")){
                    Toast.makeText(getActivity(), "Please enter Course title", Toast.LENGTH_SHORT).show();
                }
                if(hourView.getText().toString().matches("")){
                    Toast.makeText(getActivity(), "Please enter hours", Toast.LENGTH_SHORT).show();
                }

                if(minView.getText().toString().matches("")){
                    Toast.makeText(getActivity(), "Please enter minutes", Toast.LENGTH_SHORT).show();
                }
                if(flag ==0){
                    Toast.makeText(getActivity(), "Please add instructors", Toast.LENGTH_SHORT).show();
                }
                if(Integer.parseInt(minView.getText().toString()) > 59){
                    Toast.makeText(getActivity(),"Please enter minutes in proper format",Toast.LENGTH_SHORT).show();
                }

                if(!(Integer.parseInt(hourView.getText().toString()) < 13 &&Integer.parseInt(hourView.getText().toString()) >0)){
                    Toast.makeText(getActivity(),"Please enter hour in proper format",Toast.LENGTH_SHORT).show();
                }

                if((Integer.parseInt(minView.getText().toString()) < 59 ) &&(Integer.parseInt(hourView.getText().toString()) > 0 &&Integer.parseInt(hourView.getText().toString()) < 13) && !minView.getText().toString().matches("") && flag == 1 && !hourView.getText().toString().matches("") && !textView4.getText().toString().matches("")){
                    if(courseArrayList.size()!=0) {
                        for (int i = 0; i < courseArrayList.size(); i++) {
                            if (courseArrayList.get(i).getTitle().equalsIgnoreCase(textView4.getText().toString())) {
                                Toast.makeText(getActivity(), "Course with the same title already exists", Toast.LENGTH_SHORT).show();
                                flag1=1;
                            }
                        }
                    }
                    if(courseArrayList.size() ==0){
                        Course course = new Course();
                        course.setCredit(credit[0]);

                        course.setTitle(textView4.getText().toString());
                        course.setDay(dayspin.getSelectedItem().toString());
                        course.setSemester(semspin.getSelectedItem().toString());
                        Toast.makeText(getActivity(), String.valueOf(instructors.get(lastCheckedPosition).getId()), Toast.LENGTH_SHORT).show();
                        course.setInstructor_id(instructors.get(lastCheckedPosition).getId());
                        course.setTime(hourView.getText().toString() + ":" + minView.getText().toString() + " " + timespin.getSelectedItem().toString());
                        User user = mListener.getuserforinstructor();
                        course.setUser_id((int) user.getId());
                        //course.setCourse_id(courselist.size());
                        mListener.saveCourse(course);

                        getFragmentManager().beginTransaction().replace(R.id.container, new Coursefragment(), "third").commit();

                    }
                    if(courseArrayList.size()>0 && flag1==0){
                        Course course = new Course();
                        course.setCredit(credit[0]);

                        course.setTitle(textView4.getText().toString());
                        course.setDay(dayspin.getSelectedItem().toString());
                        course.setSemester(semspin.getSelectedItem().toString());
                        Toast.makeText(getActivity(), String.valueOf(instructors.get(lastCheckedPosition).getId()), Toast.LENGTH_SHORT).show();
                        course.setInstructor_id(instructors.get(lastCheckedPosition).getId());
                        course.setTime(hourView.getText().toString() + ":" + minView.getText().toString() + " " + timespin.getSelectedItem().toString());
                        User user = mListener.getuserforinstructor();
                        course.setUser_id((int) user.getId());
                        //course.setCourse_id(courselist.size());
                        mListener.saveCourse(course);

                        getFragmentManager().beginTransaction().replace(R.id.container, new Coursefragment(), "third").commit();

                    }
                   }
           }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new CreateCourseFragment(),"").commit();
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

        User getuserforinstructor();

        void saveCourse(Course course);
    }
}
