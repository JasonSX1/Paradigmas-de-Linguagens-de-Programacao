import java.util.List;
public class Transicoes {

    // Transição entre os estados com base no token atual
    public Estado transitar(Estado estado, Token token) {
        switch (estado) {
            case q0:
                if (token.getValor().equals("#")) {
                    return Estado.q1;  // Início de linha de comentário
                } else if (token.getValor().equals("int") || token.getValor().equals("float")) {
                    return Estado.q3;  // Declaração de variável
                } else if (token.getValor().equals("print")) {
                    return Estado.q8;  // Comando print
                } else if (token.getValor().equals("if")) {
                    return Estado.q12; // Comando if
                } else if (token.getValor().equals("while")) {
                    return Estado.q23; // Comando while
                } else if (Character.isLetter(token.toString().charAt(0))) {
                    return Estado.q5;  // Identificador de variável
                }
                break;

            case q1:  // Comentário
                return Estado.q2;  // Aceita qualquer texto após "#"

            case q3:  // Declaração de variável
                if (Character.isLetter(token.toString().charAt(0))) {
                    return Estado.q4;  // Lista de variáveis
                }
                break;

            case q4:
                if (token.getValor().equals(";")) {
                    return Estado.q0;  // Final da declaração
                }
                break;

            case q5:  // Identificador de variável
                if (token.getValor().equals("=")) {
                    return Estado.q6;  // Atribuição
                }
                break;

            case q6:  // Após receber "="
                return Estado.q7;  // Expressão de atribuição

            case q7:
                if (token.getValor().equals(";")) {
                    return Estado.q0;  // Final da atribuição
                }
                break;

            case q8:  // Comando print
                if (token.getValor().equals("(")) {
                    return Estado.q9;  // Parêntese de abertura
                }
                break;

            case q9:
                return Estado.q10;  // Recebe a expressão a ser impressa

            case q10:
                if (token.getValor().equals(")")) {
                    return Estado.q11;  // Parêntese de fechamento
                }
                break;

            case q11:
                if (token.getValor().equals(";")) {
                    return Estado.q0;  // Final do comando print
                }
                break;

            case q12:  // Comando if
                if (token.getValor().equals("(")) {
                    return Estado.q13;  // Condição do if
                }
                break;

            case q13:
                return Estado.q14;  // Expressão da condição

            case q14:
                if (token.getValor().equals(")")) {
                    return Estado.q15;  // Fecha condição
                }
                break;

            case q15:
                if (token.getValor().equals("{")) {
                    return Estado.q16;  // Início do bloco if
                }
                break;

            case q16:
                return Estado.q17;  // Instrução dentro do bloco if

            case q17:
                if (token.getValor().equals("}")) {
                    return Estado.q18;  // Fim do bloco if
                }
                break;

            case q18:
                if (token.getValor().equals("else")) {
                    return Estado.q19;  // Início do bloco else
                }
                break;

            case q19:
                if (token.getValor().equals("{")) {
                    return Estado.q20;  // Início do bloco else
                }
                break;

            case q20:
                return Estado.q21;  // Instrução dentro do bloco else

            case q21:
                if (token.getValor().equals("}")) {
                    return Estado.q22;  // Fim do bloco else
                }
                break;

            case q23:  // Comando while
                if (token.getValor().equals("(")) {
                    return Estado.q24;  // Condição do while
                }
                break;

            case q24:
                return Estado.q25;  // Expressão da condição

            case q25:
                if (token.getValor().equals(")")) {
                    return Estado.q26;  // Fim da condição while
                }
                break;

            case q26:
                if (token.getValor().equals("{")) {
                    return Estado.q27;  // Início do bloco while
                }
                break;

            case q27:
                return Estado.q28;  // Instrução dentro do while

            case q28:
                if (token.getValor().equals("}")) {
                    return Estado.q29;  // Fim do bloco while
                }
                break;

            default:
                return Estado.qErro;  // Estado de erro se algo inesperado acontecer
        }
        return Estado.qErro;  // Estado de erro para qualquer token inesperado
    }

    public boolean processar(String entrada) {
        Estado estadoAtual = Estado.q0;
        Estado[] estadosFinais = {Estado.q0};

        Token tokenizador = new Token("", "");
        List<Token> tokens = tokenizador.tokenizar(entrada);
        
        for (Token token : tokens) {
            estadoAtual = transitar(estadoAtual, token);
            if (estadoAtual == Estado.qErro)
                return false;
        }

        // Verifica se terminou em estado final
        for (Estado estadoFinal : estadosFinais) {
            if (estadoAtual == estadoFinal)
                return true;
        }
        return false;
    }
}