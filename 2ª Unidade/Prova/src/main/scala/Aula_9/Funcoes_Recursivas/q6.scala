//Exercício 06: Crie uma função pura que retorne o n-ésimo número da sequência de
//Fibonacci. Na sequência de Fibonacci, cada número é a soma dos dois números anteriores,
//com os dois primeiros números sendo 0 e 1. Use recursão.

//sequencia de fibonacci - (n-1) + (n-2)

package Aula_9.Funcoes_Recursivas



object mainq6 extends App{

  def fibonacci(posicao: Int, nAtual: Int = 1, nAnterior: Int = 0, contador: Int = 2): Int = {
    if (posicao < contador) 0
    else if (posicao == contador) nAtual
    else (fibonacci(posicao, nAtual+ nAnterior,nAtual, contador+1))

  }

  println(fibonacci(4))
}