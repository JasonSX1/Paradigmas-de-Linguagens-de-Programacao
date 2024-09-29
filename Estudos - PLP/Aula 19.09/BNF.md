Analise léxica, sintáxica e semântica

Analise léxica é pra verificar se tem alguma coisa escrita errado, enquanto a sintáxica serve para verificar se há erros de continuidade ou de ordem de leitura, e a semântica, que se divide em duas formas -  a estática consiste nos erros que são perceptíveis visualmente antes de rodar o código, e o outro tipo desta é a semântica dinâmica, que são erros que podem acontecer durante a execução do programa(como verificação de tipos, para verificar que os tipos de entrada numa operação de concatenação por exemplo, são compatíveis; ou ainda, detectar que uma instrução de um if está dentro do escopo necessário).

Mais informações em [[Semântica (Estática vs Dinamica)]]
[[Gramática de Atributos]]
[[BNF vs EBNF]]

Primeiro passo do analisador léxico é verificar se oq eu estou lendo faz parte do meu dicionário, como typedef, char, int, float.

à medida que vou lendo e identifico que oq eu li é uma palavra definida, aquilo vai compor uma unidade léxica, para identificar um erro sintáxico eu analiso por exemplo:

[if (IMC < 15.5)] - este é um código correto;
[fi (IMC < 15.5)] - Aqui está incorreto pq 'fi não é definido como uma palavra reservada, então meu programa interpreta como se ele fosse um identificador, mas um identificador não pode ser seguido de um símbolo de "(", configurando assim um erro de ordem léxica, e não sintática.

Um exemplo de erro léxico possível, é por ex. se um identificador for iniciado por um número, ou como no php, que um identificador precisa ser iniciado com um $, caso isso esteja ausente eu identifico um erro léxico.

Q. PRATICA -  como posso formar um numero inteiro simples, depois gerar uma gramatica que forma um numero inteiro com sinal (pos ou neg) numero de ponto flutuante

Meu código: 
`<int> ::= <PDigito> <digito>*`
`<PDigito> ::= [1-9]`
`<digito> ::= [0-9]`
`<intSinal> ::= <simbolo> <PDigito> <digito>*`
`<simbolo> ::= "+" | "-"`

Código do prof. para inteiro com sinal:

`<real> ::= <pos> | <neg>`
`<pos> ::= "0" | [1-9] [0-9]*`
`<neg> ::= "-" <pos>`

Código do prof. para números reais:

`<real> ::= <pos> | <neg>`
`<pos> ::= | [1-9] [0-9]* ("." [0-9]+)?`
`<neg> ::= "-" <pos>`


Código do prof. para inteiro sem sinal:

`<real> ::= <pos>`
`<pos> ::= "0" | [1-9] [0-9]*`

Q. PRATICA 2 - programa BNF para encontrar um identificador, esse identificador pode ser iniciado com underline ou com letras maiúsculas ou minúsculas e seguido por letras M ou m, underline ou numeros, cod do prof.

`<ident> ::= <inicial> <restante>`
`<inicial> ::= "_" | [a-z] | [A-Z]`
`<restante> ::= ("_" | [a-z] | [A-Z] | [0-9])*`


Q. PRATICA 3 - Programa para reconhecer números de telefone no seguinte padrão:

`DDI` - +XX (XX) 9 XXXX-XXXX
`DDD` - (XX) 9 XXXX-XXXX 

`<telefone> ::=  <ddi> | <ddd>`
`<ddd> ::= "(" [0-9] [0-9] ")" "9" <numero> "-" <numero>`
`<ddi> ::= "+" [0-9] [0-9] <ddd>`
`<numero> ::= [0-9] [0-9] [0-9] [0-9]`

Q. PRATICA 4 - RECONHECER DATA E HORA DD/MM/AA HH:MM:SS

`<data_hora> ::= <dia> "/" <mes> "/" <ano> " " <hora> ":" <minuto> ":" <segundo>`
`<dia> ::=    "0" [1-9] | [1-2] [0-9] | "3" [0-1]
`<mes> ::=    "0" [1-9] | "1" [0-2]`
`<ano> ::=    [0-1] [0-9] [0-9] [0-9] | "20" [0-1] [0-9] | "202" [0-4]`
`<hora> ::=    [0-1] [0-9] | "2" [0-3]`
`<minuto> ::=    [0-5] [0-9]`
`<segundo> ::=    [0-5] [0-9]`

Na hora de criar padrões BNF como este, iniciamos "instanciando" os campos com range máximo, depois limitamos ele, sendo essa uma foram mais facilitada de conseguir criar os tipos de dados limitados, como no campo de `<segundos>`, que deve ir de `00 a 59`, iniciamos instanciando com máximo range: `<segundo> ::= [0-9][0-9]` (neste caso é possível ir de 00 a 99 segundos), então pra criar a limitação que preciso, basta limitar o primeiro campo até o `5`, onde obtemos: `<segundo> ::=    [0-5] [0-9]`

Q. PRATICA 5 - RECONHECER UMA LISTA DE IDENTIFICADORES

Q. PRATICA 6 - RECONHECER INTEIRO OU FLOAT

.Expressão regular? - professor falou que a geração q ele tá fazendo é como se fosse uma geração regular gigante