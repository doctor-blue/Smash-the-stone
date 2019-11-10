package com.doctor.blue.smashthestone.views

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class GameView(context: Context): GLSurfaceView(context) {
    private var gameRenderer:GameRenderer= GameRenderer()
    init {
        this.setRenderer(gameRenderer)
    }
}