package iitropar.aarohan;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import static android.content.ContentValues.TAG;


public class GeneralChampionFragment extends  Fragment{

    private static final String JSON_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=_4OOABObJuZTZzUwGSStXmLzyrTvxyLD8Jnly8OA2-1f8Rt-xtLN3vAWuNMvIHXJXJ6_Ola0O1l6kUiSgMU0xPjVZAwERd9sOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1ZsYSbt7G4nMhEEDL32U4DxjO7V7yvmJPXJTBuCiTGh3rUPjpYM_V0PJJG7TIaKp5XydYixzJPu541uII25ITzxHHZhPxVZfignSY_QqS9yQcdBu4XHRCvcNyXohjQuH8KiW3k6MDkf31SIMZH6H4k&lib=MbpKbbfePtAVndrs259dhPT7ROjQYJ8yx";
    public GeneralChampionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View myView;
    private ArrayList<Event> eventList;
    private ArrayList<Event> sortedList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ChampionAdapter adapter ;
    private Button refreshButton ;
    private RecyclerView recyclerView ;
    private static EventAdapter eventAdapter ;
    private static Context context ;
    private static LinearLayoutManager layoutManager ;
    private DBHandler dba ;
    private int day = 0;
    private int type = 0 ;
    private Spinner spinner , spinnerDay ;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_result_championship, container, false);
        eventList = new ArrayList<>();
        recyclerView = myView.findViewById(R.id.recyclerView);
        spinner = myView.findViewById(R.id.spinner);
        spinnerDay = myView.findViewById(R.id.spinnerday);
        context = getContext() ;
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.sports_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDay.setAdapter(adapter1);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    type = position ;
                    if (type != 0) {
                        sortedList.clear();
                        for (int i = 0 ; i < eventList.size() ; i++){
                            Event event = eventList.get(i);
                            if (day != 0 ){
                                if (event.getType() == type && event.getDay() == day){
                                    sortedList.add(event);
                                }
                            }
                            else {
                                if (event.getType() == type){
                                    sortedList.add(event);
                                }
                            }
                        }
                        adapter = new ChampionAdapter(context, sortedList);
                        recyclerView.setAdapter(adapter);
                    }
                    if (type == 0) {
                        sortedList.clear();
                        if (day == 0) {

                            adapter = new ChampionAdapter(context, eventList);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            for (int i = 0 ; i < eventList.size() ; i++){
                                Event event = eventList.get(i);

                                if (event.getDay() == day){
                                    sortedList.add(event);
                                }
                            }
                            adapter = new ChampionAdapter(context, sortedList);
                            recyclerView.setAdapter(adapter);

                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    day = position ;
                    if (type != 0) {
                        sortedList.clear();
                        for (int i = 0 ; i < eventList.size() ; i++){
                            Event event = eventList.get(i);
                            if (day != 0 ){
                                if (event.getType() == type && event.getDay() == day){
                                    sortedList.add(event);
                                }
                            }
                            else {
                                if (event.getType() == type){
                                    sortedList.add(event);
                                }
                            }
                        }
                        adapter = new ChampionAdapter(context, sortedList);
                        recyclerView.setAdapter(adapter);
                    }
                    if (type == 0) {
                        sortedList.clear();
                        if (day == 0) {

                            adapter = new ChampionAdapter(context, eventList);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            for (int i = 0 ; i < eventList.size() ; i++){
                                Event event = eventList.get(i);

                                if (event.getDay() == day){
                                    sortedList.add(event);
                                }
                            }
                            adapter = new ChampionAdapter(context, sortedList);
                            recyclerView.setAdapter(adapter);

                        }
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });



        ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            loadData();
            refreshButton = myView.findViewById(R.id.refresh);
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        spinner.setSelection(0);
                        spinnerDay.setSelection(0);
                        loadData();

                    }

                }
            });
        }
        else {
            myView = inflater.inflate(R.layout.blank_button, container, false);
            TextView text = myView.findViewById(R.id.blankText);
            text.setText("Connect With Internet and then Refresh the Page");
            setHasOptionsMenu(true);
            refreshButton = myView.findViewById(R.id.refresh);

            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        //Intent intent = new Intent(getContext(), Results.class);
                        //startActivity(intent);
                        String Tag_name = getTag();
                        Fragment frg = null;
                        frg = getFragmentManager().findFragmentByTag(Tag_name);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(frg);
                        ft.attach(frg);
                        ft.commit();
                    }

                }
            });
        }



        return myView;
    }


    private void loadData( ) {
        //getting the progressbar
        final ProgressBar progressBar =  myView.findViewById(R.id.progressBar);

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
                            eventList.clear();
                            //now looping through all the elements of the json array
                            for (int i = 0; i < eventArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject eventObject = eventArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Event event = new Event(eventObject.getString("TeamA"), eventObject.getString("TeamB"),eventObject.getString("Time"), eventObject.getString("Venue"),eventObject.getInt("Type"), eventObject.getInt("Day"),eventObject.getString("Description"));

                                //adding the hero to herolist
                                eventList.add(event);
                            }
                            adapter= new ChampionAdapter(context, eventList );
                            layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);




                        } catch (JSONException e) {
                            Log.e(TAG, "Error ");
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

}