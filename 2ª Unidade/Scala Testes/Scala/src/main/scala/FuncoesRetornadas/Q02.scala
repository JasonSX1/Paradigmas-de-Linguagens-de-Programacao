package FuncoesRetornadas

object Q02 extends App {
  def getFuncaoFormatadora(style: String): String => String = {
    val estilo: String = style.toLowerCase
    estilo match {
      case "uppercase" => (text: String) => text.toUpperCase
      case "lowercase" => (text: String) => text.toLowerCase
      case "titlecase" => (text: String) => text.split(" ").map(x => x.capitalize).mkString(" ")
      case _ => (text: String) => text
    }
  }

  val formatadorTitulo = getFuncaoFormatadora("titlecase")
  println(formatadorTitulo("hello scala world")) 
}