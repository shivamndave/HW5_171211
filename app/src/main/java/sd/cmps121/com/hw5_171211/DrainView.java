package sd.cmps121.com.hw5_171211;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

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

        int tomatoRadius = 50;
        int drainRadius = 75;

        int drainX = mMeasuredWidth / 2;
        int drainY = mMeasuredHeight - (mMeasuredHeight / 8);

        _tomatoStartX += getTomatoX();

        if (_tomatoStartX < tomatoRadius) {
            _tomatoStartX = tomatoRadius;
        } else if (_tomatoStartX > mMeasuredWidth - tomatoRadius) {
            _tomatoStartX = mMeasuredWidth - tomatoRadius;
        }

        _tomatoStartY += getTomatoY();

        if (_tomatoStartY < tomatoRadius) {
            _tomatoStartY = tomatoRadius;
        } else if (_tomatoStartY > mMeasuredHeight - tomatoRadius) {
            _tomatoStartY = mMeasuredHeight - tomatoRadius;
        }

        int line1StartX = -100;
        int line1Y = mMeasuredHeight / 4;
        int line1EndX = mMeasuredWidth - (mMeasuredWidth / 3);

        int line2StartX = mMeasuredWidth;
        int line2Y = mMeasuredHeight - (mMeasuredHeight / 4);
        int line2EndX = mMeasuredWidth / 3;

        if ((0 <= _tomatoStartX && _tomatoStartX <= line1EndX) && Math.abs(_tomatoStartY - line1Y) < tomatoRadius) {
            int tomatoDiff1 = _tomatoStartY - line1Y;
            if (tomatoDiff1 < 0) {
                _tomatoStartY += Math.abs(tomatoDiff1) - tomatoRadius;
            } else if (tomatoDiff1 > 0) {
                _tomatoStartY -= Math.abs(tomatoDiff1) - tomatoRadius;
            }
        }

        if ((line2EndX <= _tomatoStartX && _tomatoStartX <= line2StartX) && Math.abs(_tomatoStartY - line2Y) < tomatoRadius) {
            int tomatoDiff1 = _tomatoStartY - line2Y;
            if (tomatoDiff1 < 0) {
                _tomatoStartY += Math.abs(tomatoDiff1) - tomatoRadius;
            } else if (tomatoDiff1 > 0) {
                _tomatoStartY -= Math.abs(tomatoDiff1) - tomatoRadius;
            }
        }

        canvas.drawCircle(_tomatoStartX, _tomatoStartY, tomatoRadius, tomatoPaint);
        canvas.drawCircle(drainX, drainY, drainRadius, drainPaint);
        canvas.drawLine(line1StartX, line1Y, line1EndX, line1Y, line1Paint);
        canvas.drawLine(line2StartX, line2Y, line2EndX, line2Y, line2Paint);

        canvas.save();
        canvas.restore();
    }

}
