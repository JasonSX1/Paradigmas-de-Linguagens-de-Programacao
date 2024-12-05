package com.example.auditservice.impl

import akka.actor.typed.ActorSystem
import akka.stream.Materializer
import javax.inject.Inject
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

class AuditServiceImpl @Inject()(loader: AuditServiceLoader)(implicit system: ActorSystem[_], mat: Materializer, ec: ExecutionContext) {

  private val logger = LoggerFactory.getLogger(classOf[AuditServiceImpl])

  def gerarRelatorio(tipo: String): Future[String] = {
    logger.info(s"Iniciando a geração do relatório para o tipo: $tipo")

    val entidades = loader.obterEntidades(tipo)

    if (entidades.isEmpty) {
      logger.warn(s"Nenhuma entidade encontrada no cache para o tipo: $tipo")
      Future.successful("Nenhuma entidade disponível para gerar o relatório.")
    } else {
      val relatorio = entidades.mkString(s"Relatório para o tipo $tipo:\n", "\n", "")
      logger.info(s"Relatório gerado com sucesso para o tipo: $tipo")
      Future.successful(relatorio)
    }
  }

  def limparCache(tipo: String): Unit = {
    logger.info(s"Limpando cache para o tipo: $tipo")
    loader.removerEntidades(tipo)
    logger.info(s"Cache limpo para o tipo: $tipo")
  }
}
