package org.example.exer1_2

//Nesta classe genérica, o K representa a key e o V o value.
// O uso do Any como upper bound garante que as chaves e os valores não podem ser nulos, o que é importante para a integridade da cache
class Cache<K: Any, V:Any> {
    private val cache : MutableMap<K, V> = mutableMapOf()

    // Método que insere ou reescreve um determinado valor associado a uma determinada chave na cache
    fun put(key: K, value: V) {
        cache[key] = value
    }

    fun  get(key: K): V? {
        //O método get retorna o valor associado a uma determinada chave, ou null se a chave não estiver presente na cache
        if (cache.containsKey(key)) {
            return cache[key]
        }else{
            return null
        }
    }

}