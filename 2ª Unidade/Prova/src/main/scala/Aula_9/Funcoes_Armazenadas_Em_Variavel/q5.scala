package Aula_9.Funcoes_Armazenadas_Em_Variavel

object mainq5 extends App{
  case class Pessoa(nome: String, idade: Int)

  val pessoa1 = new Pessoa("Ana", 30)

  val paraTexto = (p: Pessoa) => s"Nome: ${p.nome}, Idade: ${p.idade}"

  println(paraTexto(pessoa1))
}
