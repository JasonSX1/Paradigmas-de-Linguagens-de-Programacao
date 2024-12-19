package FuncoesPassadasComoParametros

object Q04 extends App{
  case class Evento( id: Int, nome: String, mensagem: String, prioridade: String)

  val eventos = List(
    Evento(1, "Manutenção", "Manutenção de rotina", "baixo"),
    Evento(2, "Alerta", "Alto uso de memória", "alto"),
    Evento(3, "Atualização", "Nova versão disponível", "médio")
  )

  def notificarEventos(eventos: List[Evento], filtro: Evento => Boolean): List[Evento] = {
    eventos.filter(filtro)
  }

  val filaPrioridadeAlta = (eventos:Evento) => eventos.prioridade == "alto"

  val eventosPrioridadeAlta = notificarEventos(eventos, filaPrioridadeAlta)

  println(s"Notificações para eventos com prioridade ala: ${eventosPrioridadeAlta}")
}


