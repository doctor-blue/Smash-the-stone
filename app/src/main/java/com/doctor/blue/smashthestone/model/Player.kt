package com.doctor.blue.smashthestone.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.ETC1Util
import android.opengl.GLUtils
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Player() {
    private  var vertexBuffer:FloatBuffer
    private lateinit var textureBuffer: FloatBuffer
    private  var indexBuffer:ByteBuffer
    private val textures = IntArray(1)
    private var vertices= floatArrayOf(
        0.0f,0.0f,0.0f,
        1.5f,0.0f,0.0f,
        1.5f,0.25f,0.0f,
        0.0f,0.25f,0.0f
    )
    private var textture= floatArrayOf(
        0.0f,0.0f,
        1.0f,0.0f,
        1.0f,1.0f,
        0.0f,1.0f
    )
    private var indices= byteArrayOf(
        0,1,2,
        0,2,3
    )

    init {
        var byteBuf=ByteBuffer.allocateDirect(vertices.size*4)
        byteBuf.order(ByteOrder.nativeOrder())
        vertexBuffer=byteBuf.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        byteBuf= ByteBuffer.allocateDirect(textture.size*4)
        byteBuf.order(ByteOrder.nativeOrder())
        textureBuffer=byteBuf.asFloatBuffer()
        textureBuffer.put(textture)
        textureBuffer.position(0)

        indexBuffer= ByteBuffer.allocateDirect(indices.size)
        indexBuffer.put(indices)
        indexBuffer.position(0)
    }
    fun draw(gl:GL10){
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textture[0].toInt())

        gl.glFrontFace(GL10.GL_CCW)
        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glCullFace(GL10.GL_BACK)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer)
        gl.glTexCoordPointer(2,GL10.GL_FLOAT,0,textureBuffer)
        gl.glDrawElements(GL10.GL_TRIANGLES,indices.size,GL10.GL_UNSIGNED_BYTE,indexBuffer)

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }

    fun loadTexture(gl:GL10,texture:Int,context: Context){
        var imagestream:InputStream?=context.resources.openRawResource(texture)
        var bitmap:Bitmap?=null
        try {
            bitmap= BitmapFactory.decodeStream(imagestream)
        }catch (e:Exception){

        }finally {
            try {
                imagestream?.close()
                imagestream=null
            }catch (e:IOException){

            }
        }
        gl.glGenTextures(1,textures,0)
        gl.glBindTexture(GL10.GL_TEXTURE_2D,textture[0].toInt())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST.toFloat())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR.toFloat())
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,bitmap,0)
        bitmap?.recycle()
    }
}