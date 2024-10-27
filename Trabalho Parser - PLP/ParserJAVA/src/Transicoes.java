import java.util.List;

public class Transicoes {

    private List<Token> tokens; // Lista de tokens da entrada
    private int indiceTokenAtual; // Índice do token atual

    // Inicializa a lista de tokens ao começar o processamento de uma nova entrada
    public void iniciarTokenizacao(String entrada) {
        Token tokenizador = new Token("", "");
        this.tokens = tokenizador.tokenizar(entrada);
        this.indiceTokenAtual = 0; // Reseta o índice para o primeiro token
    }

    // Retorna o próximo token da lista
    private Token getProximoToken() {
        if (indiceTokenAtual < tokens.size()) {
            return tokens.get(indiceTokenAtual++);
        } else {
            return new Token("EOF", ""); // Retorna um token especial de fim de arquivo
        }
    }

    // Transição principal que chama 'processarExpressao' quando necessário
    public Estado transitar(Estado estado, Token token) {
        switch (estado) {
            case q0:
                if (token.getValor().equals("#")) {
                    return Estado.q1;
                } else if (token.getValor().equals("int") || token.getValor().equals("float")) {
                    return Estado.q3;
                } else if (token.getValor().equals("print")) {
                    return processarComandoPrint(Estado.q8, token);
                } else if (token.getValor().equals("if")) {
                    return Estado.q12; // Vai diretamente para o estado q12 para processar o bloco if
                } else if (token.getValor().equals("while")) {
                    return processarBlocoWhile(token);
                } else if (Character.isLetter(token.getValor().charAt(0))) {
                    return Estado.q5;
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0;
                } else if (token.getTipo().equals("Comentário")) {
                    return Estado.q0;
                } else {
                    return Estado.qErro;
                }

            case q1: // Comentário
                return Estado.q2;

            case q3: // Declaração de variável
                // Mantém o loop enquanto o token atual é um identificador de variável
                while (Character.isLetter(token.getValor().charAt(0))) {
                    token = getProximoToken();

                    // Verifica se chegou ao fim da declaração (ponto e vírgula ou quebra de linha)
                    if (token.getValor().equals(";") || token.getValor().equals("\n")) {
                        return Estado.q0; // Finaliza a lista de variáveis
                    }

                    // Verifica se o próximo token não é um identificador válido
                    else if (!Character.isLetterOrDigit(token.getValor().charAt(0))) {
                        return Estado.qErro; // Se não for uma letra ou dígito, gera erro
                    }
                    logTokensProcessados(token);
                }
                break;

            case q4: // Após identificar a variável
                if (token.getValor().equals("=")) {
                    return Estado.q6; // Atribuição
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0; // Declaração de variável sem atribuição
                } else {
                    return Estado.qErro; // Qualquer outro token gera erro
                }

            case q5: // Identificador de variável em expressão
                if (token.getValor().equals("=")) {
                    return Estado.q6;
                } else if (token.getTipo().equals("Operador") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7;
                } else if (token.getValor().equals(";") || token.getValor().equals("\n")) {
                    return Estado.q0;
                } else if (token.getValor().equals(")")) {
                    return Estado.q14;
                } else {
                    return Estado.qErro;
                }

            case q6: // Após receber "=" (expressão de atribuição)
                return Estado.q7;

            case q7: // Expressão matemática ou lógica
                if (token.getTipo().equals("Número") || token.getTipo().equals("Identificador")) {
                    return Estado.q7;
                } else if (token.getTipo().equals("Operador") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7;
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0;
                }
                break;

            case q8: // Comando print
                return processarComandoPrint(estado, token);

            case q12: // Comando if
                if (token.getValor().equals("(")) {
                    return Estado.q13;
                }
                break;

            case q13: // Dentro da condição do if
                return processarExpressaoAte(estado, token, ")");

            case q14: // Após a condição do if
                if (token.getValor().equals("{")) {
                    return Estado.q15;
                }
                break;

            case q15: // Bloco if
                return processarExpressaoAte(estado, token, "}");

            case q16: // Após fechamento do bloco if
                if (token.getValor().equals("else")) {
                    return Estado.q17;
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0;
                }
                break;

            case q17: // Bloco else
                if (token.getValor().equals("{")) {
                    return Estado.q18;
                }
                break;

            case q18: // Instruções do bloco else
                return processarExpressaoAte(estado, token, "}");

            default:
                return Estado.qErro;
        }
        return Estado.qErro;
    }

