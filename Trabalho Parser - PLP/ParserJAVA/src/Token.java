import java.util.List;
import java.util.ArrayList;

public class Token {
    private String tipo;
    private String valor;

    public Token(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tipo='" + tipo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
    
    public List<Token> tokenizar(String entrada) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        
        while (i < entrada.length()) {
            char charAtual = entrada.charAt(i);

            if (Character.isWhitespace(charAtual)) {
                i++;  // Ignora espaços em branco
                continue;
            }

            if (charAtual == '#') {  // Comentário
                // Ignora o comentário até o final da linha
                while (i < entrada.length() && entrada.charAt(i) != '\n') {
                    i++;
                }
            } else if (Character.isLetter(charAtual)) {  // Identificador ou palavra-chave
                StringBuilder identificador = new StringBuilder();
                while (i < entrada.length() && Character.isLetterOrDigit(entrada.charAt(i))) {
                    identificador.append(entrada.charAt(i));
                    i++;
                }
                String valorIdentificador = identificador.toString();
                
                // Determina se é uma palavra-chave ou identificador
                if (valorIdentificador.equals("int") || valorIdentificador.equals("float") ||
                    valorIdentificador.equals("if") || valorIdentificador.equals("else") || 
                    valorIdentificador.equals("while") || valorIdentificador.equals("print")) {
                    tokens.add(new Token("Palavra-chave", valorIdentificador));
                } else {
                    tokens.add(new Token("Identificador", valorIdentificador));
                }
            } else if (Character.isDigit(charAtual)) {  // Número
                StringBuilder numero = new StringBuilder();
                while (i < entrada.length() && Character.isDigit(entrada.charAt(i))) {
                    numero.append(entrada.charAt(i));
                    i++;
                }
                tokens.add(new Token("Número", numero.toString()));
            } else if ("=();{}".indexOf(charAtual) != -1) {  // Símbolos específicos
                tokens.add(new Token("Símbolo", String.valueOf(charAtual)));
                i++;
            } else {
                i++;  // Ignora caracteres inválidos (ou lança erro dependendo do caso)
            }
        }
        return tokens;
    }
}
