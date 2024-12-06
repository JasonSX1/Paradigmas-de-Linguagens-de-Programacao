//Exercício 05: Faça uma função pura que calcule o coeficiente binomial de (m,n).
package Aula_9.Funcoes_Recursivas

object mainq5 {
  // Função que calcula o fatorial de um número
  def fatorial(n: Int): Int = {
    if (n < 0) throw new IllegalArgumentException("Fatorial de número negativo não é definido.")
    else if (n == 0) 1
    else n * fatorial(n - 1)
  }

  // Função que calcula o coeficiente binomial, Combinação de m elementos tomados n a n.
  def coeficienteBinomial(n: Int, k: Int): Int = {
    if (k < 0 || k > n) 0 // C(m, n) é 0 se n é negativo ou maior que m
    else fatorial(n) / (fatorial(k) * fatorial(n - k))
  }

  // Exemplos de uso
  def main(args: Array[String]): Unit = {
    println(coeficienteBinomial(5, 2)) // 10
    println(coeficienteBinomial(10, 3)) // 120
    println(coeficienteBinomial(6, 0)) // 1
    println(coeficienteBinomial(6, 6)) // 1
    println(coeficienteBinomial(5, 7)) // 0
  }
}