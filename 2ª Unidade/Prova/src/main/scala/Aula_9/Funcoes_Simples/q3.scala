//Exercício 03: Faça uma função pura mult35 que retorne True caso a entrada seja múltiplo de 3 e 5 e False caso contrário.
package Funcoes_Simples

def mult35(x: Int): Boolean = (x%3==0 | x%5==0)

object mainq3s{
  def main(args: Array[String]): Unit = {
    println(mult35(12))
    println(mult35(1))
  }
}
