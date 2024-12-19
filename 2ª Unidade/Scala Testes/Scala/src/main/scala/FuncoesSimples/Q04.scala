package FuncoesSimples

class Temperature(val temp: Double){}

def celsiusToFahrenheit(x:Double): Double = (x * 1.8) + 32
object Q04 {
  def main(array: Array[String]): Unit = {
    val temp = new Temperature(20.0)
    println(celsiusToFahrenheit(temp.temp))
  }
}
