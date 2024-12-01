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
        return "Token [" + "tipo='" + tipo + '\'' + ", valor='" + valor + '\'' + ']';
    }

    public List<Token> tokenizar(String entrada) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < entrada.length()) {
            char charAtual = entrada.charAt(i);

            if (charAtual == '\n') { // Captura a quebra de linha como token
                tokens.add(new Token("Quebra de linha", "\n"));
                i++;
                continue;
            }

            if (Character.isWhitespace(charAtual)) {
                i++; // Ignora espaços em branco
                continue;
            }

            if (charAtual == '#') { // Comentário
                StringBuilder comentario = new StringBuilder();
                comentario.append(charAtual); // Adiciona o caractere '#' no início

                // Captura o conteúdo do comentário até o final da linha
                while (i < entrada.length() && entrada.charAt(i) != '\n') {
                    comentario.append(entrada.charAt(i));
                    i++;
                }

                // Adiciona o token de comentário
                tokens.add(new Token("Comentário", comentario.toString()));
            } else if (Character.isLetter(charAtual)) { // Identificador ou palavra-chave
                StringBuilder identificador = new StringBuilder();
                while (i < entrada.length() && Character.isLetterOrDigit(entrada.charAt(i))) {
                    identificador.append(entrada.charAt(i));
                    i++;
                }
                String valorIdentificador = identificador.toString();

                if (valorIdentificador.equals("int") || valorIdentificador.equals("float") ||
                        valorIdentificador.equals("if") || valorIdentificador.equals("else") ||
                        valorIdentificador.equals("while") || valorIdentificador.equals("print")) {
                    tokens.add(new Token("Palavra-chave", valorIdentificador));
                } else {
                    tokens.add(new Token("Identificador", valorIdentificador));
                }
            } else if (Character.isDigit(charAtual)) { // Número
                StringBuilder numero = new StringBuilder();
                while (i < entrada.length() && Character.isDigit(entrada.charAt(i))) {
                    numero.append(entrada.charAt(i));
                    i++;
                }
                tokens.add(new Token("Número", numero.toString()));
            }

            // Operadores lógicos e de comparação
            else if (entrada.startsWith("&&", i)) {
                tokens.add(new Token("OperadorLógico", "&&"));
                i += 2;
            } else if (entrada.startsWith("||", i)) {
                tokens.add(new Token("OperadorLógico", "||"));
                i += 2;
            } else if (entrada.startsWith("!", i)) {
                tokens.add(new Token("OperadorLógico", "!"));
                i++;
            } else if (entrada.startsWith("==", i)) {
                tokens.add(new Token("OperadorComparacao", "=="));
                i += 2;
            } else if (entrada.startsWith("!=", i)) {
                tokens.add(new Token("OperadorComparacao", "!="));
                i += 2;
            } else if (entrada.startsWith(">=", i)) {
                tokens.add(new Token("OperadorComparacao", ">="));
                i += 2;
            } else if (entrada.startsWith("<=", i)) {
                tokens.add(new Token("OperadorComparacao", "<="));
                i += 2;
            } else if (charAtual == '>') {
                tokens.add(new Token("OperadorComparacao", ">"));
                i++;
            } else if (charAtual == '<') {
                tokens.add(new Token("OperadorComparacao", "<"));
                i++;
            }

            // Símbolos especiais
            else if ("=();{}".indexOf(charAtual) != -1) {
                tokens.add(new Token("Símbolo", String.valueOf(charAtual)));
                i++;
            }

            // Operadores matemáticos
            else if ("+-*/%".indexOf(charAtual) != -1) {
                tokens.add(new Token("Operador", String.valueOf(charAtual)));
                i++;
            } else {
                i++; // Ignora caracteres inválidos
            }
        }

        // Log para imprimir a lista de tokens gerados
        System.out.println("Tokens gerados:");
        for (Token token : tokens) {
            System.out.println(token);
        }

        return tokens;
    }
}
