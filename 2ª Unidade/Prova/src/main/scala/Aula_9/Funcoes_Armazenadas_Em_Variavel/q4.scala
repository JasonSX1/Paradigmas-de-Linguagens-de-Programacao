package Aula_9.Funcoes_Armazenadas_Em_Variavel

object mainq4 extends App {
  val descontoEstudante = (preco: Double) => preco * 0.9
  val descontoBlackFriday = (preco: Double) => preco * 0.8
  val descontoVIP = (preco: Double) => preco * 0.85

  val precoOriginal = 100.0
  val tipoDesconto = "Black Friday"

  val precoFinal = tipoDesconto match {
    case "Estudante" => descontoEstudante(precoOriginal)
    case "Black Friday" => descontoBlackFriday(precoOriginal)
    case "VIP" => descontoVIP(precoOriginal)
  }

  println(s"Preco final com desconto: $precoFinal")
}