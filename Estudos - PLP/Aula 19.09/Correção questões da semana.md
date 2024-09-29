- Tipos de dados:
-Professor falou sobre record, é tipo um objeto simplificado, n tem herança, n tem polimorfismo ou visibilidade, consiste numa forma de ter um dado mais complexo q um dado primitivo;
- (Pegar ex. do Gchat), abaixo segue o ex. em C

typedef struct {
char Nome[50];
int Idade;
float Altura;
} Pessoa;

Isso já havia sido previsto na primeira linguagem (nome alemao)

q2 q ele mandou essa semana - A resposta correta é "compilacao independente de subprogramas/rotinas"

r. A compilação independente consiste num subprograma q faz uma execução em um arquivo à parte, e na hora de compilar eu preciso de um link editor, pra quando eu precisar de um código de um subprograma o link vai lá buscar essa info, como se fosse uma biblioteca, aqui é simplesmente uma função num arquivo separado, isso foi adc. no fortran 1, isso serve pra saber que cada trecho tá funcionando bem, entao quando eu compilar tudo e gerar um único programa ele vai funcionar;

-----------Acompanhar o resumo doq cada linguagem trouxe de evoilução ---------------------

q3 - De que maneira as ling. Scheme e COMMON LISP são opostas?

r. A ideia do scheme era ser uma linguagem pura, puramente funcional, ou seja, não ter traços de outros paradigmas, enquanto algumas ling. permitem programar de linguagens de diversas formas, a scheme seria só pra programação funcional, enquanto o common lisp q vem do lisp (nele começaram a surgir muitos dialetos, hoje tudo é padronizado) o common lisp serve para integrar todos esses dialetos com muitas variações que ocorreram na linguagem. Um dos problemas do commonlisp é que ela é muito grande e suporta muita coisa mas geralmente os usuarios não terão conhecimento de tudo isso, entretanto isso de manter as coisas de uma versão antiga é comum em qualquer programa ou linguagem.

A ideia do ALGOL é manter a melhor forma possivel, por isso utiliza a programação ortogonal, onde com um pequeno conjunto de coisa, sem mts exceções é possivel fazer muita coisa;

Ele tinha um if chamado de if aritmético [IF (valor) Rotulo1 Rotulo2 Rotulo3] - O que difere dos ifs atuais, que são aritméticos, se for menor q zero vai pro 1, igual a zero pro 2, pro rotulo 3 se for maior q zero

//

Qual parser a primeira linguagem de programação usou?

A primeira foi feita com um parser no nível de máquina, geralmente até hoje em dia é utilizado o c pelo seu acesso direto à memória

Os 3 pilares são  - Encapsulamento (visibilidade dos comp. da abstração) e abstração (classes e obj,), herança e polimorfismo, o smalltalk é considerado a primeira linguagem pq implementou esses 3 pilares

-- outra questao
Cite duas caracteristicas do c q tornam menos segura

O c não verifica se voce chegou na posicao do array, ex. se vc chegou na posicao 10 de um array de tamanho 8, entretanto numa posicao q n existe pode ter alguma coisa do lixo de memória e ele acaba retornando isso, ent ele pode desalocar alguma outra variavel por estar "expandido" o array por conta propria, e outra coisa que a torna insegura é a manipulação dos ponteiros direto na memória, principalmente por conta do uso de uma variavel que armazena diretamente o endereço de memoria

Sentenças que compoe uma base de dados prolog

fatos e regras, exx. fulano é pai de ciclano, não necessariamente isso precisa estar implementado, só preciso ter os valores definidos, a questoa de pai é filho é um fato, uma regra seria por ex. a capacidade de buscar os netos de ciclano por conta da regra inerente ao fato,

q. aplicação de java nos primeiros anos de uso publico, que são aplicações para web, a primeira ideia de qnd o java foi criado era de que ela fosse uma ling. de uso em diversos equipamentos, principalmente de IOT, mas quando a ling. foi pra web ela se tornou popular.
Apesar da JVM não ser portável, O desenvolvimento dos applets para web foi uma das grandes capacidades para incorporar em págs. html, ex. disso são jogos de navegador com java ou teclados de internet banking para desktop

q. JAVA VS PHP - R.B - Os tipos em php não são indefinidos, e sim dinamicos, enquanto em java os tipos são estáticos., no phpo é posível alterar o tipo em tempo de execucao

q. RUBY - R.E O ruby tem como objetivo ser totalmente orientada a objeto, onde até operações aritméticas são feitas por meio de métodos, chamando por ex. um .sum() para somar dois inteiros;

q. JSP - R.C - O jsp é uma pagina html baseada em java, que quando é analisada pelo compilador tendo em vista que ela é feita por meio no extend servlet, transofrmando a página em um arquivo servlet, capaz de processar requisições HTTP, 

q. deficiencia da sentença switch do c++ e java vs C#, o C# fecha automaticamente blocos switch não necessitando do break para que todas as sentenças não sejam executadas, enquanto em java e c++ o problema de fallthrough acontece, "caindo" direto quando não estão delimitados

q. oq q o python usa ao inves de vetores
As tuplas são imutaveis, as listas e os dicionários(ed map que utiliza um esquema chave-valor)

