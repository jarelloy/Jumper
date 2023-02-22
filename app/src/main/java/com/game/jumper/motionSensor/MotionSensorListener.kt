package com.game.jumper.motionSensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class MotionSensorListener(context: Context) : SensorEventListener {
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val rotationMatrix = FloatArray(9)
    private val orientation = FloatArray(3)

    private var lastTimestamp: Long = 0

    companion object {
        var currentRotation: Int = 0
        var currentPitch: Float = 0f
        var currentRoll: Float = 0f
        var currentYaw: Float = 0f
        private const val NS2S = 1.0f / 1000000000.0f
    }


    private val tag = "SensorReading"

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val alpha = 0.8f
                val gravity = FloatArray(3)
                val linearAcceleration = FloatArray(3)

                // Remove gravity from the accelerometer readings using a high-pass filter
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]
                linearAcceleration[0] = event.values[0] - gravity[0]
                linearAcceleration[1] = event.values[1] - gravity[1]
                linearAcceleration[2] = event.values[2] - gravity[2]

                // Calculate pitch and roll angles using the accelerometer readings
                currentPitch = atan2(
                    linearAcceleration[1],
                    sqrt(linearAcceleration[0].pow(2) + linearAcceleration[2].pow(2))
                )
                currentRoll = atan2(
                    linearAcceleration[0],
                    sqrt(linearAcceleration[1].pow(2) + linearAcceleration[2].pow(2))
                )
            }
            Sensor.TYPE_GYROSCOPE -> {
                if (lastTimestamp != 0L) {
                    val dt = (event.timestamp - lastTimestamp) * NS2S

                    // Calculate the change in rotation based on the gyroscope readings
                    val omegaMagnitude = sqrt(
                        event.values[0].pow(2) + event.values[1].pow(2) + event.values[2].pow(
                            2
                        )
                    )
                    if (omegaMagnitude > 0.1f) {
                        event.values.forEachIndexed { index, value ->
                            event.values[index] = value / omegaMagnitude
                        }
                        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                        SensorManager.getOrientation(rotationMatrix, orientation)

                        // Calculate yaw angle using the gyroscope and magnetometer readings
                        currentYaw = Math.toDegrees(orientation[0].toDouble()).toFloat()

                        // Calculate current rotation based on the pitch and roll angles
                        currentRotation = ((currentRoll.toDegrees() ) % 180).toInt()
                    }
                }
            }
        }
        lastTimestamp = event?.timestamp ?: lastTimestamp

        //Log.d (tag, "Current Rotation: $currentRotation, Roll: ${currentRoll.toDegrees()}, Pitch: ${currentPitch.toDegrees()}, Yaw: $currentYaw")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    fun start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    fun getCurrentRotation(): Int {
        return currentRotation
    }

    fun getCurrentPitch(): Float {
        return currentPitch.toDegrees()
    }

    fun getCurrentRoll(): Float {
        return currentRoll.toDegrees()
    }

    fun getCurrentYaw(): Float {
        return currentYaw
    }

    private fun Float.toDegrees(): Float {
        return Math.toDegrees(this.toDouble()).toFloat()
    }

}