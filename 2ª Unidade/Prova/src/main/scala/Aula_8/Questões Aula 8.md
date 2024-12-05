1. Explique o que é programação declarativa, como ela difere da programação
imperativa e dê exemplos de situações em que a programação declarativa pode ser
mais vantajosa.

R.A programação declarativa é um paradigma de programação que se concentra em o que deve ser
feito em vez de como deve ser feito. Nesse estilo, o desenvolvedor descreve o resultado desejado
sem precisar especificar detalhadamente o fluxo de controle ou a sequência de etapas para alcançá-lo.

Exemplo de programação declarativa (usando SQL):

    ```SELECT * FROM produtos WHERE preco > 100;```

O sistema decide como realizar a busca.

2. Na programação funcional, o que significa uma função ser 'pura'? Dê um exemplo
de uma função pura e um exemplo de função que não é pura, explicando os
motivos.

Uma função pura:
Não possui efeitos colaterais:
    Ela não altera nada fora de seu escopo, como variáveis globais, I/O, ou estado compartilhado.

É determinística:
    Para as mesmas entradas, sempre retorna a mesma saída.

    def soma(a: Int, b: Int): Int = {a + b}

*Determinística:* Para os mesmos valores de a e b, a função sempre retorna o mesmo resultado.
*Sem efeitos colaterais:* Não modifica estados externos, não realiza I/O, e apenas retorna um valor.

var contador = 0

    def somaComEfeito(a: Int, b: Int): Int = {
    contador += 1 // Altera o estado externo 
    a + b }

*Efeitos colaterais:* A função altera a variável global contador toda vez que é chamada.
*Não determinística:* Mesmo com as mesmas entradas a e b, o estado de contador influencia no comportamento da aplicação, tornando os efeitos da função imprevisíveis.

3. Descreva o conceito de imutabilidade e explique sua importância na programação
funcional e como esse conceito ajuda a evitar problemas.

4. Como a imutabilidade e as funções puras influenciam o design de programas em
linguagens funcionais?


5. Explique por que funções sem efeitos colaterais são preferidas na programação
funcional e dê exemplo de função com e sem efeitos colaterais.


6. Defina o que é uma função de alta ordem e exemplifique um cenário prático de
seu uso.


7. Explique o conceito de transparência referencial e como ele se relaciona com
funções puras.


8. O que é uma função lambda e em que situações é vantajoso usá-la?


9. Explique a diferença entre uma função recursiva e uma função que usa iteração.


10. Explique o conceito de "funções como cidadãos de primeira classe" em
programação funcional.


11. Em linguagens funcionais, é comum que as funções possam ser passadas como
parâmetros e retornadas como valores. Explique como isso aumenta a
modularidade e reutilização de código.


12. Em linguagens que suportam avaliação preguiçosa, listas infinitas podem ser
geradas sem causar um loop infinito. Explique como isso é possível e cite um
exemplo de uso prático de uma lista infinita.