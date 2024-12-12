object maint2q5 extends App {

  def filtrarSomar(filterFunc: Int => Boolean, mapFunc: Int => Int): List[Int] => Int = {
    lista => lista.filter(filterFunc).map(mapFunc).sum
  }

  val filtrarImpares: (Int => Boolean) = x => x % 2 == 1
  val mapDobro: (Int => Int) = x => x * 2

  val processar = filtrarSomar(filtrarImpares, mapDobro)

  val listaNumeros = List(1, 2, 3, 4)

  val aux1 = processar(listaNumeros)
  
  println(aux1)

}