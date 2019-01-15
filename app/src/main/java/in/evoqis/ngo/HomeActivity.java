package in.evoqis.ngo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.evoqis.ngo.Activities.AboutUs;
import in.evoqis.ngo.Activities.Notification;
import in.evoqis.ngo.Activities.SignUp;
import in.evoqis.ngo.Fragments.FragmentEvents;
import in.evoqis.ngo.Activities.WhatWeDoActivity;
import in.evoqis.ngo.Fragments.FragmentActivities;
import in.evoqis.ngo.Fragments.FragmentProfile;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    private LinearLayout recentEvents, upcommingEvents, whatWeDo, aboutUs, settings;
    private TextView mTextMessage;
    ImageView imgDrawer,imgNotify;
    FloatingActionButton fab;
    String value;
    Toolbar toolbar;
    SharedPreferences sharedpreference;
    BottomNavigationView navigation;
    LinearLayout llheader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        initListner();

     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
*/
        //bottom navigation Listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initListner() {
        imgDrawer.setOnClickListener(this);
        imgNotify.setOnClickListener(this);
    }

    private void initUI() {
        imgDrawer = findViewById(R.id.imgDrawer);
        imgNotify = findViewById(R.id.imgNotify);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mTextMessage = (TextView) findViewById(R.id.txtMain);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        llheader = findViewById(R.id.llheader);
        // llheader.setVisibility(View.VISIBLE);

        mTextMessage.setText("Activities");
        navigation.getMenu().getItem(1).setChecked(true);
        loadFragment(new FragmentActivities());

    }

    /*// Creates and displays a notification
    private void notification() {

        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ngo_logo)
                .setContentTitle("You Logged in Successfully")
                .setContentText("Welcome To Global Indiands Forum");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }*/


    //Image for loading the fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Events:
                    mTextMessage.setText("Events");
                    loadFragment(new FragmentEvents());
                    llheader.setVisibility(View.VISIBLE);
                    navigation.getMenu().getItem(0).setChecked(true);
                    return true;
                case R.id.navigation_Activities:

                    mTextMessage.setText("Activities");
                    loadFragment(new FragmentActivities());
                    llheader.setVisibility(View.VISIBLE);
                    navigation.getMenu().getItem(1).setChecked(true);
                    return true;
                case R.id.navigation_Profile:
                  /*  if(value == sharedpreference.getString("button_value", "")){
                        Intent guest = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(guest);
                    }
                    else {*/
                        mTextMessage.setText("Profile");
                        loadFragment(new FragmentProfile());
                        llheader.setVisibility(View.GONE);
                        navigation.getMenu().getItem(2).setChecked(true);
                    //}
                    return true;
            }
            return false;
        }
    };


    int doubleBackToExitPressed = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressed == 2) {
            //logOut();
            finishActivity(0);
            finishAffinity();
            System.exit(0);

        } else {
            mTextMessage.setText("Activities");
            navigation.getMenu().getItem(1).setChecked(true);
            loadFragment(new FragmentActivities());
            llheader.setVisibility(View.VISIBLE);
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press , again to exit", Toast.LENGTH_SHORT).show();

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed = 1;

            }
        }, 1500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imgDrawer:
                mTextMessage.setText("Profile");
                loadFragment(new FragmentProfile());
                llheader.setVisibility(View.GONE);
                navigation.getMenu().getItem(2).setChecked(true);
                break;
            case R.id.imgNotify:
                Intent notify = new Intent(this, Notification.class);
                startActivity(notify);
                break;
        }
    }
}
