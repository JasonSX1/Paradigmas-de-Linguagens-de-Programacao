object Main extends App{
  val x: Int = 10
  val operacaoSomar: ((Int, Int) => Int) = (a: Int, b: Int) => a+b
  val operacaoMultiplicar: ((Int, Int) => Int) = (a, b) => a*b /*ele pode ser _+_, que o scala infere os tipos/variaveis*/
  /*ISSO AQ SAO FUNCOES LAMBDAS, ELAS TEM O => E NAO TEM DEF

   */

  def operacaoCondicional(operacao: ((Int, Int) => Int), condicao((Int, Int) => Int), a: Int, b: Int): Int = {
    if condicao(a,b) operacao(a,b) else 0
  }

  val condicaoMaiorQue: (Int, Int) => Boolean = (a: Int, b: Int) => a > b
  val condicaoIgual:  (Int, Int) => Boolean = (a: Int, b: Int) => a=b
}