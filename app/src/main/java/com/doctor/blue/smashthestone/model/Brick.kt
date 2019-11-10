package com.doctor.blue.smashthestone.model

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class Brick {
    var posY = 0f
    var posX = 0f
    var posT = 0f
    var isDestroyed = false;
    var brickType = 0
    lateinit var vertexBuffer: FloatBuffer
    lateinit var textureBuffer: FloatBuffer
    lateinit var indexBuffer: ByteBuffer

    private var vertices = floatArrayOf(
        0.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.25f, 0.0f,
        0.0f, 0.25f, 0.0f
    )
    private var texture = floatArrayOf(
        0.0f, 0.0f,
        0.25f, 0.0f,
        0.25f, 0.25f,
        0.0f, 0.25f
    )
    private var indices = byteArrayOf(
        0, 1, 2,
        0, 2, 3
    )

    constructor()
    constructor(type: Int) {
        brickType = type
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

    fun draw(gl: GL10, spriteSheet: IntArray) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[0])
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
}