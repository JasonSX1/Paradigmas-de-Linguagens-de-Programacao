import java.util.List;

public class Transicoes {

    // Transição entre os estados com base no token atual
    public Estado transitar(Estado estado, Token token) {
        switch (estado) {
            case q0:
                if (token.getValor().equals("#")) {
                    return Estado.q1; // Início de linha de comentário
                } else if (token.getValor().equals("int") || token.getValor().equals("float")) {
                    return Estado.q3; // Declaração de variável
                } else if (token.getValor().equals("print")) {
                    return Estado.q8; // Comando print
                } else if (token.getValor().equals("if")) {
                    return Estado.q12; // Comando if
                } else if (token.getValor().equals("while")) {
                    return Estado.q23; // Comando while
                } else if (Character.isLetter(token.getValor().charAt(0))) {
                    return Estado.q5; // Identificador de variável
                }
                break;

            case q1: // Comentário
                return Estado.q2; // Aceita qualquer texto após "#"

            case q3: // Declaração de variável
                if (Character.isLetter(token.getValor().charAt(0))) {
                    return Estado.q4; // Lista de variáveis (nome da variável)
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

            case q5: // Identificador de variável
                if (token.getValor().equals("=")) {
                    return Estado.q6; // Atribuição de valor à variável
                } else if (token.getTipo().equals("Operador") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7; // Parte de uma expressão com variável
                } else if (token.getValor().equals(";")) {
                    return Estado.q15; // Fim da instrução
                } else if (token.getValor().equals("\n")) {
                    return Estado.q15; // Continua processando dentro do bloco
                } else if (token.getValor().equals(")")) {
                    return Estado.q14; // Fecha a expressão condicional do if
                } else {
                    return Estado.qErro; // Qualquer outro token inesperado gera erro
                }

            case q6: // Após receber "=" (expressão de atribuição)
                return Estado.q7; // Recebe uma expressão (matemática ou lógica)

            case q7: // Expressão matemática ou lógica
                if (token.getTipo().equals("Número") || token.getTipo().equals("Identificador")) {
                    return Estado.q7; // Continua recebendo a expressão
                } else if (token.getTipo().equals("Operador")) {
                    return Estado.q7; // Continua para um operador matemático
                } else if (token.getTipo().equals("OperadorLogico")) {
                    return Estado.q7; // Continua para um operador lógico
                } else if (token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7; // Continua para um operador de comparação
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0; // Final da atribuição ou expressão
                }
                break;

            case q8: // Comando print
                if (token.getValor().equals("(")) {
                    return Estado.q9; // Parêntese de abertura
                } else if (Character.isLetter(token.getValor().charAt(0))) {
                    return Estado.q10; // Print sem parênteses
                }
                break;

            case q9:
                return Estado.q10; // Recebe a expressão a ser impressa

            case q10:
                if (token.getValor().equals(")")) {
                    return Estado.q11; // Parêntese de fechamento
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0; // Print sem parênteses, finalizado por quebra de linha
                }
                break;

            case q11:
                if (token.getValor().equals("\n")) {
                    return Estado.q0; // Final do comando print
                }
                break;

            case q12: // Comando if
                if (token.getValor().equals("(")) {
                    return Estado.q13; // Início da condição do if
                }
                break;

            case q13: // Processamento da expressao matematica ou logica do if
                if (token.getTipo().equals("Número") || token.getTipo().equals("Identificador")) {
                    return Estado.q13; // Continua recebendo a expressão
                } else if (token.getTipo().equals("Operador")) {
                    return Estado.q13; // Continua para um operador matemático
                } else if (token.getTipo().equals("OperadorLogico")) {
                    return Estado.q13; // Continua para um operador lógico
                } else if (token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q13; // Continua para um operador de comparação
                } else if (token.getValor().equals("\n")) {
                    return Estado.q13; // Possivel quebra de linha em bloco de instrução do if, deve continuar
                                       // recebendo instruções
                } else if (token.getValor().equals(")")) {
                    return Estado.q14; // Fecha a condição e vai para o próximo estado
                } else {
                    return Estado.qErro; // Qualquer outro token fora da expressão gera erro
                }

            case q14: // Após a condição do if, verifica se abre o bloco com "{"
                if (token.getValor().equals("{")) {
                    return Estado.q15; // Início do bloco if
                }
                break;

            case q15: // Insruções dentro do bloco if
                // Processa qualquer expressão ou comando dentro do bloco
                if (token.getValor().equals("print")) {
                    //Criar um subprograma de comando de print que volta praqui depois que terminar
                    return Estado.q8; // Comando print
                } else if (token.getValor().equals("if")) {
                    //Criar um subprograma de comando if para suporte de ifs aninhados e que volta praqui depois que terminar
                    return Estado.q12; // Comando if aninhado
                } else if (token.getValor().equals("while")) {
                    return Estado.q23; // Comando while aninhado
                } else if (token.getTipo().equals("Operador") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7; // Expressão matemática ou lógica
                } else if (token.getValor().equals("}")) {
                    return Estado.q16; // Fecha o bloco if
                } else if (token.getValor().equals("{")) {
                    return Estado.q15; // Processa início de um bloco aninhado
                } else if (token.getTipo().equals("Número")) {
                    return Estado.q7; // Processa números dentro do bloco
                } else if (token.getValor().equals(";")) {
                    return Estado.q15; // Continua no mesmo estado após finalizar uma instrução com ;
                } else if (token.getValor().equals("\n")) {
                    return Estado.q15; // Ignora quebras de linha e continua processando o bloco
                } else {
                    return Estado.qErro; // Qualquer outro token inesperado gera erro
                }

            case q16: // Após fechamento do bloco if
                if (token.getValor().equals("else")) {
                    return Estado.q17; // Início do bloco else
                } else if (token.getValor().equals("\n")) {
                    return Estado.q0; // Fim do comando if sem else
                }
                break;

            case q17: // Início do bloco else
                if (token.getValor().equals("{")) {
                    return Estado.q18; // Início do bloco else
                }
                break;

            case q18: // Instruções dentro do bloco else
                // Processa qualquer expressão ou comando dentro do bloco else
                if (Character.isLetter(token.getValor().charAt(0))) {
                    return Estado.q5; // Identificador de variável ou chamada de função
                } else if (token.getValor().equals("print")) {
                    return Estado.q8; // Comando print
                } else if (token.getValor().equals("if")) {
                    return Estado.q12; // Comando if aninhado
                } else if (token.getValor().equals("while")) {
                    return Estado.q23; // Comando while aninhado
                } else if (token.getTipo().equals("Operador") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorComparacao")) {
                    return Estado.q7; // Expressão matemática ou lógica
                } else if (token.getValor().equals("}")) {
                    return Estado.q0; // Fim do bloco else
                } else if (token.getTipo().equals("Número")) {
                    return Estado.q7; // Processa números dentro do bloco
                } else if (token.getValor().equals(";")) {
                    return Estado.q18; // Continua no mesmo estado após finalizar uma instrução com ;
                } else {
                    return Estado.qErro; // Qualquer outro token inesperado gera erro
                }

            case q19: // Instruções dentro do bloco else
                return Estado.q20; // Processa as instruções do bloco else

            case q20: // Verifica se o bloco else termina com "}"
                if (token.getValor().equals("}")) {
                    return Estado.q21; // Fim do bloco else
                }
                break;

            case q22: // Comando while, verifica se abre com "("
                if (token.getValor().equals("(")) {
                    return Estado.q23; // Condição do while
                }
                break;

            case q23: // Processa a condição do while (similar ao if)
                if (token.getTipo().equals("Identificador") || token.getTipo().equals("Número")) {
                    return Estado.q23; // Continua processando a expressão (variável ou número)
                } else if (token.getTipo().equals("OperadorComparacao") || token.getTipo().equals("OperadorLogico")
                        || token.getTipo().equals("OperadorMatematico")) {
                    return Estado.q23; // Continua processando operadores (comparação, lógicos ou matemáticos)
                } else if (token.getValor().equals(")")) {
                    return Estado.q24; // Fecha a condição e vai para o próximo estado
                } else {
                    return Estado.qErro; // Qualquer outro token fora da expressão gera erro
                }

            case q24: // Após a condição do while, verifica se abre o bloco com "{"
                if (token.getValor().equals("{")) {
                    return Estado.q25; // Início do bloco while
                }
                break;

            case q25: // Instruções dentro do bloco while
                return Estado.q26; // Processa as instruções do bloco while

            case q26: // Verifica se o bloco while termina com "}"
                if (token.getValor().equals("}")) {
                    return Estado.q27; // Fim do bloco while
                }
                break;

            default:
                return Estado.qErro; // Estado de erro para tokens inesperados
        }
        return Estado.qErro; // Estado de erro
    }

    public boolean processar(String entrada) {
        Estado estadoAtual = Estado.q0;
        Estado[] estadosFinais = { Estado.q0 };

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
