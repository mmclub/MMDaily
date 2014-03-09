package org.nupter.mmdaily.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.nupter.mmdaily.R;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 14-3-1
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public class ReadPageActivity extends Activity {

    private Button mBackButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_page);

        mBackButton = (Button) findViewById(R.id.ReadPageBack);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadPageActivity.this.finish();
            }
        });
    }
}
