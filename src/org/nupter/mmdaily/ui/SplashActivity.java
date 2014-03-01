package org.nupter.mmdaily.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import org.nupter.mmdaily.R;

/**
 * Created by Inner on 14-3-1.
 */
public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
        public void run() {
                 Intent mSplashIntent = new Intent(SplashActivity.this,
                                             MainActivity.class);
                              SplashActivity.this.startActivity(mSplashIntent);
                           SplashActivity.this.finish();
                        }

                        }, SPLASH_DISPLAY_LENGHT);

           }


}
