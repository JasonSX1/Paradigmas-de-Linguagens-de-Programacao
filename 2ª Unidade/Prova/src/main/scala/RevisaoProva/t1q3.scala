//Utilizando a LP Scala, dada uma lista de inteiros, implemente uma função que retorne
//uma nova lista contendo apenas os números pares, multiplicados por 3 e ordenados
//em ordem decrescente.

object maint1q3 extends App {
  def filtrar(lista: List[Int]): List[Int] = {
    lista.filter(_%2==0).map(_*3).sorted(Ordering.Int.reverse)
  }

  val lista1 = List(1,2,3,4)

  val aux = filtrar(lista1)

  println(aux)
}