/*************************************************************************
\file   GameObject.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of the game object class
 *************************************************************************/
package com.game.jumper.engine

import android.content.res.Resources
import android.opengl.Matrix
import android.opengl.Matrix.*
import com.game.jumper.engine.objects.Component
import com.game.jumper.engine.objects.Script
import com.game.jumper.graphics.JumperQuad
import com.game.jumper.math.Transform
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

/**
 *  GameObject class
 *
 *  This class represents a gameobject that will be contained in a scene.
 *  [quad] contains the render object. [transform] represents the scale,
 *  rotation, and position of the object.
 */
class GameObject {
    var components = ArrayList<Component>()
    var scripts = ArrayList<Script>()
    var isActive: Boolean = true
    var name: String = "GameObject"
    var transform = Transform()
    var quad : JumperQuad? = null

    /**
     * Calls the start function of every script in [scripts]
     */
    fun start() {
        scripts.forEach{script -> script.start() }
    }

    /**
     * If the object is active, update every components in [components] and scripts in [scripts]
     */
    fun update() {
        if(!isActive)
            return

        components.forEach{component -> component.update() }
        scripts.forEach { script -> script.update() }
    }

    /**
     * Set a new rendering component to the object
     */
    fun setNewQuad(newQuad: JumperQuad)
    {
        quad = newQuad
    }

    /**
     * Called when the scene is paused
     */
    fun pausedUpdate()
    {
        if(!isActive) return

        components.forEach{component -> component.pausedUpdate() }
        scripts.forEach { script -> script.pausedUpdate() }
    }

    /**
     * Transpose the matrix in [m]
     */
    private fun transpose(m : FloatArray) : FloatArray{
        var result = FloatArray(16)
        for(i in 0..2) {
            for(j in 0..2) {
                result[j*3+i] = m[i*3+j]
            }
        }
        return result
    }

    /**
     * Draw the game object takin in [vpMatrix] as the renderer's view/projection matrix
     *
     * matrixValues is first transposed as the matrix from android.graphics.Matrix is in row-major
     * whereas the matrix in android.opengl.Matrix is in column major
     */
    fun draw(vpMatrix: FloatArray) {
        if(quad != null)
        {
            var matrixValues = FloatArray(16)
            val matrix4x4 = FloatArray(16)

            transform.getTrans().getValues(matrixValues)
            matrixValues = transpose(matrixValues)
            // Convert the matrix from transform from 3x3 to 4x4
            // Assuming column major for both
            matrix4x4[0] = matrixValues[0];
            matrix4x4[1] = matrixValues[1];
            matrix4x4[2] = 0.0f;
            matrix4x4[3] = matrixValues[2];

            matrix4x4[4] = matrixValues[3];
            matrix4x4[5] = matrixValues[4];
            matrix4x4[6] = 0.0f;
            matrix4x4[7] = matrixValues[5];

            matrix4x4[8] = 0.0f;
            matrix4x4[9] = 0.0f;
            matrix4x4[10] = 1.0f; // z position
            matrix4x4[11] = 0.0f;

            matrix4x4[12] = matrixValues[6];
            matrix4x4[13] = matrixValues[7];
            matrix4x4[14] = 0.0f;
            matrix4x4[15] = matrixValues[8];

            val MVPmat = FloatArray(16)
            multiplyMM(MVPmat, 0, vpMatrix, 0, matrix4x4, 0)

            quad!!.drawTextured(MVPmat)
        }
    }

    /**
     * Adds a component to the game object
     */
    fun addComponent(component: Component) {
        component.gameObject = this
        components.add(component)
    }

    /**
     * Destroy a component on the game object
     */
    fun destroyComponent(component: Component){
        components.remove(component)
    }

    /**
     * Get component without null checking
     */
    inline fun <reified T> getComponentForced() : T{
        components.forEach{component ->
            if (component is T)
                return component
        }

        throw Resources.NotFoundException("Failed to find component of type ${typeOf<T>()}")
    }

    /**
     * Get component of the object based on type [T]. Returns null if not found.
     */
    inline fun <reified T> getComponent() : T?{
        components.forEach{component ->
            if (component is T)
                return component
        }
        return null
    }

    /**
     * Check if the object has a component of type [T]
     */
    inline fun <reified T> hasComponent() : Boolean{
        components.forEach{component ->
            if (component is T)
                return true
        }
        return false
    }

    /**
     * Get script of the object based on type [T]. Returns null if not found.
     */
    inline fun <reified T> getScript() : T?{
        scripts.forEach{script ->
            if (script is T)
                return script
        }
        return null
    }

    /**
     * Destroy the game object
     */
    fun destroy() {
        isActive = false
        components.clear()
        scripts.clear()
    }

    /**
     * Add a script to the game object
     */
    fun addScript(script: Script) {
        script.gameObject = this
        scripts.add(script)
    }

    /**
     * Add a script of type [T]
     */
    inline fun <reified T : Script> addScript() : T{
        return addScript(T::class)
    }

    /**
     * Reflection implementation of adding script of type [T]
     */
    fun <T : Script> addScript(klass: KClass<T>) : T {
        val instance = klass.constructors.first { it.parameters.isEmpty() }.call()
        instance.gameObject = this

        scripts.add(instance)
        return instance
    }
}
