/*************************************************************************
\file   JumperQuad.kt
\author Leong Cheng Onn Darryl, 2000686
\date   Feb 24, 2023
\brief  This file contains the implementation of the quads used to draw
        gameobjects
 *************************************************************************/
package com.game.jumper.graphics

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Handles creation of quad objects used to draw gameobjects with
 */
open class JumperQuad {
    var gameContext: Context

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

    var texName: String = ""
    var texBuffer: FloatBuffer
    val texCoords: FloatArray = floatArrayOf(
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 1.0f
    )

    var posHandle: Int = 0
    var colHandle: Int = 0
    var vPMatrixHandle: Int = 0
    var texCoordsHandle: Int = 0
    var texUniformHandle: Int = 0
    var texDataHandle: Int = 0

    val vtxCount: Int = quadCoords.size / coordsPerVtx
    val vtxStride: Int = coordsPerVtx * 4

    /**
     * constructor to create a JumperQuad object
     */
    constructor(context: Context, texture: String = "") {

        gameContext = context
        texName = texture

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

        // repeat for texture buffer
        texBuffer =
        ByteBuffer.allocateDirect(texCoords.size * 4).run {
            order(ByteOrder.nativeOrder())
            asFloatBuffer().apply {
                put(texCoords)
                position(0)
            }
        }

        // handle shader
        val vtxShader: Int = JumperGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, JumperShaderCode.vtxShaderCode)
        val fragShader: Int = JumperGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, JumperShaderCode.fragShaderCode)

        shaderProgram = GLES20.glCreateProgram()
        GLES20.glAttachShader(shaderProgram, vtxShader)
        GLES20.glAttachShader(shaderProgram, fragShader)
        GLES20.glLinkProgram(shaderProgram)

        // check if texture exists and load if it does
        if (!texName.isEmpty())
            texDataHandle = loadTexture(texName)
    }

    /**
     * function to load and bind textures to the JumperQuad object
     */
    fun loadTexture(texture: String): Int {
        val textureHandle = IntArray(1)
        GLES20.glGenTextures(1, textureHandle, 0)

        if (textureHandle[0] != 0) {
            val options = BitmapFactory.Options()
            options.inScaled = false // No pre-scaling

            // Read in the resource
            val bitmap = BitmapFactory.decodeStream(gameContext.assets.open(texture))

            // Bind to the texture in OpenGL
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0])

            // Set filtering
            GLES20.glTexParameteri(
                GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST
            )
            GLES20.glTexParameteri(
                GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_NEAREST
            )

            // enable blending for texture alpha
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc (GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle()
        }
        if (textureHandle[0] == 0) {
            throw RuntimeException("Error loading texture.")
        }
        return textureHandle[0]
    }

    /**
     * function to draw non-textured JumperQuads
     */
    fun draw(mvpMatrix: FloatArray) {

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(shaderProgram)

        // get handle to vertex shader's vPosition member
        posHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition").also {

            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)

            // Prepare the triangle coordinate data
            GLES20.glVertexAttribPointer(
                it,
                coordsPerVtx,
                GLES20.GL_FLOAT,
                false,
                vtxStride,
                vtxBuffer
            )

            // get handle to shape's transformation matrix
            vPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix").also { matrixHandle ->

                // Pass the projection and view transformation to the shader
                GLES20.glUniformMatrix4fv(matrixHandle, 1, false, mvpMatrix, 0)
            }

            // get handle to fragment shader's vColor member
            colHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor").also { colorHandle ->

                // Set color for drawing the triangle
                GLES20.glUniform4fv(colorHandle, 1, quadColor, 0)
            }

            // Draw the triangle
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vtxCount)

            // Disable vertex array
            GLES20.glDisableVertexAttribArray(it)
        }
    }

    /**
     * function to non-textured JumperQuads
     */
    fun drawTextured(mvpMatrix: FloatArray) {

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(shaderProgram)

        // get handle to vertex shader's vPosition member
        posHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition").also {

            // Enable a handle to the triangle vertices
            GLES20.glEnableVertexAttribArray(it)

            // Prepare the triangle coordinate data
            GLES20.glVertexAttribPointer(
                it,
                coordsPerVtx,
                GLES20.GL_FLOAT,
                false,
                vtxStride,
                vtxBuffer
            )

            // get handle to shape's transformation matrix
            vPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix").also { matrixHandle ->

                // Pass the projection and view transformation to the shader
                GLES20.glUniformMatrix4fv(matrixHandle, 1, false, mvpMatrix, 0)
            }

            // get handle to fragment shader's vColor member
            colHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor").also { colorHandle ->

                // Set color for drawing the triangle
                GLES20.glUniform4fv(colorHandle, 1, quadColor, 0)
            }

            //Texture position handler
            texCoordsHandle = GLES20.glGetAttribLocation(shaderProgram, "a_TexCoord").also {

                // Enable a handle to the triangle vertices
                GLES20.glEnableVertexAttribArray(it)

                GLES20.glVertexAttribPointer(
                    it,
                    coordsPerVtx,
                    GLES20.GL_FLOAT,
                    false,
                    vtxStride,
                    texBuffer
                )
            }

            //Texture uniform handler
            texUniformHandle = GLES20.glGetUniformLocation(shaderProgram, "u_Texture")

            // Set the active texture unit to texture unit 0.
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

            // Bind the texture to this unit.
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texDataHandle);

            // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
            GLES20.glUniform1i(texUniformHandle, 0);

            // Draw the triangle
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vtxCount)

            // Disable vertex array
            GLES20.glDisableVertexAttribArray(texCoordsHandle)
            GLES20.glDisableVertexAttribArray(it)
        }
    }
}