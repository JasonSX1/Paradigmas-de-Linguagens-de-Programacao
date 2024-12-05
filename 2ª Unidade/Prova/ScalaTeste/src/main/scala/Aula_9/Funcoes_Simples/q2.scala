//Exercício 02: Faça uma função pura mult3 que retorne True caso a entrada seja múltiplo de 3 e False caso contrário.
class Teste(val idade: Int){}
//Classe criada somente para encapsular a variável idade, que poderia ser instanciada diretamente na função abaixo, ficando:
//
//
//  def main(args: Array[String]): Unit = {
//    val idade = 20
//    println(s"O resultado é: ${mult(idade)}")
//    // Retorna se a idade é múltiplo de 3
//  }
//
//

object Main {
  def mult(x: Int): Boolean = x % 3 == 0

  def main(args: Array[String]): Unit = {
    val t = new Teste(20)
    println(s"O resultado é: ${mult(t.idade)}")
    //Retorna se a idade de Teste é multiplo de 3
  }
}

