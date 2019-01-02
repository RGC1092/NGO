package in.evoqis.ngo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.evoqis.ngo.Activities.Events;
import in.evoqis.ngo.Activities.SignUp;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvForgotPassword,tvguest;
    private Button btnLogin,btnSignup;
    private EditText etUserName, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();

    }

    //initialization of all the components
    private void initUI() {



        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvguest = findViewById(R.id.tvguest);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);

        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

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
        }



    }



}
