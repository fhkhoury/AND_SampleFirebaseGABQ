package fiftyfive.and_samplefirebasegabq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HomePage extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics; //Firebase Analytics object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Obtain the FirebaseAnalytics instance & configure singleton
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Utils.configureSingleton(mFirebaseAnalytics);

        //TODO : FOnction pour pousser le screenview
        // scrrenview tracking - Firebase datalayer
        Bundle params = new Bundle();
        params.putString("screenName", "HomePage");
        // button tracking - Send the event to Firebase Analytics
        mFirebaseAnalytics.logEvent("screenView", params);

        Button goToListe = (Button) findViewById(R.id.goToListe);
        goToListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Liste.class);
                startActivity(i);
            }
        });

        Button infos = (Button) findViewById(R.id.informations);
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Informations.class);
                startActivity(i);
            }
        });


    }
}
