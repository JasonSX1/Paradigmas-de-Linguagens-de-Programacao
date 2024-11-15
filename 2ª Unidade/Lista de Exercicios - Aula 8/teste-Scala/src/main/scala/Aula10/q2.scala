package Aula10

class q2 {
  object Main extends App {
    def classificacaoCategoria(i: Int, s: Int):
    (Int => Boolean) = {
      (a: Int) => a >= i && a < s
    }
    
    val categoriaAdulto =
      classificacaoCategoria(18,30)
    
    val categoriaJuvenil =
      classificacaoCategoria (18,16)
      println(categoriaAdulto(27))
  }

}
