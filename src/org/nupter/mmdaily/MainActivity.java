package org.nupter.mmdaily;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.squareup.picasso.Picasso;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nupter.mmdaily.widget.PinnedSectionListView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*花了一些时间，大致的完成对代码的注释，还有很多没有弄，主要是想换个事情做做，过几天，会把
 * SeekBar重新做一下改进，勉强可以看，如果有问题，欢迎致电我的email：www.dai1019439568@gmail.com
 */

public class MainActivity extends Activity {

    /*这里定义的Item类，*/
    static class Item {

        public static final int ITEM = 0;
        public static final int SECTION = 1;

        public final int type;
        public String text;

        /*sectionPosition代表的的是每个标签栏的位置，如：A，B。。。(带颜色的Item)
        *而listPosition代表的是每个小标签的位置，这里的设定，可以对Item点击后内容获取取得方便
        */
        public int sectionPosition;
        public int listPosition;
        public String imageurl;
        public String texturl;
        public String timeSection;

        public Item(int type, String timeSection) {
            this.type = type;
            this.timeSection = timeSection;
        }

        public Item(int type, String text, String imageurl, String texturl) {
            this.type = type;
            this.text = text;
            this.imageurl = imageurl;
            this.texturl = texturl;
        }

        @Override
        public String toString() {
            return text;
        }

    }

    /*
    *生成一个PinnedSectionListView对象
    */
    private Button next_button;

    private ImageView headimage;

    private PinnedSectionListView MainLv;

    private View head;

    private Handler mHandler = null;

    private static ArrayList<Item> arrayList = new ArrayList<Item>();

    private SimpleAdapter simpleAdapter;

    private static final String MAIN_URL = "http://zhihudaily.sinaapp.com/";

    private static ArrayList<String> ZURLS = new ArrayList();

    private static boolean ClickFlag = false;

    private static int URL_NUM = 0;

    private static String TimeSection;

    private static String NextUrl;

    private static String ImgUrl;

    public static int sectionPosition = 0, listPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
/*
 *     利用LayoutInflater动态载入XML布局文件，并且实例化，并且使用inflate返回一个View
 *     对象，且将ViewGroup设置为null，将此View作为根
*/
        next_button = (Button) findViewById(R.id.next_button);

        head = LayoutInflater.from(this).inflate(R.layout.item_main_viewheader,
                null);

        headimage = (ImageView) findViewById(R.id.path_headimage);

        MainLv = (PinnedSectionListView) findViewById(R.id.main_list);

        MainLv.setTopView((ImageView) findViewById(R.id.iv_top));

        MainLv.setHeadView(head);
        MainLv.addHeaderView(head);

        simpleAdapter = new SimpleAdapter(MainActivity.this, arrayList);
        MainLv.setAdapter(simpleAdapter);

