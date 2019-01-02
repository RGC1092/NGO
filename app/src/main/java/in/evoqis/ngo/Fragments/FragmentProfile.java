package in.evoqis.ngo.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.evoqis.ngo.Activities.AboutUs;
import in.evoqis.ngo.Activities.Notification;
import in.evoqis.ngo.Activities.WhatWeDoActivity;
import in.evoqis.ngo.HomeActivity;
import in.evoqis.ngo.R;

import static android.app.Activity.RESULT_OK;


public class FragmentProfile extends Fragment implements View.OnClickListener {

    View view;
    private LinearLayout recentEvents, upcommingEvents, whatWeDo, aboutUs, settings;
    private static final int PICK_IMAGE_REQUEST = 0;
    private ImageView imgProfile;
    private final String TAG = "Main Activity";
    private Uri mImageUri;


    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI();


        initListner();

        return view;
    }

    private void initUI() {

        imgProfile = view.findViewById(R.id.profileImage);
        recentEvents = view.findViewById(R.id.llRecentEvent);
        upcommingEvents = view.findViewById(R.id.llUpcomming);
        whatWeDo = view.findViewById(R.id.llWho);
        aboutUs = view.findViewById(R.id.llAboutus);
        settings = view.findViewById(R.id.llSetting);

           /* SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String mImageUri = preferences.getString("image", null);

            if (mImageUri != null) {
                imgProfile.setImageURI(Uri.parse(mImageUri));
            } else {
                imgProfile.setImageResource(R.drawable.male);
            }*/

    }

    private void initListner() {

        recentEvents.setOnClickListener(this);
        upcommingEvents.setOnClickListener(this);
        whatWeDo.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        settings.setOnClickListener(this);
    }


    /**
     * Select an image
     */
    public void imageSelect() {
        permissionsCheck();
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.llRecentEvent:
                Intent recent = new Intent(getContext(), HomeActivity.class);
                startActivity(recent);
                break;
            case R.id.llUpcomming:
                Intent notify = new Intent(getContext(), Notification.class);
                startActivity(notify);
                break;
            case R.id.llWho:
                Intent whatwedo = new Intent(getContext(), WhatWeDoActivity.class);
                startActivity(whatwedo);
                break;
            case R.id.llAboutus:
                Intent aboutus = new Intent(getContext(), AboutUs.class);
                startActivity(aboutus);
                break;
            case R.id.llSetting:
                Intent settings = new Intent(getContext(), Notification.class);
                startActivity(settings);
                break;


        }

    }

/*
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            // Check which request we're responding to
            if (requestCode == PICK_IMAGE_REQUEST) {
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {
                    // The user picked a image.
                    // The Intent's data Uri identifies which item was selected.
                    if (data != null) {

                        // This is the key line item, URI specifies the name of the data
                        mImageUri = data.getData();

                        // Saves image URI as string to Default Shared Preferences
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("image", String.valueOf(mImageUri));
                        editor.commit();

                        // Sets the ImageView with the Image URI
                        imgProfile.setImageURI(mImageUri);
                        imgProfile.invalidate();
                    }
                }
            }
        }
*/

    /**
     * Clear Default Shared Preferences
     * /*
     *//*
    public void clearData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        getActivity().finish();
        startActivity(getActivity().getIntent());
    }



            imgProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // clearData();
                    imageSelect();
                }
            });


        */

}


