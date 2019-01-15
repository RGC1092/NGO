package in.evoqis.ngo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.evoqis.ngo.HomeActivity;
import in.evoqis.ngo.LoginActivity;
import in.evoqis.ngo.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etOtp, etMail, etMobile;
    AutoCompleteTextView etCity;
    TextView btnSignIn;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
        initListner();
    }

    private void initListner() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void initUI() {
        btnSignUp = findViewById(R.id.btnSignup);
        btnSignIn = findViewById(R.id.tvalreadyAccount);
        etName = findViewById(R.id.etName);
        etCity = findViewById(R.id.etCity);
        etOtp = findViewById(R.id.etPassword);
        etMail = findViewById(R.id.etMail);
        etMobile = findViewById(R.id.etMobile);


    }

    @Override
    public void onBackPressed() {
        finish();
        Intent login = new Intent(this, LoginActivity .class);
        startActivity(login);
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignup:
                if (etName.getText().toString().trim().equals("")) {
                    etName.setError("This field is required");
                } else if (etMail.getText().toString().trim().equals("")) {
                    etMail.setError("This field is required");
                } else if (etCity.getText().toString().trim().equals("")) {
                    etCity.setError("This field is required");
                } else if (etMobile.getText().toString().trim().equals("")) {
                    etMobile.setError("This field is required");
                } else {

                    Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    //new CheckLogin().execute();
                    Intent intent1 = new Intent(SignUp.this, LoginActivity.class);
                    startActivity(intent1);

                }
                break;
            case R.id.tvalreadyAccount:
                Intent signin = new Intent(SignUp.this, LoginActivity.class);
                startActivity(signin);
                break;
        }
    }
}
