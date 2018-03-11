package iitropar.aarohan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ScheduleDay extends AppCompatActivity  {
    private int dayNumber ;
    private Spinner spinner ;
    private RecyclerView recyclerView ;
    private static EventAdapter eventAdapter ;
    private ArrayList<Event> eventList ;
    private static Context context ;
    private static LinearLayoutManager layoutManager ;
    private DBHandler dba ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_day);


        spinner = findViewById(R.id.spinner);
        context = getApplicationContext() ;
        Intent intent = getIntent();
        dayNumber = intent.getExtras().getInt("dayNumber");
        if (dayNumber == 1) {

            getSupportActionBar().setTitle("Day 1");

        }
        else if (dayNumber == 2) {

            getSupportActionBar().setTitle("Day 2");

        }
        else if (dayNumber == 3) {

            getSupportActionBar().setTitle("Day 3");

        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sports_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        dba = new DBHandler(context, null, null, 1);
        eventList = new ArrayList<Event>();
        eventList = dba.getDataDay(dayNumber);
        eventAdapter = new EventAdapter(context, eventList, getSupportFragmentManager());
        layoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
//                getActivity()
//        ));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (position != 0 ){
                    eventList.clear();
                    eventList = dba.getDataDayType(dayNumber,position);
                    eventAdapter = new EventAdapter(context, eventList, getSupportFragmentManager());
                    recyclerView.setAdapter(eventAdapter);
                }
                if (position == 0){
                    eventList.clear();
                    eventList = dba.getDataDay(dayNumber);
                    eventAdapter = new EventAdapter(context, eventList, getSupportFragmentManager());
                    recyclerView.setAdapter(eventAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }


}
