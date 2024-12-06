package Aula_11

// MAP
object mainMap extends App {
  // Lista de preços originais
  val precos = List(100.0, 200.0, 300.0, 400.0, 500.0)

  // Função para calcular o preço com desconto de 10%
  def aplicarDesconto(preco: Double): Double = preco * 0.9

  // Usando map para aplicar o desconto em cada elemento da lista
  val precosComDesconto = precos.map(aplicarDesconto)

  val precosComDescontoLambda = precos.map(preco => preco * 0.9)

  val precosComDescontoOmitido = precos.map(_ * 0.9)

  // Exibindo os resultados
  println("Preços originais: " + precos)
  println("Preços com desconto: " + precosComDesconto)
}
