package Aula_9.Funcoes_Recursivas

object mainPrimoRecursivo extends App{

  def ehprimo(numero: Int, divisor: Int=2): Boolean = {
    if(numero < 2) false
    else if (divisor > numero/2) true
    else if (numero % divisor == 0) false
    else ehprimo(numero, divisor+1)
  }

  println(ehprimo(15))
}