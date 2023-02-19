package com.game.jumper.engine

import android.content.res.Resources
import android.opengl.Matrix
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

    fun draw() {
        if(quad != null)
        {
            val projectionMatrix = FloatArray(16)
            val viewMatrix = FloatArray(16)
            val matrixValues = FloatArray(16)
            Matrix.frustumM(projectionMatrix, 0, -10f, 10f, -1f, 1f, 3f, 7f)
            Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 7f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

            transform.getTrans().getValues(matrixValues)
            val MVmat = FloatArray(16)
            val MVPmat = FloatArray(16)
            Matrix.multiplyMM(MVmat, 0, projectionMatrix, 0, viewMatrix, 0)
            Matrix.multiplyMM(MVPmat, 0, MVmat, 0, matrixValues, 0)

            quad!!.drawTextured(matrixValues)
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
