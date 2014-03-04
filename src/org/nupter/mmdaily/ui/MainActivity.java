package org.nupter.mmdaily.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.nupter.mmdaily.R;

public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private ListView mListView;
    private DrawerLayout mDrawerLayout;
    String[] mListName ={"HotNews","IWrite","MyStory","Others"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.main);


        mListView = (ListView)findViewById(R.id.listView);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,mListName);
        mListView.setAdapter(arrayAdapter);

        mDrawerLayout.setDrawerListener(new ActionBarDrawerToggle(this,
                mDrawerLayout,R.drawable.ic_launcher,R.string.app_name,R.string.title_activity_main){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("content",mListName[i]);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout,fragment);
                mListView.setItemChecked(i, true);
                mDrawerLayout.closeDrawer(mListView);

            }
        });
    }

}
