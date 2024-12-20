package FuncoesPassadasComoParametros

object Q02 extends App {
  case class Funcionario(nome: String, pontuacao: Int)

  val funcionarios = List(
    Funcionario("Alice", 85),
    Funcionario("Bruno", 65),
    Funcionario("Carlos", 90)
  )

  def filtrarFuncionarios(funcionarios: List[Funcionario], criterio: Funcionario => Boolean): List[Funcionario] = {
    funcionarios.filter(criterio)
  }

  val condicaoAcima80 = (funcionario: Funcionario) => funcionario.pontuacao > 80

  println(s"Funcionários que tem pontuação acima de 80: ${filtrarFuncionarios(funcionarios, condicaoAcima80)}")

}