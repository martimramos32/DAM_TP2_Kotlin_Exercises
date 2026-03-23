package org.example.exer1_1

fun main(){
    // lista de eventos de teste presente no enunciado do trabalho
    val events = listOf(
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500)
    )

    //Escreve uma descrição humana legivél com recurso à expressão when para cada evento recorrendo à função processEvents
    processEvents(events) { evento ->
        when (evento) {
            is Event.Login -> println("[LOGIN] ${evento.username} logged in at t=${evento.timestamp}")
            is Event.Purchase -> println("[PURCHASE] ${evento.username} spent $${evento.amount} at t=${evento.timestamp}")
            is Event.Logout -> println("[LOGOUT] ${evento.username} logged out at t=${evento.timestamp}")
        }
    }

    //Calcula o total gasto pela Alice e pelo Bob usando a função totalSpent
    println("\nTotal spent by alice: $${events.totalSpent("alice")}")
    println("Total spent by bob: $${events.totalSpent("bob")}")

    //Imprimie apenas os eventos da Alice usando a função filterByUser
    println("\nEvents for alice:")
    val eventosAlice = events.filterByUser("alice")
    eventosAlice.forEach { evento ->
        println(evento)
    }
}