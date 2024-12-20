package FuncoesRetornadas

object Q03 extends App {
  def getFuncaoValidacao(funcao: String): String => Boolean = {
    val tipoFunc = funcao.toLowerCase

    tipoFunc match {
      case "email" => (entrada: String) => entrada.contains("@") && entrada.contains(".")
      case "telefone" => (num: String) => num.length == 11 && num.forall(_.isDigit)
      case _ => (entrada: String) => false
    }
  }

  val validadorEmail = getFuncaoValidacao("email")
  println(validadorEmail("test@example.com"))
}
