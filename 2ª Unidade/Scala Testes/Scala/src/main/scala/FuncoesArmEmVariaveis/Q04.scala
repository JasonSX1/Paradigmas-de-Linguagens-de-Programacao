package FuncoesArmEmVariaveis

object Q04 extends App{
  val descontoEstudante = (valor: Double) => valor * 0.9
  val descontoBlackFriday = (valor: Double) => valor * 0.8
  val descontoVip = (valor: Double) => valor * 0.85

  val precoOriginal = 100.0
  val tipoDesconto = "Black Friday"

  val resultado = tipoDesconto match{
    case "Black Friday" => descontoBlackFriday(precoOriginal)
    case "Estudante" => descontoEstudante(precoOriginal)
    case "VIP" => descontoVip(precoOriginal)
  }

  println(s"Preço com desconto: ${resultado}")
}