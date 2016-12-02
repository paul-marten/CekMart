package pam.develops.cekmart.Analytics;

/**
 * Created by paulms on 5/14/2016.
 */
import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google. android.gms.analytics.Tracker;
import pam.develops.cekmart.R;

public class AnalyticsApplication extends Application {
    private Tracker mTracker;
    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics =
                    GoogleAnalytics. getInstance(this);
            // To enable debug logging use: adb shell setproplog.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml. global_tracker);
        }
        return mTracker;
    }
}
