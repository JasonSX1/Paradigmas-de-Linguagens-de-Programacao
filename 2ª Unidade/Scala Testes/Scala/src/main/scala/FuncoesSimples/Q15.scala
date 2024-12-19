package FuncoesSimples

def calcPassagem (idade : Int, valor: Double) : Double = {
  if (idade >= 60) {
  valor * 0.6 // Pessoas com 60 anos ou mais pagam 60%
} else if (idade < 2) {
  valor * 0.1 // Bebês abaixo de 2 anos pagam 10%
} else if (idade <= 10) {
  valor * 0.5 // Crianças até 10 anos pagam 50%
} else {
  valor // Pessoas entre 11 e 59 anos pagam o valor total
}
}

object Q15 {
  def main(array: Array[String]): Unit = {
    val valor = 100.0
    val idade = 60
    println(calcPassagem(idade, valor))
  }
}
