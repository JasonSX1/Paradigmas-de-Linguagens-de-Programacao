package FuncoesSimples

def isPrimo (num: Int) : Boolean = {
  if (num <= 1) false
  else (2 to math.sqrt(num).toInt).forall(x => num % x != 0)
}

object Q16 {
  def main(array: Array[String]): Unit = {
    val a = 3
    val b = 6
    println(isPrimo(a))
    println(isPrimo(b))
  }
}
