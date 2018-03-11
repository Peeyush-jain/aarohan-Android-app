package iitropar.aarohan;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MyEventDayFragment extends Fragment{
    View myView ;
    private RecyclerView recyclerView ;
    private static UpcomingEventAdapter eventAdapter ;
    private ArrayList<Event> eventList ;
    private static Context context ;
    private static LinearLayoutManager layoutManager ;

    private DBHandler dba ;
    private int day ;
    public MyEventDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_my_event_day, container, false);
        recyclerView = myView.findViewById(R.id.myEventRecyclerView);






        return myView ;
    }

}
