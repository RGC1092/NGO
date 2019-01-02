package in.evoqis.ngo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import in.evoqis.ngo.Fragments.FragmentEvents;
import in.evoqis.ngo.Activities.WhatWeDoActivity;
import in.evoqis.ngo.Fragments.FragmentActivities;
import in.evoqis.ngo.Fragments.FragmentProfile;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
Context context=this;
    private LinearLayout recentEvents,upcommingEvents, whatWeDo, aboutUs, settings;
    private TextView mTextMessage;
    ImageView imgDrawer;
    FloatingActionButton fab;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;

    BottomNavigationView navigation;
    LinearLayout llheader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
      // initListner();

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

        //Drawer menu navigation Listener
        navigationView.setNavigationItemSelectedListener(this);


        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }

   /* private void initListner() {
       recentEvents.setOnClickListener(this);
        upcommingEvents.setOnClickListener(this);
        whatWeDo.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        settings.setOnClickListener(this);
    }*/
    private void initUI() {
        imgDrawer = findViewById(R.id.imgDrawer);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mTextMessage = (TextView) findViewById(R.id.txtMain);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        llheader = findViewById(R.id.llheader);
        recentEvents = findViewById(R.id.llRecent);
        upcommingEvents = findViewById(R.id.llUpcomming);
        whatWeDo =findViewById(R.id.llWho);
        aboutUs = findViewById(R.id.llAboutus);
        settings = findViewById(R.id.llSetting);
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
                    mTextMessage.setText("Profile");
                    loadFragment(new FragmentProfile());
                    llheader.setVisibility(View.GONE);
                    navigation.getMenu().getItem(2).setChecked(true);
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
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                mTextMessage.setText("Activities");
                navigation.getMenu().getItem(1).setChecked(true);
                loadFragment(new FragmentActivities());
                doubleBackToExitPressed++;
                Toast.makeText(this, "Please press , again to exit", Toast.LENGTH_SHORT).show();
            }


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.llRecentEvent) {
            Intent recent = new Intent(this, HomeActivity.class);
            startActivity(recent);
            // Handle the camera action
        }else if (id == R.id.llUpcomming) {
            Intent notify = new Intent(this, Notification.class);
            startActivity(notify);

        } else if (id == R.id.llWho) {
            Intent whatwedo = new Intent(this, WhatWeDoActivity.class);
            startActivity(whatwedo);

        } else if (id == R.id.llAboutus) {
            Intent aboutus = new Intent(this, AboutUs.class);
            startActivity(aboutus);

        } else if (id == R.id.llSetting) {
            Intent settings = new Intent(this, Notification.class);
            startActivity(settings);

        } else if (id == R.id.llLogout) {
            Toast.makeText(context, "Loguot Successfully", Toast.LENGTH_SHORT).show();
            finish();

        }




        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {

/*
        switch (view.getId()) {

            case R.id.llRecent:
                Intent recent = new Intent(this, HomeActivity.class);
                startActivity(recent);
                break;
            case R.id.llUpcomming:
                Intent notify = new Intent(this, Notification.class);
                startActivity(notify);
                break;
            case R.id.llWho:
                Intent whatwedo = new Intent(this, WhatWeDoActivity.class);
                startActivity(whatwedo);
                break;
            case R.id.llAboutus:
                Intent aboutus = new Intent(this, AboutUs.class);
                startActivity(aboutus);
                break;
            case R.id.llSetting:
                Intent settings = new Intent(this, Notification.class);
                startActivity(settings);
                break;


        }
*/
    }
}
