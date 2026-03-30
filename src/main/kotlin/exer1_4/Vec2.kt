package org.example.exer1_4

data class Vec2(val x: Double, val y: Double){

    //O operator Overloading permite "ensinar" ao kotlin as regras de como ele se deve comportar quando encontrar um determinado operador entre instancias criadas de classes

    //operador "+"
    operator fun plus(valor: Vec2): Vec2{
        return Vec2(this.x + valor.x, this.y + valor.y)
    }

    //operador "-"
    operator fun minus(valor: Vec2): Vec2{
        return Vec2(this.x - valor.x, this.y - valor.y)
    }

    //operador "*"
    operator fun times(valor: Double): Vec2{
        return Vec2(this.x * valor, this.y * valor)
    }

    //operador "-" sozinho, ou seja, inverte o sinal do proprio vetor
    operator fun unaryMinus(): Vec2{
        return Vec2(-this.x, -this.y)
    }


}
