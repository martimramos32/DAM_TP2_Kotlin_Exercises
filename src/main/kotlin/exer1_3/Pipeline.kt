package org.example.exer1_3

class Pipeline {
    private val steps = mutableListOf< Pair<String, (List<String>) -> List<String> > >() // cria uma lista vazia que só pode receber pares em que lá dentro esteja o nome do step e a regra de transformação a realizar que pega numa lista e devolve outra nova

    //Função responsável por acrescentar a uma determinada lista um determinado par (nome, transformação)
    fun addStage(name: String, transform: (List<String>)-> List<String>) {
        val par = Pair(name, transform) //criamos o par a acrescentar à lista
        steps.add(par) //acrescentamos este par à lista
    }

    fun execute(input: List<String>): List<String> {
        var dadosAtuais = input //dados atuais declarados como var pois o seu valor será modificado no futuro
        //é necessario criar a variavel acima porque o Kotlin não deixa "mexer" nos dados recebidos entre parênteses, pois são considerados como constantes (val). isto serve para nao ser possivel destruir ou alterar os dados originais
        for (step in steps){
            val funcaoTransform = step.second // first seria o nome do step mas aqui queremos a regra de transformaçao que é o segundo valor do par
            dadosAtuais = funcaoTransform(dadosAtuais)
        }
        return dadosAtuais
    }
}