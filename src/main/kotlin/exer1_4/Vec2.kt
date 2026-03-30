package org.example.exer1_4

import kotlin.math.sqrt

//O uso da expressão "Comparable<Vec2>" foi necessário, pois assim estamos a dizer ao kotlin que uma instância desta classe poode ser comparavél com outra instância desta mesma classe
//Sem o uso deste contrato, não seria possível comparar instancias desta classe
data class Vec2(val x: Double, val y: Double): Comparable<Vec2>{

    //O operator Overloading permite "ensinar" ao kotlin as regras de como ele se deve comportar quando encontrar um determinado operador entre instâncias criadas de classes

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

    //o uso do override cumpre a promessa que foi "assinada" ao usar o contrato Comparable
    override operator fun compareTo(valor: Vec2): Int {
        //distancia euclideana do vetor atual
        val tamanhoVetor = sqrt((this.x * this.x) + (this.y * this.y))

        //distancia euclideana do vetor comparação
        val tamanhoVetorComp = sqrt((valor.x * valor.x) + (valor.y * valor.y))

        //retorna 0 se os dois vetores forem iguais
        //retorna um numero positivo se o primeiro for maior que o segundo
        //retorna um numero negativo se o primeiro for menor que o segundo
        return tamanhoVetor.compareTo(tamanhoVetorComp)
    }

    //função responsável por calcular a magnitude
    fun magnitude(): Double{
        //distancia euclideana
        return sqrt((this.x * this.x) + (this.y * this.y))
    }

    //função responsável por calcular o produto escalar
    fun dot(other: Vec2): Double{
        return ((this.x * other.x) + (this.y * other.y))
    }

    //função que retorna um novo vetor unitário na mesma direção
    fun normalized(): Vec2{

        val tamanho = magnitude() //calcula o tamanho do vetor através da função magnitude criada nesta mesma classe

        //Se o vetor tiver tamanho zero lança uma excessão
        if (tamanho ==0.0){
            throw IllegalStateException("Não é possível normalizar um vetor com tamanho 0.")
        }

        return Vec2(this.x / tamanho, this.y / tamanho) // retorna o novo vetor
    }

    //overload get operator
    //se for pedido a posição 0 do vetor, retorna o valor de x
    //se for pedido a posiçao 1 do vetor, retorna o valor de y
    operator fun get(i: Int): Double{
        return when(i){
            0 -> this.x
            1 -> this.y
            else -> throw IndexOutOfBoundsException("Índice inválido.")
        }
    }

}
