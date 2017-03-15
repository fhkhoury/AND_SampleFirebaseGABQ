package fiftyfive.and_samplefirebasegabq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.util.Log.INFO;
import static fiftyfive.and_samplefirebasegabq.R.id.call;
import static fiftyfive.and_samplefirebasegabq.R.id.crash;

public class Informations extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);


        //App Name
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setText(appName.getText() + " " + getResources().getString(R.string.app_name) );

        TextView udid = (TextView) findViewById(R.id.udid);
        //appName.setText(appName.getText() + " " + Context.getSystemService(Context.TELEPHONY_SERVICE).getDeviceID() );

        Context context = getApplicationContext();
        //Définition du toast
        CharSequence text = "INFO - Non-fatal error well reported!";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);

        Button crash = (Button) findViewById(R.id.crash);
        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Report a non-fatal error in Crash Reporting tool
                FirebaseCrash.logcat(INFO, "INFO", "Non-fatal error well reported"); // Logs a message that will appear in a subsequent crash report as well as in logcat
                FirebaseCrash.report(new Exception()); // Report the exception in to the dashboard
                toast.show();
            }
        });
    }

}
