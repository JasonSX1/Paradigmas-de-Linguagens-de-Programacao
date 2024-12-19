package FuncoesRecursivas

def somaDigitos(n: Int): Int = {
  if (n < 0) somaDigitos(-n) // Se o número for negativo, converte para positivo
  else if (n == 0) 0         // A soma dos dígitos de 0 é 0
  else (n % 10) + somaDigitos(n / 10) // Adiciona o último dígito e chama a função recursivamente
}

object Q01{
  def main(args: Array[String]): Unit = {
    println(somaDigitos(12345))
  }
}