    // Função central que processa blocos de código, incluindo if, else e while
    private Estado processarExpressao(Estado estadoAtual, Token token) {
        Estado estadoInicial = estadoAtual;

        while (true) {
            if (token.getValor().equals("if")) {
                estadoAtual = processarBlocoIf(token);
            } else if (token.getValor().equals("while")) {
                estadoAtual = processarBlocoWhile(token);
            } else if (token.getValor().equals("else")) {
                estadoAtual = processarBlocoElse(token);
            } else if (token.getValor().equals("{")) {
                estadoAtual = processarExpressao(estadoAtual, getProximoToken());
            } else if (token.getValor().equals("}")) {
                return Estado.q0;
            } else {
                estadoAtual = transitar(estadoAtual, token);
            }

            if (estadoAtual == Estado.qErro) {
                return Estado.qErro;
            }
            token = getProximoToken();
        }
    }

    // Função auxiliar para processar bloco if
    private Estado processarBlocoIf(Token token) {
        Estado estadoAtual = Estado.q12;

        if (token.getValor().equals("(")) {
            estadoAtual = processarExpressaoAte(estadoAtual, token, ")");
            if (estadoAtual != Estado.qErro) {
                estadoAtual = processarExpressao(estadoAtual, getProximoToken());
            }
        }
        return estadoAtual;
    }

    // Função auxiliar para processar bloco else
    private Estado processarBlocoElse(Token token) {
        Estado estadoAtual = Estado.q17;

        if (token.getValor().equals("{")) {
            return processarExpressao(estadoAtual, getProximoToken());
        }
        return Estado.qErro;
    }

    // Função auxiliar para processar bloco while
    private Estado processarBlocoWhile(Token token) {
        Estado estadoAtual = Estado.q23;

        if (token.getValor().equals("(")) {
            estadoAtual = processarExpressaoAte(estadoAtual, token, ")");
            if (estadoAtual != Estado.qErro) {
                estadoAtual = processarExpressao(estadoAtual, getProximoToken());
            }
        }
        return estadoAtual;
    }

    // Processa tokens até encontrar o delimitador específico
    private Estado processarExpressaoAte(Estado estadoAtual, Token token, String delimitador) {
        while (!token.getValor().equals(delimitador)) {
            estadoAtual = transitar(estadoAtual, token);
            if (estadoAtual == Estado.qErro) {
                return Estado.qErro;
            }
            token = getProximoToken();
        }
        return estadoAtual;
    }

    // Processa o comando print
    private Estado processarComandoPrint(Estado estadoInicial, Token token) {
        Estado estadoAtual = estadoInicial;

        if (token.getValor().equals("(")) {
            estadoAtual = Estado.q9;
        } else if (Character.isLetter(token.getValor().charAt(0))) {
            estadoAtual = Estado.q10;
        }

        while (estadoAtual != Estado.q11 && estadoAtual != Estado.qErro) {
            estadoAtual = transitar(estadoAtual, token);
            if (estadoAtual == Estado.q11)
                break;
        }

        return estadoAtual == Estado.q11 ? Estado.q0 : Estado.qErro;
    }

    public boolean processar(String entrada) {
        Estado estadoAtual = Estado.q0;
        Estado[] estadosFinais = { Estado.q0 };

        // Inicializa a tokenização para a entrada fornecida
        iniciarTokenizacao(entrada);

        while (indiceTokenAtual < tokens.size()) {
            Token token = getProximoToken();

            // Log para ver qual token está sendo processado
            logTokensProcessados(token);

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

    public void logTokensProcessados(Token token){
        System.out.println("\n=== Processando Token ===");
        System.out.println("Token: " + token.getValor() + ", Tipo: " + token.getTipo());
        System.out.println("=========================");
    }
}
