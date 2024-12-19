package FuncoesArmEmVariaveis

object Q02 extends App{
  val celsiusParaFahrenheit = (c: Double) => (c * 1.8) + 32
  val fahrenheitParaCelsius = (f: Double) => (f - 32)/1.8
  val celsiusParaKelvin = (c: Double) => c + 273.15

  val temperatura = 30
  val conversao = 1

  val resultado = conversao match{
    case 1 => celsiusParaFahrenheit(temperatura)
    case 2 => fahrenheitParaCelsius(temperatura)
    case 3 => celsiusParaKelvin(temperatura)
  }

  println(s"Temperatura convertida: ${resultado}")
}
