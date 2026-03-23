package org.example.exer1_1

sealed class Event {
    class Login(val username: String, val timestamp: Long): Event()
    class Purchase(val username: String, val amount: Double, val timestamp: Long): Event()
    class Logout(val username: String, val timestamp: Long): Event()
}

fun List<Event>.filterByUser(username: String): List<Event> {
    return this.filter { evento ->
        when(evento) {
            is Event.Login -> evento.username == username
            is Event.Purchase -> evento.username == username
            is Event.Logout -> evento.username == username
        }
    }
}

fun List<Event>.totalSpent(username: String): Double {
    // O método filterIsIsntance retorna uma lista que contém todas as instancias de uma determinada classe. Neste caso aplicado retorna uma lista que contém apenas as instancias do evento Purchase
    // Depois disso, usa-se o filter para encontrar apenas os eventos de compra do usuário em questão e por fim soma-se o valor de todas as compras para ter-se o valor total gasto (através do metodo sumOf)
    return this.filterIsInstance<Event.Purchase>().filter { compra -> compra.username == username}.sumOf { compra -> compra.amount }
}