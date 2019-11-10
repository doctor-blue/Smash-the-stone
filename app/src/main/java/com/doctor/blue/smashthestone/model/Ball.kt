package com.doctor.blue.smashthestone.model

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.*
import javax.microedition.khronos.opengles.GL10


class Ball {
    var posY = 0f
    var posX = 0f
    var posT = 0f

    var ballMode = 0
    private var randomPos: Random = Random()

    private var damage = 0
    private var vertexBuffer: FloatBuffer
    private var textureBuffer: FloatBuffer
    private var indexBuffer: ByteBuffer
    private var vertices: FloatArray = floatArrayOf(
        0.0f, 0.0f, 0.0f,
        0.25f, 0.0f, 0.0f,
        0.25f, 0.25f, 0.0f,
        0.0f, 0.25f, 0.0f
    )

    private var texture: FloatArray = floatArrayOf(
        0.0f, 0.0f,
        0.5f, 0.0f,
        0.50f, 0.50f,
        0.0f, 0.50f
    )
    private var indies: ByteArray = byteArrayOf(
        1, 2, 3,
        0, 2, 3
    )

    init {
        posY = (randomPos.nextFloat() + 1f) * (-1.75 - -1.6).toFloat()
        posX = randomPos.nextFloat() * 0.75f
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

        indexBuffer = ByteBuffer.allocateDirect(indies.size)
        indexBuffer.put(indies)
        indexBuffer.position(0)
    }

    fun draw(gl: GL10, spiteSheet: IntArray) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, spiteSheet[2])
        gl.glFrontFace(GL10.GL_CCW)
        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glCullFace(GL10.GL_BACK)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY)

        gl.glVertexPointer(
            3, GL10.GL_FLOAT, 0
            , vertexBuffer
        )
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer)

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }
}