package FuncoesSimples

import scala.math._

object Q07 {
  def senoMetadeAngulo(x: Double): (Double, Double) = {
    val resultado = sqrt((1 - cos(x)) / 2)
    (resultado, -resultado)
  }

  def main(array: Array[String]): Unit = {
    val angulo = 60
    val (pos, neg) = senoMetadeAngulo(angulo)
    println(f"sen(x/2) é: +$pos%.4f e $neg%.4f")
  }
}


