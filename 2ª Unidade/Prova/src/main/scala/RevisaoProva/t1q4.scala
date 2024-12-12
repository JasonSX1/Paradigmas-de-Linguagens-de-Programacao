object mainQ1t4 extends App {

  def filtrarEMapear(filterFunc: Int => Boolean, mapFunc: Int => Int): List[Int] => List[Int] = {
    lista => lista.filter(filterFunc).map(mapFunc)
  }

  val filtrarPares: (Int => Boolean) = x => x%2 == 0
  val mapQuadrado: (Int => Int) = x => x*x

  val lista1 = List(1,2,3,4,5,56)

  val aux1 = filtrarEMapear(filtrarPares, mapQuadrado)

  val aux2 = aux1(lista1)

  println(aux2)
}