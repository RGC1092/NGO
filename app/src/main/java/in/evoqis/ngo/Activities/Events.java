package in.evoqis.ngo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import in.evoqis.ngo.CustomAdapter;
import in.evoqis.ngo.R;

public class Events extends AppCompatActivity {




        // ArrayList for person names, email Id's and mobile numbers
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> user_id = new ArrayList<>();
        ArrayList<String> unique_event_id = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_events);

            // get the reference of RecyclerView
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            // set a LinearLayoutManager with default vertical orientation
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            try {
                // get JSONObject from JSON file
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                // fetch JSONArray named users
                JSONArray userArray = obj.getJSONArray("Event");
                // implement for loop for getting users list data
                for (int i = 0; i < userArray.length(); i++) {
                    // create a JSONObject for fetching single user data
                    JSONObject userDetail = userArray.getJSONObject(i);
                    // fetch email and name and store it in arraylist
                    id.add(userDetail.getString("id"));
                    user_id.add(userDetail.getString("user_id"));
                    // create a object for getting contact data from JSONObject
                    JSONObject contact = userDetail.getJSONObject("unique_event_id");
                    // fetch mobile number and store it in arraylist
                    unique_event_id.add(contact.getString("unique_event_id"));
                }
            } catch (JSONException e) {
            e.printStackTrace();
                Toast.makeText(this, ""+ e, Toast.LENGTH_SHORT).show();
            }

            //  call the constructor of CustomAdapter to send the reference and data to Adapter
            CustomAdapter customAdapter = new CustomAdapter(Events.this, id, user_id, unique_event_id);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        }

        public String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("https://www.dropbox.com/s/9umsbg8tnl5wuqv/upcoming_events.json?dl=1");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();

                Log.v("rahul",""+ex);
                return null;
            }
            return json;
        }
    }