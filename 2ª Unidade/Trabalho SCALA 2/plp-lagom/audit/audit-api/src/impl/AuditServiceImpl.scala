import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ServicoDeRelatorioImpl.class);

    @Autowired
    private CarregadorDeEntidades carregadorDeEntidades;

    /**
     * Gera um relatório baseado nas entidades carregadas no cache.
     *
     * @param tipo Tipo das entidades a serem usadas para o relatório.
     * @return Mensagem do relatório gerado.
     */
    public String gerarRelatorio(String tipo) {
        logger.info("Iniciando a geração do relatório para o tipo: {}", tipo);

        // Busca as entidades carregadas no cache
        List<String> entidades = carregadorDeEntidades.obterEntidades(tipo);

        if (entidades == null || entidades.isEmpty()) {
            logger.warn("Nenhuma entidade encontrada no cache para o tipo: {}", tipo);
            return "Nenhuma entidade disponível para gerar o relatório.";
        }

        // Simulação da geração do relatório
        StringBuilder relatorio = new StringBuilder("Relatório para o tipo ").append(tipo).append(":\n");
        entidades.forEach(entidade -> relatorio.append("- ").append(entidade).append("\n"));

        logger.info("Relatório gerado com sucesso para o tipo: {}", tipo);
        return relatorio.toString();
    }

    /**
     * Limpa o cache para um tipo específico de entidade.
     *
     * @param tipo Tipo das entidades a serem removidas do cache.
     */
    public void limparCache(String tipo) {
        logger.info("Limpando cache para o tipo: {}", tipo);
        carregadorDeEntidades.removerEntidades(tipo);
        logger.info("Cache limpo para o tipo: {}", tipo);
    }
}
