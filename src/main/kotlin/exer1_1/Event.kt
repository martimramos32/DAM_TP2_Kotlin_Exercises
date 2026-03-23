package org.example.exer1_1

//É de notar que subclasses desta seguinte classe foram declaradas como data class, pois existe a necessidade de serem impressas nos testes realizados. Como a funçao toString já é gerada automaticamente para este tipo de classes, torma-se mais fácil a sua impressão na consola
//Se as sunbclasses não fossem definidas como data class, a sua visualização como output na consola não seria legível
sealed class Event {
    data class Login(val username: String, val timestamp: Long): Event()
    data class Purchase(val username: String, val amount: Double, val timestamp: Long): Event()
    data class Logout(val username: String, val timestamp: Long): Event()
}

fun List<Event>.filterByUser(username: String): List<Event> {
    //A função retorna uma lista com os eventos associados a um determinado utilizador
    //Usa-se o metodo filter para iterar sobre a lista de eventos e verificar se o nome do utilizador está associado a algum dos vários eventos (Login, Purchase ou Logout).
    //Se o nome do utilizador estiver associado a algum desses eventos, o evento é adicionado à lista de resultados que será retornada no final da função
    return this.filter { evento ->
        when(evento) {
            is Event.Login -> evento.username == username
            is Event.Purchase -> evento.username == username
            is Event.Logout -> evento.username == username
        }
    }
}

fun List<Event>.totalSpent(username: String): Double {
    //O metodo filterIsIsntance retorna uma lista que contém todas as instâncias de uma determinada classe. Neste caso aplicado retorna uma lista que contém apenas as instancias do evento Purchase
    //Depois disso, usa-se o filter para encontrar apenas os eventos de compra do usuário em questão e por fim soma-se o valor de todas as compras para ter-se o valor total gasto (através do metodo sumOf)
    return this.filterIsInstance<Event.Purchase>().filter { compra -> compra.username == username}.sumOf { compra -> compra.amount }
}

fun processEvents(listaEventos: List<Event>, handler: (Event)-> Unit){
    //Utiliza-se o metodo forEach, pois queremos percorrer todos os eventos da lista e para cada um deles aplicar o handler
    listaEventos.forEach { evento -> handler(evento) }
}
