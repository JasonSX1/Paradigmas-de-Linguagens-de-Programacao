package Aula_11

object mainReduce extends App {
  // Lista de números
  val numeros = List(1, 2, 3, 4, 5)

  println(numeros.forall(_<10))
}