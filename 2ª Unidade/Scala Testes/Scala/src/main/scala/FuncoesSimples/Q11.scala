package FuncoesSimples

def menor2v (a: Double, b: Double, c: Double): Double = {
  if (a <= b && a <= c) a
  else if (b <= a && b <= c) b
  else c
}

object Q11 {
  def main(array: Array[String]): Unit = {
    val a = 2.8
    val b = 2.3
    val c = 2.4
    println(menor2v(a, b, c))
  }
}

