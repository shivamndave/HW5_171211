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

    private int _tomatoX;
    private int _tomatoY;

    private float _xLoc;
    private float _yLoc;

//  Physics variables
    private double _alpha = .3;
    private double  _beta = 7;
    private double _gamma = -0.5;


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

        _tomatoX = mMeasuredWidth / 2;
        _tomatoY = mMeasuredHeight / 8;

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

        _tomatoX += (getTomatoX()*_beta) - (_alpha*getTomatoX()*_beta);

        if (_tomatoX < tomatoRadius) {
            _tomatoX = tomatoRadius;
        } else if (_tomatoX > mMeasuredWidth - tomatoRadius) {
            _tomatoX = mMeasuredWidth - tomatoRadius;
        }

        _tomatoY += (getTomatoY()*_beta) - (_alpha*getTomatoY()*_beta);

        if (_tomatoY < tomatoRadius) {
            _tomatoY = tomatoRadius;
        } else if (_tomatoY > mMeasuredHeight - tomatoRadius) {
            _tomatoY = mMeasuredHeight - tomatoRadius;
        }

        int line1StartX = -100;
        int line1Y = mMeasuredHeight / 4;
        int line1EndX = mMeasuredWidth - (mMeasuredWidth / 3);

        int line2StartX = mMeasuredWidth;
        int line2Y = mMeasuredHeight - (mMeasuredHeight / 4);
        int line2EndX = mMeasuredWidth / 3;

        // If the first barrier is hit on a flat side
        if ((0 <= _tomatoX && _tomatoX <= line1EndX) && Math.abs(_tomatoY - line1Y) < tomatoRadius) {
            int tomatoDiff1 = _tomatoY - line1Y;
            if (tomatoDiff1 < 0) {
                _tomatoY += (Math.abs(tomatoDiff1) - tomatoRadius);
            } else if (tomatoDiff1 > 0) {
                _tomatoY -= (Math.abs(tomatoDiff1) - tomatoRadius);
            }
        }

        double corner1Dist = Math.sqrt(Math.pow((line1EndX - _tomatoX), 2) + Math.pow((line1Y - _tomatoY), 2));

        if (line1EndX < _tomatoX && corner1Dist < tomatoRadius){
            if(_tomatoY > line1Y){
                _tomatoX += tomatoRadius;
                _tomatoY += tomatoRadius;
            } else if(_tomatoY < line1Y){
                _tomatoX += tomatoRadius;
                _tomatoY -= tomatoRadius;
            }
        }

        // If the second barrier is hit on a flat side
        if ((line2EndX <= _tomatoX && _tomatoX <= line2StartX) && Math.abs(_tomatoY - line2Y) < tomatoRadius) {
            int tomatoDiff2 = _tomatoY - line2Y;
            if (tomatoDiff2 < 0) {
                _tomatoY += (Math.abs(tomatoDiff2) - tomatoRadius);
            } else if (tomatoDiff2 > 0) {
                _tomatoY -= (Math.abs(tomatoDiff2) - tomatoRadius);
            }
        }

        double corner2Dist = Math.sqrt(Math.pow((line2EndX - _tomatoX), 2) + Math.pow((line2Y - _tomatoY), 2));

        if (line2EndX > _tomatoX && corner2Dist < tomatoRadius){
            if(_tomatoY > line2Y){
                _tomatoX += tomatoRadius;
                _tomatoY += tomatoRadius;
            } else if(_tomatoY < line1Y){
                _tomatoX += tomatoRadius;
                _tomatoY -= tomatoRadius;
            }
        }



        double holeTomatoDistance = Math.sqrt(Math.pow((_tomatoX - drainX), 2) + Math.pow((_tomatoY - drainY), 2));

        if (holeTomatoDistance < Math.abs(tomatoRadius - drainRadius)){
            _tomatoX = mMeasuredWidth / 2;
            _tomatoY = mMeasuredHeight / 8;
        }

        canvas.drawCircle(_tomatoX, _tomatoY, tomatoRadius, tomatoPaint);
        canvas.drawCircle(drainX, drainY, drainRadius, drainPaint);
        canvas.drawLine(line1StartX, line1Y, line1EndX, line1Y, line1Paint);
        canvas.drawLine(line2StartX, line2Y, line2EndX, line2Y, line2Paint);

        canvas.save();
        canvas.restore();
    }

}
