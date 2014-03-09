package org.nupter.mmdaily.ui;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import org.nupter.mmdaily.R;

/**
 * Author: linxiangyu
 * Date:   3/2/14
 * Time:   3:24 AM
 */
public class ReadPageActivityTest extends ActivityInstrumentationTestCase2<ReadPageActivity> {

    /**
     * 一个Android Activity的Example，是<a href="https://developer.android.com/tools/testing/testing_android.html">官方教程的简化版</a>
     */

    private ReadPageActivity mActivity;
    private Button mButton;


    public ReadPageActivityTest() {
        super(ReadPageActivity.class);    // 整个测试开始之前构建的初始化条件。注意这个构造函数的样子，写错了运行测试的时候这个类就不会被调用了
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();       // 每个TestCase开始之前都会调用，在这里初始化一些成员变量，可以避免TestCase对变量的修改的影响

        setActivityInitialTouchMode(false); //如果你的测试要触发触摸或者键盘输入，那么必须手动关闭掉Touch Mode

        mActivity = getActivity();    // 获取activity

        mButton = (Button) mActivity.findViewById(
               R.id.ReadPageBack
        );

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();    // 每个TestCase结束之后都会调用
    }



    public void testPreConditions() {
        assertTrue(mButton != null);
    } // 测试一些初始条件，比如你的UI控件不为空。


    public void testBaseUI(){
        assertEquals(mButton.getText(), "返回");
        mActivity.runOnUiThread(
                new Runnable() {
                    public void run() {
                        mButton.requestFocus();
                        mButton.callOnClick();
                    } //
                }); // UI控件的改变必须这样运行在一个runOnUiThread 中

    }  // 测试一些UI事件

    public void testStatePause() {
        Instrumentation mInstr = this.getInstrumentation();
        mInstr.callActivityOnPause(mActivity);
        assertEquals(mButton.getText(), "返回");
    } // 测试一些App状态转移的时候的样子
}


