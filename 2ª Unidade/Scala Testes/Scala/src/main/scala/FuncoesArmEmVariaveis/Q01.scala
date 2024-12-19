package FuncoesArmEmVariaveis

object Q01 extends App{
  val soma = (a: Int, b: Int) => a + b
  val subtracao = (a: Int, b: Int) => a - b
  val multi = (a: Int, b: Int) => a * b
  val div = (a: Int, b: Int) => a/b

  val numero1 = 8
  val numero2 = 2

  println(s"Soma: ${soma(numero1,numero2)}")
  println(s"Subtração: ${subtracao(numero1,numero2)}")
  println(s"Multiplicação: ${multi(numero1,numero2)}")
  println(s"Divisão: ${div(numero1,numero2)}")
}
