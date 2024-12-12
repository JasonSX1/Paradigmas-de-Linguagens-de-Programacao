import scala.annotation.tailrec
//tem que receber uma lista e multiplicar os
// elementos da lista recursivamente

object maint2q3 extends App {

  def multiplicaLista(numeros: List[Int]): Int = {

    def multiplicaListaRecursivo(numeros: List[Int], acumulador: Int): Int = {
      numeros match {
        case List() => acumulador
        case head:: tail => multiplicaListaRecursivo(tail, acumulador*head)
      }
    }
    multiplicaListaRecursivo(numeros, 1)
  }

  val listanumeros = List(1,2,3,4,5)

  val aux1 = multiplicaLista(listanumeros)

 println(aux1)
}