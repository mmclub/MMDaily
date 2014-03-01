package org.nupter.mmdaily.ui;

import android.test.ActivityInstrumentationTestCase2;
import org.nupter.mmdaily.ui.ActivityListActivity;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class org.nupter.mmdaily.ui.ActivityListActivityTest \
 * org.nupter.mmdaily.tests/android.test.InstrumentationTestRunner
 */
public class ActivityListActivityTest extends ActivityInstrumentationTestCase2<ActivityListActivity> {

    public ActivityListActivityTest() {
        super("org.nupter.mmdaily", ActivityListActivity.class);
    }

}
