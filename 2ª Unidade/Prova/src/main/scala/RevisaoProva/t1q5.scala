object Main extends App {

  def geradorDeValidador(isValid: Int => Boolean): List[Int] => Boolean = {
    lista => lista.exists(isValid)
  }

  val par: (Int => Boolean) = x => x%2 == 0
  val maior25: (Int => Boolean) = x => x>25
  val positivo: (Int => Boolean) = x => x>=0


  val listanumeros = List(1,2,3,4,5)

  val listaValidacoes: (List[Int => Boolean]) = List(par, maior25, positivo)

  val existePar = geradorDeValidador(par)
  val existeMaiorQue25 = geradorDeValidador(maior25)
  val existePositivo = geradorDeValidador(positivo)

  println(existePar(listanumeros))
  println(existeMaiorQue25(listanumeros))
  println(existePositivo(listanumeros))
}