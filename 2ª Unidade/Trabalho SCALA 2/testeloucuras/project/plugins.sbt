// Configuração de repositórios
resolvers += Resolver.sonatypeRepo("public")

// Plugin para o Lagom Framework
addSbtPlugin("com.lightbend.lagom" % "sbt-lagom" % "1.6.7")

// Plugin para empacotar aplicações nativas
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.5.2")
