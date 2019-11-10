package com.doctor.blue.smashthestone.keys

import android.annotation.SuppressLint
import android.content.Context
import android.view.Display

object GameVars {
    lateinit var display:Display
    var GAME_THREAD_DELAY = 3000
    var MENU_BUTTON_ALPHA = 0
    var HAPTIC_BUTTON_FEEDBACK = true
    var GAME_THREAD_FPS_SLEEP = 1000 / 60
    var playerBankPosX = -0.73f
    var playerAction = 0
    var PLAYER_MOVE_LEFT_1 = 1
    var PLAYER_RELEASE = 3
    var PLAYER_MOVE_RIGHT_1 = 4
    var PLAYER_MOVE_SPEED = .2f
    var BRICK_BLUE = 1
    var BRICK_BROWN = 2
    val BRICK_DARK_GRAY = 3
    val BRICK_GREEN = 4
    val BRICK_LITE_GRAY = 5
    val BRICK_PURPLE = 6
    val BRICK_RED = 7
    val BALL_SPEED = 0.01f
    val BALL_MODE_NORMAL = 0
    val BALL_MODE_SPIKE = 1
    var ballTargetY = 0.01f
    var ballTargetX = -1.125f
    val NUMBER_OF_MODE_IMPACTS = 6
    @SuppressLint("StaticFieldLeak")
    lateinit var context:Context
}