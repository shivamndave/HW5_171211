package sd.cmps121.com.hw5_171211;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shivamndave on 5/26/15.
 */
public class DrainView extends View {

    private Paint tomatoPaint;
    private Paint drainPaint;
    private Paint line1Paint;
    private Paint line2Paint;

    public DrainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDrainView();
    }

    protected void initDrainView() {
        setFocusable(true);

        Resources r = this.getResources();

        tomatoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tomatoPaint.setColor(r.getColor(R.color.tomato_color));
        tomatoPaint.setStrokeWidth(1);
        tomatoPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        drainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drainPaint.setColor(r.getColor(R.color.drain_color));
        drainPaint.setStrokeWidth(1);
        drainPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        line1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        line1Paint.setColor(r.getColor(R.color.line_color));
        line1Paint.setStrokeWidth(1);
        line1Paint.setStyle(Paint.Style.FILL_AND_STROKE);

        line2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        line2Paint.setColor(r.getColor(R.color.line_color));
        line2Paint.setStrokeWidth(1);
        line2Paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mMeasuredWidth = getMeasuredWidth();
        int mMeasuredHeight = getMeasuredHeight();

        int tomatoStartX = mMeasuredWidth / 2;
        int tomatoStartY = mMeasuredHeight / 8 ;

        int drainX = mMeasuredWidth / 2;
        int drainY = mMeasuredHeight - (mMeasuredHeight / 8) ;

        int line1StartX = -100;
        int line1StartY = mMeasuredHeight / 4 ;
        int line1EndX = mMeasuredWidth - (mMeasuredWidth / 3);
        int line1EndY = mMeasuredHeight / 4 ;

        int line2StartX = mMeasuredWidth;
        int line2StartY = mMeasuredHeight - (mMeasuredHeight / 4);
        int line2EndX = mMeasuredWidth / 3;
        int line2EndY = mMeasuredHeight - (mMeasuredHeight / 4) ;

        int tomatoRadius = 50;
        int drainRadius = 75;

        canvas.drawCircle(tomatoStartX, tomatoStartY, tomatoRadius, tomatoPaint);
        canvas.drawCircle(drainX, drainY, drainRadius, drainPaint);
        canvas.drawLine(line1StartX, line1StartY, line1EndX, line1EndY, line1Paint);
        canvas.drawLine(line2StartX, line2StartY, line2EndX, line2EndY, line2Paint);

        canvas.save();
        canvas.restore();
    }

}
