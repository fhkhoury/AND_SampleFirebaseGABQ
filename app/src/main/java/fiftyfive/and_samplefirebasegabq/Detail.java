package fiftyfive.and_samplefirebasegabq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Detail extends AppCompatActivity {


    private FirebaseAnalytics mFirebaseAnalytics; //Firebase Analytics object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getApplicationContext();
        //Définition du toast
        CharSequence text = "CALL MADE !";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);

        setContentView(R.layout.activity_detail);

        // Obtain the FirebaseAnalytics instance & configure singleton
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Utils.configureSingleton(mFirebaseAnalytics);

        // scrrenview tracking - Firebase datalayer
        Bundle params = new Bundle();
        params.putString("screenName", "Detail");
        // button tracking - Send the event to Firebase Analytics
        mFirebaseAnalytics.logEvent("screenView", params);

        Button call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.show();
            }
        });

        Button infos = (Button) findViewById(R.id.informations);
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Detail.this, Informations.class);
                startActivity(i);
            }
        });
    }

}
