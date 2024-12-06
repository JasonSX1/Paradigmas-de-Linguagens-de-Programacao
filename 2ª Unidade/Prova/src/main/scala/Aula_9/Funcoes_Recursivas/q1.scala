//Exercício 01: Faça uma função pura que calcule a soma dos dígitos de um número. Use
//recursão.
def somaDigitos(x: Int): Int = {
  if (x<0) somaDigitos(-x)
  else if (x==0) 0
  else (x%10) + somaDigitos(x/10)
}


object mainq1{
  def main(args: Array[String]): Unit = {
    println(somaDigitos(12345))
  }
}