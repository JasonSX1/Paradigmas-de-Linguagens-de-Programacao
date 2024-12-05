package com.example.auditservice.impl

import org.slf4j.LoggerFactory
import javax.inject.Singleton
import scala.collection.concurrent.TrieMap

@Singleton
class AuditServiceLoader {

  private val logger = LoggerFactory.getLogger(classOf[AuditServiceLoader])
  private val cacheDeEntidades = new TrieMap[String, Seq[String]]()

  def carregarEntidades(tipo: String, entidades: Seq[String]): Unit = {
    logger.info(s"Carregando entidades do tipo: $tipo")
    cacheDeEntidades.put(tipo, entidades)
    logger.info(s"${entidades.size} entidades carregadas no cache para o tipo $tipo")
  }

  def obterEntidades(tipo: String): Seq[String] = {
    logger.info(s"Buscando entidades do tipo: $tipo")
    cacheDeEntidades.getOrElse(tipo, Seq.empty)
  }

  def removerEntidades(tipo: String): Unit = {
    logger.info(s"Removendo entidades do tipo: $tipo")
    cacheDeEntidades.remove(tipo)
  }
}
