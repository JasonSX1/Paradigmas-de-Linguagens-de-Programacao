import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {
    public static void main(String[] args) {
        String nomeArquivo = "C:\\Users\\Cliente\\Desktop\\IV Semestre\\Paradigmas-de-Linguagens-de-Programacao\\Trabalho Parser - PLP\\ParserJAVA\\src\\Programa1.plp"; // Path do arquivo de código-fonte
    
        StringBuilder entrada = new StringBuilder();
    
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                entrada.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }
    
        Transicoes transicoes = new Transicoes();
        boolean resultado = transicoes.processar(entrada.toString());
        System.out.println("Entrada " + (resultado ? "válida" : "inválida"));
    }
}
