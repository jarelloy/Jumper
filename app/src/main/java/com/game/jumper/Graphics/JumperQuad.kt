package com.game.jumper.Graphics

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

open class JumperQuad {
    var shaderProgram: Int = 0

    var vtxBuffer: FloatBuffer
    val coordsPerVtx: Int = 2
    val quadCoords: FloatArray = floatArrayOf(
        -0.5f, -0.5f, // bottom left
         0.5f, -0.5f, // bottom right
         0.5f,  0.5f, // top right
        -0.5f,  0.5f  // top left
    )
    val quadColor: FloatArray = floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f) // red

    lateinit var texBuffer: FloatBuffer
    val texCoords: FloatArray = floatArrayOf(
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f
    )
    var texture: IntArray = IntArray(1)

    constructor() {
        val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(
            quadCoords.size * 4 // 4 bytes per float
        )
        byteBuffer.order(ByteOrder.nativeOrder())

        // create a floating point buffer from the ByteBuffer
        vtxBuffer = byteBuffer.asFloatBuffer()
        // add coords to float buffer
        vtxBuffer.put(quadCoords)
        // set buffer to read first coord
        vtxBuffer.position(0)

        // handle shader
        val vtxShader: Int = JumperGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, JumperShaderCode.vtxShaderCode)
        val fragShader: Int = JumperGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, JumperShaderCode.fragShaderCode)

        shaderProgram = GLES20.glCreateProgram()
        GLES20.glAttachShader(shaderProgram, vtxShader)
        GLES20.glAttachShader(shaderProgram, fragShader)
        GLES20.glLinkProgram(shaderProgram)
    }

    var posHandle: Int = 0
    var colHandle: Int = 0
    var vPMatrixHandle: Int = 0

    val vtxCount: Int = quadCoords.size / coordsPerVtx
    val vtxStride: Int = coordsPerVtx * 4

    fun draw(mvpMatrix: FloatArray) {
        GLES20.glUseProgram(shaderProgram)

        GLES20.glEnableVertexAttribArray(posHandle)

        GLES20.glVertexAttribPointer(posHandle, coordsPerVtx, GLES20.GL_FLOAT, false, vtxStride, vtxBuffer)

        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix")

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)

        colHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor")

        GLES20.glUniform4fv(colHandle, 1, quadColor, 0)

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vtxCount)

        GLES20.glDisableVertexAttribArray(posHandle)
    }
}