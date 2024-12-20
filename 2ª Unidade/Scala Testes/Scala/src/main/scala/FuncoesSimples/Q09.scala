package FuncoesSimples

def concatString(w1: String, w2: String): String = {
  w1 + " " + w2
}

object Q09 {
  def main(array: Array[String]): Unit = {
    val word1 = "Bom"
    val word2 = "Dia"
    println(concatString(word1, word2))
  }
}
