package com.doctor.blue.smashthestone.views

import android.opengl.GLSurfaceView
import com.doctor.blue.smashthestone.R
import com.doctor.blue.smashthestone.keys.GameVars
import com.doctor.blue.smashthestone.model.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class GameRenderer : GLSurfaceView.Renderer {
    private var loopStart: Long = 0
    private var loopEnd: Long = 0
    private var loopRuntime: Long = 0
    private var bgScoll1:Float=0f


    private var player1: Player = Player()
    private var ball: Ball = Ball()
    private lateinit var texturesLoader: Textures
    private var spriteSheets = IntArray(3)
    private var numberOfRows = 4
    private lateinit var wall: Wall
    private var background = Background()
    override fun onDrawFrame(gl: GL10?) {
        loopStart = System.currentTimeMillis()
        try {
            if (loopRuntime < GameVars.GAME_THREAD_FPS_SLEEP) {
                Thread.sleep(GameVars.GAME_THREAD_FPS_SLEEP - loopRuntime)
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        drawBackground1(gl!!)
        movePlayer1(gl)
        drawBricks(gl)
        moveBall(gl)
        detectCollisions()
        loopEnd = System.currentTimeMillis()
        loopRuntime = (loopEnd - loopStart)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.glViewport(0, 0, width, height)
        gl?.glMatrixMode(GL10.GL_PROJECTION)
        gl?.glLoadIdentity()
        gl?.glOrthof(0f, 1f, 0f, 1f, -1f, 1f)



    }

    override fun onSurfaceCreated(gl: GL10?, arg1: EGLConfig?) {
        initializeBricks()
        gl?.let {
            texturesLoader = Textures(it)
            spriteSheets =
                texturesLoader.loadTexture(it, R.drawable.bricksheet, GameVars.context, 1)
        }
        gl?.glEnable(GL10.GL_TEXTURE_2D)
        gl?.glClearDepthf(1.0f)
        gl?.glEnable(GL10.GL_DEPTH_TEST)
        gl?.glDepthFunc(GL10.GL_LEQUAL)


        gl?.let {
            background.loadTexture(it, R.drawable.bg_game_1, GameVars.context)
            player1.loadTexture(it, R.drawable.goldbrick, GameVars.context)
        }

    }

    private fun initializeBricks() {
        wall = Wall(numberOfRows)

    }
    private fun drawBackground1(gl:GL10){
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
        gl.glPushMatrix()
        gl.glScalef(1f,1f,1f)

        background.draw(gl)
        gl.glPopMatrix()
        gl.glLoadIdentity()
    }

    private fun drawBricks(gl: GL10) {
        for (x in wall.rows.indices) {
            for (y in wall.rows[x].bricks.indices) {
                if (!wall.rows[x].bricks[y].isDestroyed) {

                    when (wall.rows[x].bricks[y].brickType) {
                        GameVars.BRICK_BLUE -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.50f, 0.25f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_BROWN -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.0f, 0.50f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_DARK_GRAY -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.25f, 0.25f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_GREEN -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.0f, 0.25f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_LITE_GRAY -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.25f, 0.0f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_PURPLE -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.50f, 0.0f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        GameVars.BRICK_RED -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.0f, 0.0f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                        else -> {
                            gl.glMatrixMode(GL10.GL_MODELVIEW)
                            gl.glLoadIdentity()
                            gl.glPushMatrix()
                            gl.glScalef(.25f, .25f, 1f)
                            gl.glTranslatef(
                                wall.rows[x].bricks[y].posX,
                                wall.rows[x].bricks[y].posY,
                                0f
                            )

                            gl.glMatrixMode(GL10.GL_TEXTURE)
                            gl.glLoadIdentity()
                            gl.glTranslatef(0.0f, 0.0f, 0.0f)


                            wall.rows[x].bricks[y].draw(gl, spriteSheets)
                            gl.glPopMatrix()
                            gl.glLoadIdentity()
                        }
                    }

                }
            }
        }

    }

    private fun moveBall(gl: GL10) {

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
        gl.glPushMatrix()
        gl.glScalef(.25f, .25f, 1f)

        ball.posX += ((GameVars.ballTargetX - ball.posX) / (ball.posY / GameVars.ballTargetY)) as Float

        ball.posY -= GameVars.ballTargetY * 3



        gl.glTranslatef(ball.posX, ball.posY, 0f)
        gl.glMatrixMode(GL10.GL_TEXTURE)
        gl.glLoadIdentity()
        gl.glTranslatef(0.0f, 0.0f, 0.0f)

        ball.draw(gl, spriteSheets)
        gl.glPopMatrix()
        gl.glLoadIdentity()

    }

    private fun movePlayer1(gl: GL10) {

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
        gl.glPushMatrix()
        gl.glScalef(.25f, .25f, 1f)

        if (GameVars.playerAction == GameVars.PLAYER_MOVE_LEFT_1 && GameVars.playerBankPosX > 0) {
            GameVars.playerBankPosX = GameVars.playerBankPosX - GameVars.PLAYER_MOVE_SPEED

        } else if (GameVars.playerAction == GameVars.PLAYER_MOVE_RIGHT_1 && GameVars.playerBankPosX < 2.5) {
            GameVars.playerBankPosX = GameVars.playerBankPosX + GameVars.PLAYER_MOVE_SPEED
        }

        gl.glTranslatef(GameVars.playerBankPosX, .5f, 0f)
        gl.glMatrixMode(GL10.GL_TEXTURE)
        gl.glLoadIdentity()
        gl.glTranslatef(0.0f, 0.0f, 0.0f)

        player1.draw(gl)
        gl.glPopMatrix()
        gl.glLoadIdentity()

    }

    private fun detectCollisions() {
        if (ball.posY <= 0) {
            //gameover
        }

        for (x in wall.rows.indices) {
            for (y in wall.rows[x].bricks.indices) {
                if (!wall.rows[x].bricks[y].isDestroyed) {
                    if (ball.posY > wall.rows[x].bricks[y].posY - .25f
                        && ball.posY < wall.rows[x].bricks[y].posY
                        && ball.posX + .25f > wall.rows[x].bricks[y].posX
                        && ball.posX < wall.rows[x].bricks[y].posX + 1.50f
                    ) {
                        wall.rows[x].bricks[y].isDestroyed = true

                        GameVars.ballTargetY = GameVars.ballTargetY * -1f
                        if (GameVars.ballTargetX === -2f) {
                            GameVars.ballTargetX = 5f
                        } else {
                            GameVars.ballTargetX = -2f
                        }

                    }


                }

            }
        }

        if (ball.posY - .25f <= .5f
            && ball.posX + .25f > GameVars.playerBankPosX
            && ball.posX < GameVars.playerBankPosX + 1.50f
        ) {
            GameVars.ballTargetY = GameVars.ballTargetY * -1f
            if (GameVars.ballTargetX === -2f) {
                GameVars.ballTargetX = 5f
            } else {
                GameVars.ballTargetX = -2f
            }
        }
        if (ball.posX < 0 || ball.posX + .25f > 3.75f) {
            GameVars.ballTargetX = GameVars.ballTargetX * -1f

        }

    }
}