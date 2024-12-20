package FuncoesSimples

class Teste(val idade: Int){}

object Q02 {
  def mult3(x: Int): Boolean = x % 3 == 0
  def main(args: Array[String]):Unit = {
    val t = new Teste(9)
    println(mult3(t.idade))
  }
}
