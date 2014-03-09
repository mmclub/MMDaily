package org.nupter.mmdaily;

import android.app.Application;
import android.content.Context;

/**
 * Author: linxiangyu
 * Date:   3/1/14
 * Time:   3:40 PM
 */
public class MyApplication extends Application {
    private static Context sContext;


    public void onCreate() {
        super.onCreate();
        MyApplication.sContext = getApplicationContext();
    }

    public static Context getAppContext() {
        /**
         *  静态方法获取Application Context
         *  @see <a href="http://stackoverflow.com/questions/2002288/static-way-to-get-context-on-android">这个StackOverFlow问答</a>
         */
        return MyApplication.sContext;
    }


}
