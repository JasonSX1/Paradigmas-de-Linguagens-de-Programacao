//Exercício 04: Faça uma função pura que forneça uma temperatura em graus Fahrenheit a
//  partir de uma temperatura em graus Celsius.
package Funcoes_Simples

def conversor(tempc: Double): Double = tempc * 1.8 + 32

object mainq4{
  def main(args: Array[String]): Unit = {
    println(conversor(0))
    println(conversor(100))
    println(conversor(37.8))
  }
}