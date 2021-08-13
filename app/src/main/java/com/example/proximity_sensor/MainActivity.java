package com.example.proximity_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView img;
    SensorManager mSensorManager;
    Sensor mProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.image);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (mProximity == null) {
            finish(); // Close App
        }

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // Do something here if sensor accuracy changes.
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.values[0] < mProximity.getMaximumRange())
        {
            //Detected Something nearby
            img.getLayoutParams().height = 230;
            img.getLayoutParams().width = 250;
        }
        else {
            img.getLayoutParams().height = 180;
            img.getLayoutParams().width = 200;
        }
    }
}

