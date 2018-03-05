package iitropar.aarohan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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

public class Anouncement extends AppCompatActivity {

    private static final String JSON_URL = "`";
    private ArrayList<AnnouncementModel> dataList ;
    private RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement);

        dataList = new ArrayList<>();        
        recyclerView = findViewById(R.id.recycler_announcement);
        loadData();

    }
    
    private void loadData( ) {
        //getting the progressbar
        final ProgressBar progressBar = findViewById(R.id.progressBar);

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
                            JSONArray dataArray = obj.getJSONArray("Sheet1");
                            dataList.clear();
                            //now looping through all the elements of the json array
                            for (int i = 0; i < dataArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject dataObject = dataArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                AnnouncementModel data = new AnnouncementModel(dataObject.getString("title"), dataObject.getString("description"),dataObject.getString("time"));

                                //adding the hero to herolist
                                dataList.add(data);
                            }
                            AnnouncementAdapter adapter = new AnnouncementAdapter(getApplicationContext(), dataList);
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

}
