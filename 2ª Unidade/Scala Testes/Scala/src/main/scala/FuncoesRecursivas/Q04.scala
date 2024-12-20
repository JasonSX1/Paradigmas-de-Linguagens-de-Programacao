package FuncoesRecursivas

def fatorial(n: Int): Int = {
  if (n < 0) fatorial(-n) // Se o número for negativo, converte para positivo
  else if (n == 0) 1         // A soma dos dígitos de 0 é 0
  else n * fatorial(n-1) // Adiciona o último dígito e chama a função recursivamente
}

object Q04{
  def main(args: Array[String]): Unit = {
    println(fatorial(3))
  }
}

