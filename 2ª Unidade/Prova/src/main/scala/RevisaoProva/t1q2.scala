//a funcao somalista deve receber uma lista de inteiros e retornar a soma de seus elementos

object maint1q2 extends App {

  def somaLista(lista: List[Int]): Int = {
    def somaListaRecursao(lista: List[Int], acumulador: Int): Int = {
      lista match {
        case List() => acumulador
        case head :: tail => somaListaRecursao(tail, acumulador + head)
      }
    }
    somaListaRecursao(lista,0)
  }

  // ENTRADA
  val entrada1_Q3 = List(1, 2, 3, 4, 5)
  val entrada2_Q3 = List(3)
  val entrada3_Q3 = List()
  // PROCESSAMENTO
  val saida1_Q3 = somaLista(entrada1_Q3)
  val saida2_Q3 = somaLista(entrada2_Q3)
  val saida3_Q3 = somaLista(entrada3_Q3)
  // SAÍDA
  println("************** QUESTÃO 3 **************")
  println(saida1_Q3)
  println(saida2_Q3)
  println(saida3_Q3)
}

