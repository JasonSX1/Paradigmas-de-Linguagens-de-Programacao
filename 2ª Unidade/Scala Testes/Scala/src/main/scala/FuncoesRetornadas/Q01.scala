package FuncoesRetornadas

object Q01 extends App{
  def multiplica(x: Int): Int => Int = {
    (y: Int) => x * y
  }

  val triplo = multiplica(3)
  println(triplo(10))
}
