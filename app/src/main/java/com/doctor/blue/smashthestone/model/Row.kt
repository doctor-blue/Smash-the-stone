package com.doctor.blue.smashthestone.model

import java.util.*

class Row {
    lateinit var bricks:Array<Brick>
    var numberOfBricksRemaining:Int=0
    var rowIsScored:Boolean=false
    private val brickType:Random= Random()
    private var isRowOld=false
    private var numberOfBrick:Int=0


    constructor()

    constructor(rowNumber:Int){
        if (rowNumber%2>0){
            numberOfBrick=4
            numberOfBricksRemaining=4
            isRowOld=true
        }
        else{
            numberOfBrick=5
            numberOfBricksRemaining=5
            isRowOld=false
        }
        bricks=Array(numberOfBrick) { Brick() }

        for (x in 0 until numberOfBrick){
            bricks[x]= Brick((brickType.nextFloat()*7).toInt())
            if (isRowOld){
                bricks[x].posX=x-2f
                bricks[x].posY=(rowNumber*0.25f)+1

            }
            else{
                bricks[x].posX=x-2.5f
                bricks[x].posY=(rowNumber*0.25f)+1
            }
        }
    }
}