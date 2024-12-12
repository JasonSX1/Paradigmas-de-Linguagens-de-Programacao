object maint2q6 extends App {
  def geradorDeValidador(isValid: Int => Boolean): List[Int] => Boolean = {
    lista => lista.forall(isValid)
  }

  
}