//Exercício 03: Faça uma função pura que calcule a persistência aditiva de um número. Use
//recursão.

//PERSISTENCIA ADITIVA é o numero de etapas necessarias para reduzir um numero a somente um dígito
//somando repetidamente seus dígitos

//Como fazer isso recursivamente? Eu preciso somar todos os dígitos e adicionar 1 ao contador

def somarDigitos(x: Int): Int = {
  if (x<0) somarDigitos(-x)
  else if (x==0) 0
  else (x%10) + (somarDigitos(x/10))
}

def calcularPersistencia(y: Int): Int = {
  def persistir(y: Int, cont: Int): Int = {
    if (y<10) cont //se n já é um dígito único, retorna o contador
    else persistir(somarDigitos(y), cont +1)
  }
  persistir(y,0)
}

object mainq3r{
  def main(args: Array[String]): Unit = {
    println(calcularPersistencia(39))
  }
}