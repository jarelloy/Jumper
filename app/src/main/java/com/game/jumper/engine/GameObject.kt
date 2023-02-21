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

class GameObject {
    var components = ArrayList<Component>()
    var scripts = ArrayList<Script>()
    var isActive: Boolean = true
    var name: String = "GameObject"
    var transform = Transform()
    var quad : JumperQuad? = null

    fun start() {
        scripts.forEach{script -> script.start() }
    }

    fun update() {
        if(!isActive)
            return

        components.forEach{component -> component.update() }
        scripts.forEach { script -> script.update() }
    }

    fun setNewQuad(newQuad: JumperQuad)
    {
        quad = newQuad
    }

    fun pausedUpdate()
    {
        if(!isActive) return

        components.forEach{component -> component.pausedUpdate() }
        scripts.forEach { script -> script.pausedUpdate() }
    }

    fun transpose(m : FloatArray) : FloatArray{
        var result = FloatArray(16)
        for(i in 0..2) {
            for(j in 0..2) {
                result[j*3+i] = m[i*3+j]
            }
        }
        return result
    }

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

    fun addComponent(component: Component) {
        component.gameObject = this
        components.add(component)
    }

    fun destroyComponent(component: Component){
        components.remove(component)
    }

    inline fun <reified T> getComponentForced() : T{
        components.forEach{component ->
            if (component is T)
                return component
        }

        throw Resources.NotFoundException("Failed to find component of type ${typeOf<T>()}")
    }

    inline fun <reified T> getComponent() : T?{
        components.forEach{component ->
            if (component is T)
                return component
        }
        return null
    }
    inline fun <reified T> hasComponent() : Boolean{
        components.forEach{component ->
            if (component is T)
                return true
        }
        return false
    }
    inline fun <reified T> getScript() : T?{
        scripts.forEach{script ->
            if (script is T)
                return script
        }
        return null
    }

    fun destroy() {
        isActive = false
        components.clear()
        scripts.clear()
    }

    fun addScript(script: Script) {
        script.gameObject = this
        scripts.add(script)
    }

    inline fun <reified T : Script> addScript() : T{
        return addScript(T::class)
    }
    fun <T : Script> addScript(klass: KClass<T>) : T {
        val instance = klass.constructors.first { it.parameters.isEmpty() }.call()
        instance.gameObject = this

        scripts.add(instance)
        return instance
    }
}
