package fiftyfive.and_samplefirebasegabq;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import static android.R.attr.id;
import static android.R.attr.maxDate;
import static android.R.attr.name;
import static java.lang.Boolean.TRUE;

public class SplashScreen extends AppCompatActivity {


    private FirebaseAnalytics mFirebaseAnalytics; //Firebase Analytics object
    private static int SPLASH_TIME_OUT = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Utils.configureSingleton(mFirebaseAnalytics);
        mFirebaseAnalytics.setCurrentScreen(this, Utils.getActivityName(this), Utils.getActivityName(this)); // Sets the current screen name, which specifies the current visual context in your app


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, HomePage.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
