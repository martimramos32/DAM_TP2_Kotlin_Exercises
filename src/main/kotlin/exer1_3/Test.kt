package org.example.exer1_3

fun main(){
    // dados fornecidos pelo docente no enunciado do trabalho em questão
    val logs = listOf(
        " INFO: server started ",
        " ERROR: disk full ",
        "                            DEBUG: checking config ",
        "                     ERROR: out of memory ",
        " INFO: request received ",
        " ERROR: connection timeout "
    )

    // Construir a pipeline pedida
    val pipeline = buildPipeline {

        // 1. Limpar espaços
        addStage("Trim") { listaDeTextos -> listaDeTextos.map { linha -> linha.trim() }} // Usamos o "map" porque queremos transformar cada linha, limpando os espaços com o "trim()"

        // 2. Filtrar apenas os erros
        addStage("Filter errors") { listaDeTextos -> listaDeTextos.filter { linha -> linha.contains("ERROR") }} // Usamos o "filter" para verificar se a linha contém a palavra "ERROR"

        // 3. Converter para maiúsculas
        addStage("Uppercase") { listaDeTextos -> listaDeTextos.map { linha -> linha.uppercase() }}

        // 4. Adicionar o número (índice) no início de cada etapa
        addStage("Add index") { listaDeTextos -> listaDeTextos.mapIndexed { index, linha -> "${index + 1}. $linha" }} // Usamos o "mapIndexed" porque fornece a linha e a sua posição index ao mesmo tempo

    }

    //Imprime o nome de todas as etapas da pipeline
    pipeline.describe()

    println("Result:")
    // Põe os dados na pipeline e guarda o resultado final
    val resultadoFinal = pipeline.execute(logs)

    // Imprime cada linha já limpa e processada
    resultadoFinal.forEach { linha ->
        println(linha)
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------
    //TESTE DAS FUNÇÕES CHALLENGE IMPLEMENTADAS

    println("\nTESTAR FUNÇÃO COMPOSE")
    pipeline.compose("Trim", "Filter errors", "Trim e filtrar")
    pipeline.describe()

    println("\nTESTAR FUNÇÃO FORK")
    val pipelineInfo = buildPipeline { addStage("Apenas Info"){listaTextos -> listaTextos.filter { linha->linha.contains("INFO") }} }

    val resultadoFork = fork(pipeline, pipelineInfo, logs)

    println("Resultado da Pipeline Original:")
    resultadoFork.first.forEach { println(it) }

    println("\nResultado da Pipeline Info:")
    resultadoFork.second.forEach { println(it) }

}