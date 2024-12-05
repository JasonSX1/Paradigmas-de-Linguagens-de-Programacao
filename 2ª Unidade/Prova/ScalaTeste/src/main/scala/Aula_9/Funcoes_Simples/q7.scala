//Exercício 07: Faça uma função pura que receba um ângulo a e retorne uma tupla contendo o seno da metade desse ângulo utilizando a identidade: sen(x/2) = +-(sqr([1-cos(x)]/2))
import scala.math._


def senoMetadeAngulo(x: Double): (Double, Double) = {
  val xRad = toRadians(x) //converte para radianos para um calculo preciso
  val resultado = sqrt((1 - cos(xRad)) / 2)
  (resultado, -resultado)
}

object mainq7 {
  
  def main(args: Array[String]): Unit = {
    
    println("a")
    val angulo = 90
    val (pos, neg) = senoMetadeAngulo(angulo)
    println(f"sen(x/2) é: +$pos%.4f e $neg%.4f")
  }
}