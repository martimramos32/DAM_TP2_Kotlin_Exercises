package org.example.exer1_2

fun main() {
    println("--- Word frequency cache ---")
    val wordCache = Cache<String, Int>() // instância criada da classe Cache que recebe palavras e conta o numero de vezes que esta palavra aparece

    // Adicionamos 3 palavras iniciais em que cada uma aparece uma vez na contagem
    wordCache.put("kotlin", 1)
    wordCache.put("scala", 1)
    wordCache.put("haskell", 1)

    println("Size: ${wordCache.size()}") // número atual de palavras na cache
    println("Frequency of \"kotlin\": ${wordCache.get("kotlin")}") // numero de vezes que a palavra "kotlin" aparece

    //A palavra "kotlin" já existe, logo devolve o valor atual que é 1, ou seja, não chega a entrar na função lambda "default" que geraria o valor 0 se a palavra nao existisse na cache
    // A palavra "java" não existe, logo vai guardar o valor zero pela funçao "default"
    println("getOrPut \"kotlin\": ${wordCache.getOrPut("kotlin") { 0 }}")
    println("getOrPut \"java\": ${wordCache.getOrPut("java") { 0 }}")

    println("Size after getOrPut: ${wordCache.size()}") // retorna o tamanho da cache após sofrer ações por parte da função "getOrPut"

    //A palavra "kotlin" existe, logo soma 1 e devolve true. "cobol" não existe, logo devolve false
    //O 'it' chama o valor atual que vai ser transformado
    println("Transform \"kotlin\" (+1): ${wordCache.transform("kotlin") { it + 1 }}")
    println("Transform \"cobol\" (+1): ${wordCache.transform("cobol") { it + 1 }}")

    //Uso da função snapshot para imprimir todo o conteudo atual da cache
    println("Snapshot: ${wordCache.snapshot()}")

    println("\n--- Id registry cache ---")
    val idCache = Cache<Int, String>() // instancia de uma classe Cache que recebe um id (inteiro) e guarda através de um nome (String)

    idCache.put(1, "Alice") //id 1 para a Alice
    idCache.put(2, "Bob") //id 2 para o Bob

    //retorna o valor da chave passada
    println("Id 1 -> ${idCache.get(1)}") //retorna Alice
    println("Id 2 -> ${idCache.get(2)}") //retorna Bob

    //Testar a remoção de conteúdos de dentro da cache funcao "evict"
    idCache.evict(1)
    println("After evict id 1, size: ${idCache.size()}")
    println("Id 1 after evict -> ${idCache.get(1)}")
}