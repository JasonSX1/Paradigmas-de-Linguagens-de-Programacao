package FuncoesSimples

def multiplicacaoEtiopica(a: Int, b: Int): Int = {
  def multiplicar(x: Int, y: Int, acc: Int): Int = {
    if (x == 0) acc
    else if (x % 2 == 1) multiplicar(x / 2, y * 2, acc + y) // Adiciona y ao acumulador se x for ímpar
    else multiplicar(x / 2, y * 2, acc) // Apenas divide x por 2 e dobra y
  }
  multiplicar(a, b, 0)
}

object Q14 {
  def main(array: Array[String]): Unit = {
    println(multiplicacaoEtiopica(3, 4))   // 12
    println(multiplicacaoEtiopica(5, 6))   // 30
    println(multiplicacaoEtiopica(7, 8))   // 56
    println(multiplicacaoEtiopica(10, 20)) // 200
  }
}
