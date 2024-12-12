package RevisaoProva

object mainT1q1 extends App {

//  val definicao = ""
//  val solucao = ""
//
//  println("questao 1")
//  println(s"${definicao} ${solucao}")

  //=======================================================

  val soma2: (Int => Int) = x => x+2
  val multi5: (Int => Int) = x => x*5
  val sub3: (Int => Int) = x => x-3

  val operacoes: (List[Int => Int]) = List(soma2, multi5, sub3)

  //funcao que aplica todas as funcoes da lista de operacoes a cada elemento de numeros
  def aplicarOperacoes(numeros: List[Int]): List[Int] = {
    numeros.map(num => operacoes.foldLeft(num)((acc, op) => op(acc)))
  }

  def repetirElementos(lista: List[Int]): List[Int] = {
    lista match {
      case List() => List()
      case head :: tail => head :: head :: head :: repetirElementos(tail)
    }
  }

  //execucao do código

  val lista1: (List[Int]) = List(1,2,3)
  val lista2: (List[Int]) = List(10)
  val lista3: (List[Int]) = List()

  val aux1 = aplicarOperacoes(lista1)
  val aux2 = aplicarOperacoes(lista2)
  val aux3 = aplicarOperacoes(lista3)
  
  val saida1 = repetirElementos(lista1)
  val saida2 = repetirElementos(lista2)
  val saida3 = repetirElementos(lista3)
  
  println(saida1)
  println(saida2)
  println(saida3)
}
