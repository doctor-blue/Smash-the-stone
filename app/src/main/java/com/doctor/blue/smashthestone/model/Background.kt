package com.doctor.blue.smashthestone.model

import javax.microedition.khronos.opengles.GL10
import android.opengl.GLUtils
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.nio.ByteBuffer.allocateDirect
import android.R.attr.order
import android.content.Context
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


class Background {
    private var vertexBuffer: FloatBuffer
    private var textureBuffer: FloatBuffer
    private var indexBuffer: ByteBuffer

    private val textures = IntArray(1)

    private val vertices =
        floatArrayOf(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f)

    private val texture = floatArrayOf(0.0f, 0.0f, 1.0f, 0f, 1f, 1.0f, 0f, 1f)

    private val indices = byteArrayOf(0, 1, 2, 0, 2, 3)

    constructor() {
        var byteBuf = ByteBuffer.allocateDirect(vertices.size * 4)
        byteBuf.order(ByteOrder.nativeOrder())
        vertexBuffer = byteBuf.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        byteBuf = ByteBuffer.allocateDirect(texture.size * 4)
        byteBuf.order(ByteOrder.nativeOrder())
        textureBuffer = byteBuf.asFloatBuffer()
        textureBuffer.put(texture)
        textureBuffer.position(0)

        indexBuffer = ByteBuffer.allocateDirect(indices.size)
        indexBuffer.put(indices)
        indexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0])

        gl.glFrontFace(GL10.GL_CCW)
        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glCullFace(GL10.GL_BACK)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY)

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer)
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer)

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.size, GL10.GL_UNSIGNED_BYTE, indexBuffer)

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }


    fun loadTexture(gl: GL10, texture: Int, context: Context) {
        var imagestream = context.resources.openRawResource(texture)
        var bitmap: Bitmap? = null
        try {

            bitmap = BitmapFactory.decodeStream(imagestream)

        } catch (e: Exception) {

        } finally {
            //Always clear and close
            try {
                imagestream.close()
            } catch (e: IOException) {
            }

        }

        gl.glGenTextures(1, textures, 0)
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0])

        gl.glTexParameterf(
            GL10.GL_TEXTURE_2D,
            GL10.GL_TEXTURE_MIN_FILTER,
            GL10.GL_NEAREST.toFloat()
        )
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR.toFloat())

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT.toFloat())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT.toFloat())

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0)

        bitmap!!.recycle()
    }
}