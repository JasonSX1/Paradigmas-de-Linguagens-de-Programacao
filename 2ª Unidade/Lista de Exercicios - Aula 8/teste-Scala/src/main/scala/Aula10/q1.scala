package Aula10
// o assunto é funcao q retorna funcao
class q1 {

  object Main extends App{
    def multiplica(a: Int): ((Int, Int) => Int) = {
      (x: Int, y: Int) => x * y * a
    }

    val triplo = multiplica(3)
    val dobro = multiplica(2)
    println((triplo)(10, 4))
  }
}
