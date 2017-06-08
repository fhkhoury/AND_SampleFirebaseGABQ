package fiftyfive.and_samplefirebasegabq;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.util.Log.INFO;
import static fiftyfive.and_samplefirebasegabq.R.id.call;
import static fiftyfive.and_samplefirebasegabq.R.id.crash;
import static java.security.AccessController.getContext;

public class Informations extends AppCompatActivity {


    private FirebaseAnalytics mFirebaseAnalytics; //Firebase Analytics object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        // Obtain the FirebaseAnalytics instance & configure singleton
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Utils.configureSingleton(mFirebaseAnalytics);

        //App Name
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setText(appName.getText() + " " + getResources().getString(R.string.app_name) );

        TextView androidId = (TextView) findViewById(R.id.android_id);
        androidId.setText(androidId.getText()+ Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID));

        TextView instanceId = (TextView) findViewById(R.id.instance_id);
        instanceId.setText(instanceId.getText()+ FirebaseInstanceId.getInstance().getId());

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

                // button tracking - Firebase datalayer
                Bundle params = new Bundle();
                params.putString("eventCategory", "error");
                params.putString("eventAction", "non-fatal_bug");
                params.putString("eventLabel", this.getClass().getName()+"_non-fatal");
                // button tracking - Send the event to Firebase Analytics
                mFirebaseAnalytics.logEvent("trackEvent", params);

            }
        });
    }

}
