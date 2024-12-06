//Exercício 04: Faça uma função pura que calcule o fatorial de um número. Use recursão.
def fatorial(x: Int): Int = {
  if(x<0) throw new IllegalArgumentException("Fatorial de um número negativo não é definido.")
  else if (x==0) 1
  else x * fatorial(x-1)
}


object mainq4r{
  def main(args: Array[String]): Unit = {
    println(fatorial(5))
    println(fatorial(7))
  }
}