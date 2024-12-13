object maint2q6 extends App {
  def geradorDeValidador(isValid: Int => Boolean): List[Int] => Boolean = {
    lista => lista.forall(isValid)
  }

  val par: (Int => Boolean) = x => x%2 == 0
  val maiorQue25: (Int => Boolean) = x => x>25
  val positivo: (Int => Boolean) = x => x>25

}