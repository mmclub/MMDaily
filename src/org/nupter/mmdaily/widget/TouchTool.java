package org.nupter.mmdaily.widget;

/**
 * Class: TouchTool.java<br>
 * Date: 2013/04/04<br>
 * Author: TiejianSha <br>
 * Email: tntshaka@gmail.com<br>
 */
/*这个是一个工具类，但是中间yy为什么这么算我就不知道了，估计是作者试出的最佳方式*/
public class TouchTool {

    private int startX, startY;
    private int endX, endY;

    public TouchTool(int startX, int startY, int endX, int endY) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getScrollX(float dx) {
        int xx = (int) (startX + dx / 2.5F);
        return xx;
    }

    public int getScrollY(float dy) {
        //不太懂，为什么是这个样子
        int yy = (int) (startY + dy / 2.5F);
        //Log.d("yy_number:",""+yy);
        return yy;
    }
}