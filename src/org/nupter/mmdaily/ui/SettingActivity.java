package org.nupter.mmdaily.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.SimpleAdapter;
import org.nupter.mmdaily.R;
import org.nupter.mmdaily.utils.CornerListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sudongsheng on 14-3-1.
 */
public class SettingActivity extends Activity{

    private CornerListView otherListView;

    private List<Map<String, String>> listData = null;
    private SimpleAdapter simpleAdapter = null;
    private Adapter adapter=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        otherListView = (CornerListView) findViewById(R.id.other);
        setListData();
        simpleAdapter = new SimpleAdapter(SettingActivity.this, listData, R.layout.view_other_adapter,
                new String[]{"text"}, new int[]{R.id.other_textView});
        otherListView.setAdapter(simpleAdapter);
    }

    private void setListData() {
        listData = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("text", "分享");
        listData.add(map);

        map = new HashMap<String, String>();
        map.put("text", "清除缓存");
        listData.add(map);

        map = new HashMap<String, String>();
        map.put("text", "意见反馈");
        listData.add(map);

        map = new HashMap<String, String>();
        map.put("text", "检查更新");
        listData.add(map);

        map = new HashMap<String, String>();
        map.put("text", "关于");
        listData.add(map);

    }

}
