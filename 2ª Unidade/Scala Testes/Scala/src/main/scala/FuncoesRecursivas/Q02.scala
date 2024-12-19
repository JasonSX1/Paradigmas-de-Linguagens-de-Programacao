package FuncoesRecursivas

def contDigitos(n: Int): Int = {
  if (n < 0) contDigitos(-n) // Se o número for negativo, converte para positivo
  else if (n < 10) 1         // A soma dos dígitos de 0 é 0
  else 1 + contDigitos(n / 10) // Adiciona o último dígito e chama a função recursivamente
}

object Q02{
  def main(args: Array[String]): Unit = {
    println(contDigitos(12345))
  }
}
