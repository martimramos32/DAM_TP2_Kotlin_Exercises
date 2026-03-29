package org.example.exer1_2

import jdk.internal.foreign.abi.Binding

//Nesta classe genérica, o K representa a key e o V o value.
// O uso do Any como upper bound garante que as chaves e os valores não podem ser nulos, o que é importante para a integridade da cache
class Cache<K: Any, V:Any> {
    private val cache : MutableMap<K, V> = mutableMapOf()

    // Método que insere ou reescreve um determinado valor associado a uma determinada chave na cache
    fun put(key: K, value: V) {
        cache[key] = value
    }

    fun  get(key: K): V? { // o uso do "V?" é necessário pois como à partida não se sabe se a chave que o utilizador está a pedir está presente na cache tem de haver a capacidade de tratar de um possivel caso nulo daí o uso do if/else utilizado na função abaixo
        //O método get retorna o valor associado a uma determinada chave, ou null se a chave não estiver presente na cache
        if (cache.containsKey(key)) {
            return cache[key]
        }else{
            return null
        }
    }

    fun evict(key: K) {
        // Remove uma entrada da cache associada
        cache.remove(key)
    }

    fun size(): Int {
        // Retorna o número de entradas atualmente armazenadas na cache
        return cache.size
    }

    fun getOrPut(key: K, default: ()-> V): V {
        val valor = cache[key] // armazena o valor associado à chave fornecida na chamada do metodo

        if (valor != null) { // Se a chave nao for nula na cache, retorna o seu valor associado guardado na variavel denominada "valor"

            return valor
        }else {
            val valorDefault = default() //se a chave não existir, chamamos a função lambda para gerar um novo valor
            cache[key] = default()
            return valorDefault
        }
    }

    fun transform(key: K, action: (V) -> V): Boolean {
        val valor = cache[key]

        //Se a chave existir, aplicamos a função action ao seu valor associado e atualizamos a cache com esse mesmo valor transformado
        if (valor != null){
            val valorAtual = action(valor)
            cache[key] = valorAtual
            return true //retorna true para indicar que a transformação foi bem-sucedida
        }
        return false //retorna false se a chave não existir na cache
    }

    fun snapshot(): Map<K, V> {
        return cache.toMap() //retorna uma lista com a cache. esta lista nao pode ser alterada pois o metodo toMap permite esta caracteristica
    }

    // funcao CHALLENGE
    fun filterValues(predicate: (V)-> Boolean): Map<K, V> {
        return cache.filterValues(predicate)
    }
}