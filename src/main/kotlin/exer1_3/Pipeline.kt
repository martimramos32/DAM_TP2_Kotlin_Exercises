package org.example.exer1_3

class Pipeline {
    private val steps = mutableListOf< Pair<String, (List<String>) -> List<String> > >() // cria uma lista vazia que só pode receber pares em que lá dentro esteja o nome do step e a regra de transformação a realizar que pega numa lista e devolve outra nova

    //Função responsável por acrescentar a uma determinada lista um determinado par (nome, transformação)
    fun addStage(name: String, transform: (List<String>)-> List<String>) {
        val par = Pair(name, transform) //criamos o par a acrescentar à lista
        steps.add(par) //acrescentamos este par à lista
    }

    //função responsável por percorrer por ordem todas as entradas por todos as etapas (steps) e retornar o resultado final desta operação
    fun execute(input: List<String>): List<String> {
        var dadosAtuais = input //dados atuais declarados como var pois o seu valor será modificado no futuro
        //é necessario criar a variavel acima porque o Kotlin não deixa "mexer" nos dados recebidos entre parênteses, pois são considerados como constantes (val). isto serve para nao ser possivel destruir ou alterar os dados originais
        for (step in steps){
            val funcaoTransform = step.second // first seria o nome do step mas aqui queremos a regra de transformaçao que é o segundo valor do par
            dadosAtuais = funcaoTransform(dadosAtuais)
        }
        return dadosAtuais
    }

    fun describe() {
        println("Pipeline stages:")
        for ((index, step) in steps.withIndex()) { //a funcao withIndex permite ver a posição na fila e a etapa
            println("${index + 1}. ${step.first}") //imprime o nome de cada etapa e numera-a na lista
        }
    }

    //CHALLENGES

    fun compose(nameStage1: String, nameStage2: String, newName: String){
        val etapa1 = steps.find{it.first == nameStage1}
        val etapa2 = steps.find{it.first == nameStage2}

        if(etapa1 != null && etapa2 != null) {
            val regra1 = etapa1.second
            val regra2 = etapa2.second

            //Fundir as duas regras(equivalente a usar um addThen)
            //Os dados  entram, passam pela regra1 e o resultado segue para a regra2
            val regraCombinada: (List<String>) -> List<String> = { entrada -> regra2(regra1(entrada)) }

            //Adicionar a nova etapa à lista
            addStage(newName, regraCombinada)
        }else{
            println("Uma ou nenhuma das etapas foi encontrada na lista.")
        }
    }
}

fun  buildPipeline(config: Pipeline.() -> Unit): Pipeline {
    //O uso da expressão "Pipeline.()" significa que o bloco de código seguinte irá funcionar como se estivesse dentro da classe Pipeline
    //O uso do "-> Unit" a seguir à expressão anterior, significa que o argumento config não precisará de retornar nada, simplesmente executa uma determinada função sem ter de entregar nada
    val newPipeline = Pipeline()
    newPipeline.config() //aplica a função lambda sem retornar nada
    return newPipeline //retorna a pipeline pronta após sofrer uma determinada configuração
}

fun fork(pipelineA: Pipeline, pipelineB: Pipeline, input: List<String>): Pair<List<String>, List<String>>{
    val resultadoA = pipelineA.execute(input) //A pipeline A processa os dados originais
    val resultadoB = pipelineB.execute(input) //A pipeline B processa os mesmos dados originais
    return Pair(resultadoA, resultadoB) //retorna ambos os resultados empacotados num par
}