package in.evoqis.ngo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.evoqis.ngo.Activities.SignUp;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvForgotPassword, tvguest, tvSkip;
    private Button btnLogin, btnSignup;
    private EditText etUserName, etPassword;
    SharedPreferences sharedpreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreference=PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        initUI();
        initListner();

    }

    private void initListner() {

        tvForgotPassword.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    //initialization of all the components
    private void initUI() {


        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvguest = findViewById(R.id.tvguest);
        tvSkip = findViewById(R.id.tvSkip);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        tvguest.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvForgotPassword.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


    }

    // Creates and displays a notification
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
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                if (etUserName.getText().toString().trim().equals("")) {
                    etUserName.setError("Username is required");
                } else if (etPassword.getText().toString().trim().equals("")) {
                    etPassword.setError("Password is required");
                } else {
                    notification();
                    Toast.makeText(this, "Successfully Logged Inn", Toast.LENGTH_SHORT).show();
                    //new CheckLogin().execute();
                    Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent1);

                }
                break;

            case R.id.tvForgotPassword:

                Toast.makeText(this, "Ohh..!! \nIt seems like you Forget your Password", Toast.LENGTH_SHORT).show();

                // finish();
              /*  Intent intent1 = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(intent1);*/
                break;

            case R.id.btnSignup:
                Toast.makeText(this, "Wel-Come..!! \nYou are very close to Join us.", Toast.LENGTH_SHORT).show();

                finish();
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.tvguest:
                Toast.makeText(this, "Wel-Come..!! \nLogin with Guest", Toast.LENGTH_SHORT).show();
              //  sharedpreference.edit().putString("button_value",tvguest.getText().toString()).apply();1
                Intent guest = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(guest);
                break;
        }


    }


}
