package org.example.exer1_2

//Nesta classe genérica, o K representa a key e o V o value.
// O uso do Any como upper bound garante que as chaves e os valores não podem ser nulos, o que é importante para a integridade da cache
class Cache<K: Any, V:Any> {
    private val cache : MutableMap<K, V> = mutableMapOf()

    // Método que insere ou reescreve um determinado valor associado a uma determinada chave no map da cache
    fun put(key: K, value: V) {
        cache[key] = value
    }
}