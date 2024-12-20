package FuncoesSimples

def isNegativeOrCondition(x: Int): Boolean = (x < -1 || (x > 1 && x % 2 == 0))

object Q05 {
  def main(array: Array[String]): Unit = {
    val t = new Teste(20)
    println(isNegativeOrCondition(t.idade))
  }
}

