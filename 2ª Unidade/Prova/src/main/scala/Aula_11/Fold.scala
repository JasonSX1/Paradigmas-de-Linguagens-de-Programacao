package Aula_11

object mainFold extends App {
  // Lista de strings
  val palavras = List("Scala", "é", "incrível!")

  // Usando fold para concatenar as palavras, com valor inicial ""
  val frase = palavras.fold("")((a, b) => a + " " + b)

  // Exibindo o resultado
  println(s"Frase completa: '$frase'")
}
