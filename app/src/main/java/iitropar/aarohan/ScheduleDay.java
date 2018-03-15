package iitropar.aarohan;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ScheduleDay extends AppCompatActivity  {
    private int dayNumber ;
    private Spinner spinner ;
    private RecyclerView recyclerView ;
    private static EventAdapter eventAdapter ;
    private ArrayList<Event> eventList ;
    private static Context context ;
    private static LinearLayoutManager layoutManager ;
    private DBHandler dba ;
    private static final String JSON_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=_4OOABObJuZTZzUwGSStXmLzyrTvxyLD8Jnly8OA2-1f8Rt-xtLN3vAWuNMvIHXJXJ6_Ola0O1l6kUiSgMU0xPjVZAwERd9sOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1ZsYSbt7G4nMhEEDL32U4DxjO7V7yvmJPXJTBuCiTGh3rUPjpYM_V0PJJG7TIaKp5XydYixzJPu541uII25ITzxHHZhPxVZfignSY_QqS9yQcdBu4XHRCvcNyXohjQuH8KiW3k6MDkf31SIMZH6H4k&lib=MbpKbbfePtAVndrs259dhPT7ROjQYJ8yx";
    private Button refreshButton ;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_day);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sports_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        dba = new DBHandler(context, null, null, 1);
        eventList = new ArrayList<Event>();

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

        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            loadData();


            refreshButton = findViewById(R.id.refresh);
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        spinner.setSelection(0);

                        loadData();


                    }

                }
            });
        }
        else {
            eventList = dba.getDataDay(dayNumber);

            if (eventList.size() != 0){
                eventAdapter = new EventAdapter(context, eventList, getSupportFragmentManager());
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(eventAdapter);
            }
            else {
                setContentView(R.layout.blank_button);
            }
                refreshButton = findViewById(R.id.refresh);

                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            //Intent intent = new Intent(getApplicationContext(), ScheduleDay.class);
                            //startActivity(intent);
                            finish();
                            startActivity(getIntent());
                        }

                    }
                });



        }




    }
    private void loadData( ) {
        //getting the progressbar


        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray eventArray = obj.getJSONArray("Sheet1");
                            dba.clearDatabase();

                            //now looping through all the elements of the json array
                            for (int i = 0; i < eventArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject eventObject = eventArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Event event = new Event(eventObject.getString("TeamA"), eventObject.getString("TeamB"),eventObject.getString("Time"), eventObject.getString("Venue"),eventObject.getInt("Type"), eventObject.getInt("Day"),eventObject.getString("Description"));

                                //adding the hero to herolist
                                dba.insertTableEvents(event);

                            }
                            eventList = dba.getDataDay(dayNumber);
                            eventAdapter = new EventAdapter(context, eventList, getSupportFragmentManager());
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(eventAdapter);



                        } catch (JSONException e) {
                            Log.e(TAG, "Error ");
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.INVISIBLE);
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }


//    @Override
//    public void onBackPressed() {
//        HomeScheduleFragment fragment = new HomeScheduleFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
//                .addToBackStack(null)
//                .commit();
//
////        Intent intent = new Intent(this , MainActivity.class);
////        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////        startActivity(intent);
//
//        super.onBackPressed();
//    }
}
