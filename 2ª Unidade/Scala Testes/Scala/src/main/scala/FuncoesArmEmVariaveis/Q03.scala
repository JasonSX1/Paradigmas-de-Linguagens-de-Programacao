package FuncoesArmEmVariaveis

object Q03 extends App {
    val paraMaiscula = (p: String) => p.toUpperCase()
    val contaPalavras = (p: String) => p.split("").length
    val textoReverso = (p: String) => p.reverse

    val textoOriginal = "Scala é interessante"

    println(s"Maiusculas: ${paraMaiscula(textoOriginal)}")
    println(s"Temperatura convertida: ${contaPalavras(textoOriginal)}")
    println(s"Temperatura convertida: ${textoReverso(textoOriginal)}")

}
