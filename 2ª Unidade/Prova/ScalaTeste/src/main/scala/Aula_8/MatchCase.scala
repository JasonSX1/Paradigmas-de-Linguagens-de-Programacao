package Aula_8

class MatchCase{
  val nome = String("Vitu")
  val valor: Any = 42.5
  val numero = 5

  nome match {
    case "Alice" => println("Olá, Alice!")
    case outro => println(s"Olá, $outro!")
    //O prefixo s antes das aspas define uma string interpolada em Scala
  }
  valor match {
    case a: String => println(s"String com valor: $a")
    case b: Int => println(s"Integer com valor: $b")
    case c: Double => println(s"Double com valor: $c" )
    case _ => println("Outro tipo")
  }

  numero match {
    case x if x > 0 =>
      println("Número positivo, ")
      print(s"Valor: $x")
    case _ =>
      println("Não é positivo")
  }
}