        //这里拿到了点击的链接的地址，即为storylink
        MainLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item clickitem = (Item) MainLv.getItemAtPosition(i);
                String storylink = clickitem.texturl;
                Log.d("clownqiangtest", "storylink:" + storylink);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false == ClickFlag){
                    ClickFlag = true;
                    mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.arg1) {
                                case 0:
                                    simpleAdapter.notifyDataSetChanged();
                                    ClickFlag = false;
                                    break;
                            }
                            super.handleMessage(msg);
                        }
                    };
                    new Thread() {

                        public void run() {

                            StartZhiHu(NextUrl, ZURLS);
                            mHandler.sendEmptyMessage(0);
                            ZURLS.clear();

                        }

                        ;
                    }.start();
                }
            }
        });

       /*调用了PinnedSectionListView中的方法，为TopView设置了ImageView*/

       /*//调用了PinnedSectionListView中的setAdapter方法，按照构造函数传值没什么说的
        MainLv.setAdapter(new SimpleAdapter(this,
				android.R.layout.simple_list_item_1, android.R.id.text1));*/

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.arg1) {
                    case 0:
                        simpleAdapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        new Thread() {

            public void run() {

                StartZhiHu(MAIN_URL, ZURLS);
                Log.d("img_link_clownqiang", "ImgUrl  " + ImgUrl);
                //Picasso.with(MainActivity.this).load(ImgUrl).error(R.drawable.ic_launcher).into(headimage);
                mHandler.sendEmptyMessage(0);
                ZURLS.clear();

            }

            ;
        }.start();

    }

    //Menu的实现
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
    *  定义了SimpleAdapter类，继承了ArrayAdapter，实现了接口PinniedSectionListAdapter
    */
    static class SimpleAdapter extends ArrayAdapter<Item> implements
            PinnedSectionListView.PinnedSectionListAdapter {

        /*定义了一个颜色的数组,在colors.xml中设置颜色*/
        private static final int[] COLORS = new int[]{R.color.green_light,
                R.color.orange_light, R.color.blue_light, R.color.red_light};

        public SimpleAdapter(Context context, ArrayList<Item> items) {
            super(context, R.layout.headview, items);

        }

        /*
        *这里的getView函数可以对每条Item的内容进行获取
        */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Item item = getItem(position);
            if (item.type != Item.SECTION) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.headview, null);
                }
                TextView head_textview = (TextView) convertView.findViewById(R.id.head_textview);
                ImageView head_imageview = (ImageView) convertView.findViewById(R.id.head_imageview);
                head_textview.setTextColor(Color.DKGRAY);
                head_textview.setGravity(Gravity.CENTER_VERTICAL);
                Log.d("item_text", item.text);
                head_textview.setText(item.text);
                Picasso.with(getContext()).load(item.imageurl).resize(120, 120).centerCrop().into(head_imageview);
                Picasso.with(getContext()).setDebugging(true);
                head_imageview.setMinimumHeight(180);
                //刚刚设置的Type值在这里起到了效果，对type值为1(即：Item.SECTION)的item设置背景色
            }
            if (item.type == Item.SECTION) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.timesection, null);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.title_textview);
                try {
                    //Log.d("timeSection", item.timeSection + "  " + item.type);
                    //Log.d("timetest", "timeSection_Position" + position + "  " + arrayList.get(position).timeSection);
                    textView.setMinimumHeight(100);
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                    textView.setText(item.timeSection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // view.setOnClickListener(PinnedSectionListActivity.this);
                convertView.setBackgroundColor(parent.getResources().getColor(
                        COLORS[item.sectionPosition % COLORS.length]));
            }
            return convertView;
        }

        /*覆写了getViewTypeCount方法*/
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        /*覆写了getItemViewType方法*/
        @Override
        public int getItemViewType(int position) {
            return getItem(position).type;
        }

        /*这里覆写了PinniedSectionListAdapter接口中的isItemViewTypePinned方法*/
        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == Item.SECTION;
        }

    }

    public void AddPinnedSection(String TimeSection) {
        Item item = new Item(Item.SECTION, TimeSection);
        item.sectionPosition = listPosition;
        sectionPosition = listPosition;
        Log.d("position_test", "sectionPosition:" + item.sectionPosition + "listPosition" + listPosition);
        item.listPosition = listPosition++;
        Log.d("img_link", listPosition + "");
        arrayList.add(item);
    }

    public void AddItemSection(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String headline_title = doc.getElementsByClass("headline-title").text();
        Elements nodes = doc.getElementsByClass("headline");
        String img_link = nodes.select("img").attr("src");
        Item item = new Item(Item.ITEM, headline_title, img_link, url);
        item.sectionPosition = sectionPosition;
        item.listPosition = listPosition++;
        Log.d("img_link", headline_title + "   " + img_link + "   " + listPosition);
        arrayList.add(item);
    }

    public ArrayList<String> WhatInToday(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String headline_time = document.getElementsByClass("headline-title").text();
        ImgUrl = MAIN_URL + document.getElementsByClass("headline").select("img").attr("src");
        Elements hreflinks = document.getElementsByClass("headline-background-link");
        for (Element hreflink : hreflinks) {
            String hreflink_text = hreflink.attr("href");
            Log.d("zhihutest", "hreflink:" + hreflink_text + "URL_NUM:" + URL_NUM);
            ZURLS.add(hreflink_text);
            URL_NUM++;
        }
        NextUrl = MAIN_URL + document.getElementsByClass("page-btn").attr("href");
        TimeSection = headline_time;


        return ZURLS;
    }

    public void StartZhiHu(String url, ArrayList<String> urls) {
        try {
            WhatInToday(url);
            AddPinnedSection(TimeSection);
            /**
             * 使用Jsoup解析html
             */
            //连接主页，获取html，开始进行解析
            for (int i = 0; i < urls.size(); i++) {
                Log.d("img_link", urls.size() + "    " + urls.get(i));
                AddItemSection(urls.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
