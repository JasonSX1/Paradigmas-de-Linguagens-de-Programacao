package Aula_8

case class Pessoa(nome: String, idade: Int)

class CopyCase {
  val pessoa1 = Pessoa("Alice", 30)

  val pessoa2 = pessoa1.copy(idade = 35)

  val pessoa3 = pessoa1.copy(nome = "Geison")

  println(pessoa1)
  println(pessoa2)
  println(pessoa3)
}
