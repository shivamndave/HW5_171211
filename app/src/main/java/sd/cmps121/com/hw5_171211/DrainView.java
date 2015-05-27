package sd.cmps121.com.hw5_171211;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by shivamndave on 5/26/15.
 */
public class DrainView extends View {

    private Paint tomatoPaint;
    private Paint drainPaint;
    private Paint line1Paint;
    private Paint line2Paint;

    private int _tomatoStartX;
    private int _tomatoStartY;

    private float _xLoc;
    private float _yLoc;

    private float getTomatoX() {
        return _xLoc;
    }

    private float getTomatoY() {
        return _yLoc;
    }

    public void setTomatoLoc(float xLoc, float yLoc) {
        _xLoc = xLoc;
        _yLoc = yLoc;
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
        this.invalidate();
    }

    public DrainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDrainView();
    }

    protected void initDrainView() {
        setFocusable(true);

        Resources r = this.getResources();

        int mMeasuredWidth = getMeasuredWidth();
        int mMeasuredHeight = getMeasuredHeight();

        tomatoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tomatoPaint.setColor(r.getColor(R.color.tomato_color));
        tomatoPaint.setStrokeWidth(1);
        tomatoPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        _tomatoStartX = mMeasuredWidth / 2;
        _tomatoStartY = mMeasuredHeight / 8;

        drainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drainPaint.setColor(r.getColor(R.color.drain_color));
        drainPaint.setStrokeWidth(1);
        drainPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        line1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        line1Paint.setColor(r.getColor(R.color.line_color));
        line1Paint.setStrokeWidth(4);
        line1Paint.setStyle(Paint.Style.FILL_AND_STROKE);

        line2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        line2Paint.setColor(r.getColor(R.color.line_color));
        line2Paint.setStrokeWidth(4);
        line2Paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mMeasuredWidth = getMeasuredWidth();
        int mMeasuredHeight = getMeasuredHeight();

        int drainX = mMeasuredWidth / 2;
        int drainY = mMeasuredHeight - (mMeasuredHeight / 8);

        _tomatoStartX += getTomatoX();
        _tomatoStartY += getTomatoY();

        int line1StartX = -100;
        int line1StartY = mMeasuredHeight / 4;
        int line1EndX = mMeasuredWidth - (mMeasuredWidth / 3);
        int line1EndY = mMeasuredHeight / 4;

        int line2StartX = mMeasuredWidth;
        int line2StartY = mMeasuredHeight - (mMeasuredHeight / 4);
        int line2EndX = mMeasuredWidth / 3;
        int line2EndY = mMeasuredHeight - (mMeasuredHeight / 4);

        int tomatoRadius = 50;
        int drainRadius = 75;

        canvas.drawCircle(_tomatoStartX, _tomatoStartY, tomatoRadius, tomatoPaint);
        canvas.drawCircle(drainX, drainY, drainRadius, drainPaint);
        canvas.drawLine(line1StartX, line1StartY, line1EndX, line1EndY, line1Paint);
        canvas.drawLine(line2StartX, line2StartY, line2EndX, line2EndY, line2Paint);

        canvas.save();
        canvas.restore();
    }

}
