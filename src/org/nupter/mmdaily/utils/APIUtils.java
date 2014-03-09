package org.nupter.mmdaily.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Author: linxiangyu
 * Date:   3/1/14
 * Time:   3:52 PM
 */
public class APIUtils {

    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(url, params, handler);
    }
}
