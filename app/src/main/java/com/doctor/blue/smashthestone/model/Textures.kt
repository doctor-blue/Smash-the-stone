package com.doctor.blue.smashthestone.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLUtils
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import javax.microedition.khronos.opengles.GL10

class Textures{
    private var textures= IntArray(3)
    constructor(gl:GL10){
        gl.glGenTextures(3,textures,0)
    }
    fun loadTexture(gl:GL10,texture:Int,context: Context,
                    textureNumber: Int):IntArray{
        val imageStream:InputStream=context.resources.openRawResource(texture)
        var bitmap:Bitmap?=null
        try {
            bitmap=BitmapFactory.decodeStream(imageStream)
        }catch (e:Exception){

        }
        finally {
            try {
                imageStream.close()
            }catch (e:IOException){

            }
        }
        gl.glBindTexture(GL10.GL_TEXTURE_2D,textures[textureNumber-1])
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER
        ,GL10.GL_NEAREST.toFloat())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER
        ,GL10.GL_LINEAR.toFloat())

        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE.toFloat())
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE.toFloat())
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D,0,bitmap,0)
        bitmap?.recycle()
        return textures
    }
}