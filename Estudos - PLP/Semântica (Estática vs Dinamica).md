SEMÂNTICA ESTÁTICA refere-se às regras que podem ser verificadas antes da execução do programa, geralmente durante o processo de compilação.

Tipos de problemas que lida:
-Verificação de tipos: Certificar que as operações realizadas no programa estão sendo feitas entre tipos compatíveis. Por exemplo, verificar que você não está tentando somar uma string com um número inteiro.
-Verificação de escopo: Garantir que as variáveis, funções ou objetos são usados dentro de seu escopo correto.
-Declaração de variáveis: Checar se todas as variáveis foram declaradas antes de serem utilizadas.
-Análise de sobrecarga de operadores: Determinar qual versão de um operador ou função deve ser usada, de acordo com os tipos dos argumentos.
-Constantes: Verificar que as constantes não são modificadas durante a execução do programa.
-Fechamento bem formado de bloco: Em Ada o end de um subprograma é seguido de um nome, que deve coincidir com o nome do subprograma. 
function Soma(A, B : Integer) return Integer is
   begin
      return A + B;
   end Soma;

-------------------------------
SEMÂNTICA DINÂMICA trata das regras que só podem ser verificadas durante a execução do programa, porque dependem do comportamento e dos dados que estão sendo manipulados no momento, em tempo de execução (runtime).

Tipos de problemas que lida:
Divisão por zero: Variável que só é conhecida durante a execução do programa pode ser usada numa divisão. Se o valor for zero, ocorrerá um erro de tempo de execução, pois não é possível dividir por zero.
Acesso fora dos limites de um array (out of bounds): Um programa tenta acessar o índice 5 de um array que só tem 3 elementos, ele vai tentar acessar uma área de memória fora dos limites do array. O tamanho do array pode ser conhecido somente durante a execução, e o erro ocorre quando o programa tenta acessar um índice inválido.
-Alocação e gerenciamento de memória: Se você tentar acessar uma variável ou objeto que não foi inicializado (ou que foi deletado da memória), o programa vai falhar. Mesmo que o código pareça correto, o erro só vai acontecer se o programa tentar acessar essa memória inválida enquanto está sendo executado.
-Referência a um objeto inexistente (ou referência nula): Em linguagens orientadas a objetos, um programa pode tentar acessar um método ou atributo de um objeto que não foi criado ou foi deletado (null pointer exception). O compilador pode até garantir que o objeto está sendo tratado corretamente, mas, em tempo de execução, se o objeto não existir mais ou não tiver sido inicializado, o programa vai falhar.
-Estouro de pilha (stack overflow): Pode acontecer em chamadas recursivas mal controladas, onde o programa continua chamando a si mesmo até consumir toda a memória da pilha.O compilador não consegue prever quantas vezes a função será chamada. O erro acontece apenas durante a execução, se a recursão não parar corretamente.
-Controle de fluxo: Verificar o comportamento dinâmico de loops, que dependem de valores calculados em tempo de execução.
-Exceções não tratadas: Um programa realiza uma operação que pode falhar (como abrir um arquivo que não existe) e não há um tratamento adequado para esse erro, o programa pode falhar. A existência de certos erros (como arquivos ausentes ou conexões de rede que falham) só é detectada quando o programa tenta fazer essas operações em tempo de execução.
-Concorrência e acesso a recursos compartilhados: Em programas que têm múltiplas threads ou processos, diferentes partes do programa podem tentar acessar o mesmo recurso (como um arquivo ou uma variável) ao mesmo tempo, causando conflitos. O comportamento das threads ou processos pode ser imprevisível e, em tempo de execução, podem ocorrer condições de corrida ou bloqueios.