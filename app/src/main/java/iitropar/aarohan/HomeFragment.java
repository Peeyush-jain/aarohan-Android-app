package iitropar.aarohan;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    View myView;
    private FragmentManager fragmentManager;
    private static DBHandler dba;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();

        // implement the on click event lister for the bottom navigation plane

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame_home, new HomeEventFragment())
                .commit();
        getActivity().setTitle("Events");
        dba = new DBHandler(getContext(), null, null, 1);


        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            // Code to run once
            dba.clearDatabase();
            databaseInsert();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_home , container, false);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) myView.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                System.out.println("Map");
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_frame_home, new HomeMapFragment())
                                        .commit();
                                getActivity().setTitle("Map");
                                break;
                            case R.id.action_events:
                                System.out.println("Event");
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_frame_home, new HomeEventFragment())
                                        .commit();
                                getActivity().setTitle("Events");
                                break;
                            case R.id.action_schedule:
                                System.out.println("schedule");
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_frame_home, new HomeScheduleFragment())
                                        .commit();
                                getActivity().setTitle("Schedule");
                                break;
                        }
                        return true;
                    }
                });

        return myView;
    }



    public void databaseInsert(){
        insertData(1,1);
        insertData(1,2);
        insertData(1,3);

        insertData(2,1);
        insertData(2,2);
        insertData(2,3);

        insertData(3,1);
        insertData(3,2);
        insertData(3,3);

        insertData(4,1);
        insertData(4,2);
        insertData(4,3);

        insertData(5,1);
        insertData(5,2);
        insertData(5,3);

        insertData(6,1);
        insertData(6,2);
        insertData(6,3);

        insertData(7,1);
        insertData(7,2);
        insertData(7,3);

        insertData(8,1);
        insertData(8,2);
        insertData(8,3);

        insertData(9,1);
        insertData(9,2);
        insertData(9,3);

        insertData(10,1);
        insertData(10,2);
        insertData(10,3);

        insertData(11,1);
        insertData(11,2);
        insertData(11,3);

        insertData(12,1);
        insertData(12,2);
        insertData(12,3);

        insertData(13,1);
        insertData(13,2);
        insertData(13,3);

        insertData(14,1);
        insertData(14,2);
        insertData(14,3);


    }

    public void insertData(int eventType , int eventDay){
        Event event = new Event("IIT Ropar","Chitkara University","10:00AM - 12:00 AM","Cricket Ground", eventType,eventDay );
        Event event1 = new Event("IIT Ropar","LPU","11:00AM - 12:00 AM","MP HALL", eventType,eventDay );
        Event event2 = new Event("LPU","Chitkara University","12:00AM - 12:00 AM","Football Ground", eventType,eventDay );
        Event event3 = new Event("JUIT","Chitkara University","13:00AM - 12:00 AM","Volley Ground", eventType,eventDay );
        Event event4 = new Event("IIT Delhi","Chitkara University","14:00AM - 12:00 AM","Hockey Ground", eventType,eventDay );
        Event event5 = new Event("IIT Roorkee","Chitkara University","15:00AM - 12:00 AM","tennis Ground", eventType,eventDay );

        dba.insertTableEvents(event);
        dba.insertTableEvents(event1);
        dba.insertTableEvents(event2);
        dba.insertTableEvents(event3);
        dba.insertTableEvents(event4);
        dba.insertTableEvents(event5);




    }

}