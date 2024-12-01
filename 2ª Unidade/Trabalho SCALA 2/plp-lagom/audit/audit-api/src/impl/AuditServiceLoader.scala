import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuditServiceLoader {

    private static final Logger logger = LoggerFactory.getLogger(CarregadorDeEntidades.class);

    // Map para armazenar os dados processados em memória
    private final Map<String, List<String>> cacheDeEntidades = new HashMap<>();

    /**
     * Carrega entidades em cache com base em dados fornecidos.
     *
     * @param tipo Tipo da entidade a ser carregada.
     * @param entidades Lista de entidades.
     */
    public void carregarEntidades(String tipo, List<String> entidades) {
        logger.info("Carregando entidades do tipo: {}", tipo);

        // Simula o carregamento das entidades no cache
        cacheDeEntidades.put(tipo, entidades);

        logger.info("{} entidades carregadas no cache para o tipo {}", entidades.size(), tipo);
    }

    /**
     * Obtém entidades do cache com base no tipo.
     *
     * @param tipo Tipo da entidade a buscar.
     * @return Lista de entidades ou null se não estiver no cache.
     */
    public List<String> obterEntidades(String tipo) {
        logger.info("Buscando entidades do tipo: {}", tipo);
        return cacheDeEntidades.get(tipo);
    }

    /**
     * Remove um tipo de entidade do cache.
     *
     * @param tipo Tipo da entidade a ser removida.
     */
    public void removerEntidades(String tipo) {
        logger.info("Removendo entidades do tipo: {}", tipo);
        cacheDeEntidades.remove(tipo);
    }
}
