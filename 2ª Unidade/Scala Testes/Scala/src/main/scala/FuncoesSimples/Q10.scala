package FuncoesSimples

def menor(a: Double, b: Double): Double = if (a < b) a else b

object Q10 {
  def main(array: Array[String]): Unit = {
    val a = 2.2
    val b = 2.3
    println(menor(a, b))
  }
}

