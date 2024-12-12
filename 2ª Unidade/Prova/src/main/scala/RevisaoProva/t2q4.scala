object maint2q4 extends App {
  def filtrar (lista: List[List[Int]]): List[Int] = {
    lista.flatMap(x=> x).distinct.sorted(Ordering.Int.reverse)
  }

  val entrada1_Q4 = List(List(3, 1, 4, 3), List(1, 5, 9), List(2, 6, 3, 5))

  val aux1 = filtrar(entrada1_Q4)

  println(aux1)

}