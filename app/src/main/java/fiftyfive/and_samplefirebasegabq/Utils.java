package fiftyfive.and_samplefirebasegabq;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import static java.lang.Boolean.TRUE;

/**
 * Created by Francois on 15/03/2017.
 */

public class Utils extends Activity {

    public static String instance_id = FirebaseInstanceId.getInstance().getId();

    // A function to get the name of an activity
    public static String getActivityName(Activity activity){
        PackageManager packageManager = activity.getPackageManager();
        try {
            ActivityInfo info = packageManager.getActivityInfo(activity.getComponentName(), 0);
            Log.e("app", "Activity name:" + info.name);
            return info.name;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Firebase Analytics configuration stream
    public static void configureSingleton(FirebaseAnalytics firebaseSingleton) {
        String instance_id = FirebaseInstanceId.getInstance().getId();
        firebaseSingleton.setUserId(instance_id); //Set a default Firebase user id based on the Firebase Instance id
        firebaseSingleton.setMinimumSessionDuration(0); // Sets the minimum engagement time required before starting a session. Setted to "0"
        firebaseSingleton.setAnalyticsCollectionEnabled(TRUE); // Activate Firebase Analytics data collection
    }
}
