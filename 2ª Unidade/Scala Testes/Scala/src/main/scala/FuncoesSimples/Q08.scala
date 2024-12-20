package FuncoesSimples

def isBissexto(ano: Int): Boolean = {
  ano % 400 == 0 || (ano % 100 != 0 && ano % 4 == 0)
}

object Q08 {
  def main(array: Array[String]): Unit = {
    println(isBissexto(100))
    println(isBissexto(2024))
  }
}


