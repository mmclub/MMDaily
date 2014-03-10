package org.nupter.mmdaily.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import org.nupter.mmdaily.R;

/**
 * Created by sudongsheng on 14-3-1.
 */
public class SettingActivity extends Activity {

    private ToggleButton autoDownload;
    private ToggleButton nightMode;
    private ToggleButton noPicMode;
    private ToggleButton pushMessage;
    private TextView download;
    private TextView textSize;
    private LinearLayout clear;
    private LinearLayout share;
    private LinearLayout suggestion;
    private LinearLayout update;
    private LinearLayout about;

    private SharedPreferences mySharedPreferences;

    private boolean autoDownload_flag;
    private boolean nightMode_flag;
    private boolean noPicMode_flag;
    private boolean pushMessage_flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        findViewByIds();
        setOnClickListeners();
        autoDownload_flag = mySharedPreferences.getBoolean("autoDownload", false);
        nightMode_flag = mySharedPreferences.getBoolean("nightMode", false);
        noPicMode_flag = mySharedPreferences.getBoolean("noPicMode", false);
        pushMessage_flag = mySharedPreferences.getBoolean("pushMessage", false);
        autoDownload.setChecked(autoDownload_flag);
        nightMode.setChecked(nightMode_flag);
        noPicMode.setChecked(noPicMode_flag);
        pushMessage.setChecked(pushMessage_flag);
    }

    public void findViewByIds() {
        autoDownload = (ToggleButton) findViewById(R.id.autoDownload);
        nightMode = (ToggleButton) findViewById(R.id.nightMode);
        noPicMode = (ToggleButton) findViewById(R.id.noPicMode);
        pushMessage = (ToggleButton) findViewById(R.id.pushMessage);
        download = (TextView) findViewById(R.id.download);
        textSize = (TextView) findViewById(R.id.textSize);
        share = (LinearLayout) findViewById(R.id.share);
        clear = (LinearLayout) findViewById(R.id.clear);
        suggestion = (LinearLayout) findViewById(R.id.suggestion);
        update = (LinearLayout) findViewById(R.id.update);
        about = (LinearLayout) findViewById(R.id.about);
    }

    public void setOnClickListeners() {
        autoDownload.setOnCheckedChangeListener(checkedListener);
        nightMode.setOnCheckedChangeListener(checkedListener);
        noPicMode.setOnCheckedChangeListener(checkedListener);
        pushMessage.setOnCheckedChangeListener(checkedListener);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    CompoundButton.OnCheckedChangeListener checkedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            if (buttonView.getId() == R.id.autoDownload) {
                if (isChecked)
                    autoDownload_flag = true;
                else
                    autoDownload_flag = false;
                editor.putBoolean("autoDownload", autoDownload_flag);
            } else if (buttonView.getId() == R.id.nightMode) {
                if (isChecked)
                    nightMode_flag = true;
                else
                    nightMode_flag = false;
                editor.putBoolean("nightMode", nightMode_flag);
            } else if (buttonView.getId() == R.id.noPicMode) {
                if (isChecked)
                    noPicMode_flag = true;
                else
                    noPicMode_flag = false;
                editor.putBoolean("noPicMode", noPicMode_flag);
            } else if (buttonView.getId() == R.id.pushMessage) {
                if (isChecked)
                    pushMessage_flag = true;
                else
                    pushMessage_flag = false;
                editor.putBoolean("pushMessage", pushMessage_flag);
            }
            editor.commit();
        }
    };
}
