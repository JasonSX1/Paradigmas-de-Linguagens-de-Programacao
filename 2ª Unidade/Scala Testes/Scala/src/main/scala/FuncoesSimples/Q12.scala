package FuncoesSimples

def isTriangulo (a: Double, b: Double, c: Double ) : Boolean = {
  (a + b > c)&&(a + c > b)&&(b + c > a)
}

object Q12 {
  def main(array: Array[String]): Unit = {
    val a = 3
    val b = 2
    val c = 2
    println(isTriangulo(a, b, c))
  }
}
