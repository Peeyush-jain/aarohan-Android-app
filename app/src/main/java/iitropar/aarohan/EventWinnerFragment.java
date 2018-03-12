package iitropar.aarohan;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class EventWinnerFragment extends  Fragment{
    public EventWinnerFragment() {
        // Required empty public constructor
    }
    private static final String JSON_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=_Flg0sJcDYCt9bxxR9lR7h8N4BjzZJFMeBwWBN08S--hk1oZEfXQksksGLn4rngnwNfFrVSlLHxUnzRxtAo7zfM30nGv-JU_OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1ZsYSbt7G4nMhEEDL32U4DxjO7V7yvmJPXJTBuCiTGh3rUPjpYM_V0PJJG7TIaKpwabY76-ym4KTZGlawn9n9m-A3EjxKiEwhqMSAQNzj7hlMACkaDAfg4yguR9iXXDcsKiW3k6MDkf31SIMZH6H4k&lib=MbpKbbfePtAVndrs259dhPT7ROjQYJ8yx";
    ArrayList<EventWinnerModel> winnerList;
    private RecyclerView recyclerView ;
    private Button refreshButton ;
    private static LinearLayoutManager layoutManager ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    View myView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_event_winner, container, false);
        winnerList = new ArrayList<>();

        recyclerView = myView.findViewById(R.id.recyclerView);


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
                            JSONArray collegeArray = obj.getJSONArray("Sheet1");
                            winnerList.clear();
                            //now looping through all the elements of the json array
                            for (int i = 0; i < collegeArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject eventObject = collegeArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                EventWinnerModel event = new EventWinnerModel(eventObject.getString("Name"), eventObject.getString("Winner"),eventObject.getString("Sec"),eventObject.getString("Third"));

                                //adding the hero to herolist
                                winnerList.add(event);
                            }
                            EventWinnerAdapter adapter = new EventWinnerAdapter(getContext(), winnerList);
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
                    public void onErrorResponse(VolleyError volleyError) {
                        //displaying the error in toast if occurrs
                        String message = null;
                        progressBar.setVisibility(View.GONE);
                        if (volleyError instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                            progressBar.setVisibility(View.GONE);
                        } else if (volleyError instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (volleyError instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (volleyError instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
                    }
                });



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

}



