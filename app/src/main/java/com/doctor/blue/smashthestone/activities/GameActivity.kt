package com.doctor.blue.smashthestone.activities

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.doctor.blue.smashthestone.keys.GameVars
import com.doctor.blue.smashthestone.views.GameView

class GameActivity: AppCompatActivity() {
    var gameEngine=GameVars
    private lateinit var gameView:GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GameVars.context=this
        gameView=GameView(this)
        setContentView(gameView)
    }

    override fun onResume() {
        super.onResume()
        gameView.onResume()
    }

    override fun onPause() {
        super.onPause()
        gameView.onPause()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x=event?.x
        val y=event?.y
        val height=GameVars.display.height/4
        val playableArea=GameVars.display.height-height
        if(y!! > playableArea){
            when(event?.action){
                MotionEvent.ACTION_DOWN->{
                    if (x!! < GameVars.display.width/2){
                        GameVars.playerAction=GameVars.PLAYER_MOVE_LEFT_1
                    }
                    else{
                        GameVars.playerAction=GameVars.PLAYER_MOVE_RIGHT_1
                    }
                }
                MotionEvent.ACTION_UP->{
                    GameVars.playerAction=GameVars.PLAYER_RELEASE
                }
            }
        }
        return false
    }

}