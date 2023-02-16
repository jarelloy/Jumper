package com.game.jumper.motionSensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.abs

class MotionSensorListener(context: Context) : SensorEventListener {

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private var previousReading: Float = 0f
    private var currentRotation = 0

    private val tag = "SensorReading"

    fun start() {
        gyroscopeSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            // Get the values of the gyroscope sensor readings
            val currentReading = event.values[2]

            // Calculate the difference in the gyroscope sensor readings from the previous reading
            val delta = currentReading - previousReading

            // Check if the difference in the gyroscope sensor readings is large enough to be considered a rotation
            if (abs(delta) > 0.5f) {
                // Determine the direction of the rotation based on the sign of the difference in the gyroscope sensor readings
                val direction = if (delta > 0) "Right" else "Left"

                currentRotation = (currentRotation + delta.toInt() * 3) % 360
                // Do something with the rotation direction (e.g., update the UI)
                // ...

                // Update the previous gyroscope sensor reading
                previousReading = currentReading

                Log.d(tag, "Direction: $direction, Current Reading: $currentReading")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun getCurrentRotation(): Int {
        return currentRotation
    }
}