package FuncoesArmEmVariaveis

class Pessoa(nome:String, idade:Int)

object Q05 extends App{
  case class Pessoa(nome:String, idade:Int)
  val gerarTexto = (p: Pessoa) => s"Nome: ${p.nome}, Idade: ${p.idade}"
  val pessoa = Pessoa("Ana", 30)

  println(gerarTexto(pessoa))
}