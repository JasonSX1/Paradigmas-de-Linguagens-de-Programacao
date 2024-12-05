package Aula_8

class EnumCase {
    enum DiaSemana {
      case Segunda, Terca, Quarta, Quinta, Sexta, Sabado, Domingo
    }
  
    def diaDeTrabalho(dia: DiaSemana): Boolean = {
      dia match {
        case DiaSemana.Sabado | DiaSemana.Domingo => false
        case _ => true
      }
    }

    private val dia1 = DiaSemana.Segunda
    //Verifica se o dia 1 é um dia de trabalho e retorna um booleano positivo, caso seja
    println(diaDeTrabalho(dia1))
}
