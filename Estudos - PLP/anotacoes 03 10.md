Correção de questões:
Palavras reservadas vs palavras chave:
Reservadas são as instanciadas pela lp e não podem ser usadas como identificadores (ex. int while), enquanto as palavras chave são as mesmas possivelmente, mas podem ser usadas para outras coisas (ex. int int)

escopo estatico vs dinamico:
O escopo dinamico depende da ordem da chamada, enquanto o estatico define a visibilidade das variaveis em tempo de compilação

Tempos em que vinculações podem acontecer
Os 6 se dividem em 3 grandes,

implementacao da linguagem
criação do programa
execucao do programa

vantagem das constantes nomeadas:
ajuda a entender melhor o código, como ver x<50 é mais dificil doq x<qtd_funcionarios
Serve para trazer semantica ao valor literal

vantagens da vinculação dinamica de tipos:

Vinculação estática vs dinamica:
Estatica - compilação
dinamica - execucao

Escopo estatico vs dinamico:


**TIPOS DE DADOS**

Tipo de dado **PRIMITIVO** é aquele que nao é definido em termo de nenhum outro tipo, como se fossem os tipos base
- numeros inteiros - shortint/tinyint,int/integer,longint/bigint (signed/unsigned)

- ponto flutuante (repr. de numeros reais/fracionados) - normalmente tem dois tamanhos de acordo com o tipo do dado, sendo precisao simples e precisao dupla (float e double) isando o padrão IEEE Floating-Point Standard 754

- Decimal - numero fixo de digitos decimais
- Booleano - Em C, zero é considerado falso e qualquer outra coisa é considerada verdadeira, entretanto geralmente elas ocupam um byte, já que essa é a unidade convencional de trabalho
- Caractere - codificacões numericas (ASCII, UTF-8, UTF-16, UNICODE, etc)

# Tipo de dado string

Questoes de projeto:
- Algumas LPs tratam como um tipo primitivo (java) e outras como um array de caracteres(C++, pascal, Ada)
- Tamanho estático ou dinamico? é possivel fazer ele ser os dois ema lgumas linguagens, onde eles sempre sao criados estaticamente mas quando eu precisar modificar esse dado ele realoca num novo espaço de memoria, entretanto é possivel fazer nas abordagens estática:(para acessar com o mesmo tempo qualquer caracter da sequencia) ou de forma dinamica (que permite que os dados sejam modificados após instanciados)
## Operações comuns:
- Substring, concat, camparação, tamanho, quantidade de caracteres, finalização da cadeia, caracteres de escape
# Tipo de dado definido pelo usuario

- Aqueles cuja faixa de valores podem ser facilmente associada ao conjunto dos numeros inteiros positivos
- Aumenta a legibilidade e confiabilidade, já que nao permite valores fora dos especificados, 
- ***Tipo Enumeração***:
		Ex. em ADA: Type DIAS is (Seg, Ter, Qua, Qui, Sex....);
		Ex, em Pascal: type tipocor = (vermelho, azul, verde, amarelo);
		Em java: public enum tipocor{...}
		Podem ser usados como variaveis de laços, switch....case e até comparado de forma relacional pelo posicionamente