//Exercício 05: Faça uma função pura que retorne True caso a entrada seja menor que -1 ou
//  (maior que 1 E múltiplo de 2), e False caso contrário.

def filtroEntrada(x: Int): Boolean = (x<(-1) || (x>1 && x%2==0) )

object mainq5 {
  def main(args: Array[String]): Unit = {
    println(filtroEntrada(-3))
    println(filtroEntrada(4))
    println(filtroEntrada(5))
  }
}