package Aula_11

object mainFilter extends App {
  // Lista de números inteiros
  val numeros = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  // Usando filter para extrair os números pares
  val numerosPares = numeros.filter(_ % 2 == 0)

  // Exibindo os resultados
  println("Números originais: " + numeros)
  println("Números pares: " + numerosPares)
}
