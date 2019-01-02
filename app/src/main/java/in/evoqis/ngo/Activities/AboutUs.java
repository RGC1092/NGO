package in.evoqis.ngo.Activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

import in.evoqis.ngo.R;

public class AboutUs extends AppCompatActivity {
TextView txtMission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initUI();
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void initUI() {
        txtMission=findViewById(R.id.txtmission);
        txtMission.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
    }
}
