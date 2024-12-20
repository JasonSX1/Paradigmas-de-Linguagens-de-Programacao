package FuncoesPassadasComoParametros

object Q03 extends App {
  case class Venda(valor: Double, cidade: String)

  val vendas = List(
    Venda(1200.0, "São Paulo"), 
    Venda(800.0, "Rio de Janeiro"), 
    Venda(2000.0, "São Paulo")
  )
  
  def calcularTotalComBonus(vendas : List[Venda], filtro: Venda => Boolean, bonus: Double): Double ={
    vendas.filter(filtro).map(x => x.valor * (1+ bonus)).sum
  }
  
  val Acima1000 = (venda: Venda) => venda.valor > 1000
  val VendasSP = (venda: Venda) => venda.cidade == "São Paulo"

  println(calcularTotalComBonus(vendas, Acima1000, 0.1))
  println(calcularTotalComBonus(vendas, VendasSP, 0.1))
  
}
