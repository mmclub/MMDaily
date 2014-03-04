package org.nupter.mmdaily.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;
import org.nupter.mmdaily.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-14
 * Time: 上午12:43
 * To change this template use File | Settings | File Templates.
 */
public class CornerListView extends ListView {

    public CornerListView(Context context) {
        super(context);
    }

    public CornerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CornerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int itemnum = pointToPosition(x, y);
                if (itemnum == AdapterView.INVALID_POSITION)
                    break;
                else {
                    if (itemnum == 0) {
                        setSelector(R.drawable.app_list_corner_round_top);
                    } else if (itemnum == (getAdapter().getCount() - 1))
                        //最后一项
                        setSelector(R.drawable.app_list_corner_round_bottom);
                    else {
                        //中间项
                        setSelector(R.drawable.app_list_corner_round_center);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
