package com.doctor.blue.smashthestone.model

class Wall {
    var rows:Array<Row>
    constructor(numberOfRows:Int){
        rows= Array(numberOfRows) {Row()}
        for (x in 0 until numberOfRows){
            rows[x]=Row(x)
        }
    }
}