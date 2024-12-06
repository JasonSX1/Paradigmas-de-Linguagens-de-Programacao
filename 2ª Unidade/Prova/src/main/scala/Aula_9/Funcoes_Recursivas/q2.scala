//  Exercício 02: Crie uma função pura que conte o número de dígitos em um inteiro n. Use
//  recursão.
def contarDigitos(x: Int): Int = {
  if (x<0) contarDigitos(-x)
  else if (x<10) 1
  else 1 + contarDigitos(x/10)
}


object mainq2{
  def main(args: Array[String]): Unit = {
    println(contarDigitos(115))
    println(contarDigitos(9999))
  }
}