package sd.cmps121.com.hw5_171211;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    private float _accelx;
    private float _accely;
    private Boolean _mSensorBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _mSensorBool = setSensorManager();
    }

    private Boolean setSensorManager() {
        return ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        // I hope I got the signs right.  If not, experiment with this.
                        _accelx = -event.values[0];
                        _accely = event.values[1];

                        DrainView drain = (DrainView) findViewById(R.id.drainView);

                        drain.setTomatoLoc(_accelx, _accely);

                        Log.e("acc", Float.toString(_accelx));
                        Log.e("acc", Float.toString(_accely));

                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    } //ignore
                },
                ((SensorManager) getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_GAME);
    }

}
