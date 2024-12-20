package FuncoesSimples

object Q03{
  def mult35(x: Int): Boolean = (x % 3 == 0 && x % 5 == 0)
  def main(args: Array[String]): Unit = {
    val t = new Teste(15)
    println(mult35(t.idade))
  }
}