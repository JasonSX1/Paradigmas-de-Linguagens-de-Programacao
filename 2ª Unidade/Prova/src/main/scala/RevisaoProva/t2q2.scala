object maint2q2 extends App {

  val mult10: (Int => Int) = x => x*10
  val soma4: (Int => Int) = x => x+4
  val menos5: (Int => Int) = x => x-5
  val operacoes: (List[Int => Int]) = List(mult10, soma4, menos5)

  def aplicarOperacoes(numeros: List[Int]): List[Int] = {
    numeros.map(num => operacoes.foldLeft(num)((acc, op) => op(acc)))
  }

  def repetirElementos (lista: List[Int]): List[Int] = {
    lista match {
      case List() => List()
      case head :: tail => head :: head :: repetirElementos(tail)
    }
  }

  val listaNumeros = List(1,2,3,4)

  val aux1 = aplicarOperacoes(listaNumeros)

  val aux2 = repetirElementos(aux1)

  println(aux2)
}