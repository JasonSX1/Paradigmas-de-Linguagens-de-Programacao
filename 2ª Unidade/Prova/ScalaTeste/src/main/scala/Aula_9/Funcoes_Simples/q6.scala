//Exercício 06: Faça uma função pura que recebe um tipo Integer e retorna ele dividido por 2:
//div2d :: Integer -> Double
def div2d(x: Integer): Double = x / 2.0

object mainq6{
  def main(args: Array[String]): Unit = {
    println(div2d(20))
    println(div2d(5))
  }
}

//ESSA FUNÇÃO É DETERMINISTICA E NAO GERA EFEITOS COLATEIAIS, PARA AS MESMAS ENTRADAS TENHO AS MESMAS SAÍDAS E ELA NAO ALTERA ESTADOS EXTERNOS
//POR ISSO ELA É PURA