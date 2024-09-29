BNF Pura é composta por:
1) Não terminais```

<numero>

2) Terminais
2.1)  na Computação
"casa"
2.2) na Matemática Discreta
casa

3) Operador de produção
na Computação
::= 
na Matemática Discreta
→

4) Transição vazia
na Computação
"" ou E (letra E maiúscula, representando a letra grega epsilon)
na Matemática Discreta
ε (letra grega epsilon)

5) Operador de escolha ou alternativa
|

COMPARATIVO DA BNF COM A EBNF (BNF ESTENDIDA)
A EBNF reduz a complexidade e torna a gramática mais legível, fácil de criar e entender. Mas o poder de expressão das duas é o mesmo, tudo que se faz em BNF pode ser feito em EBNF e vice-versa.
```

//Múltipla escolha. Representado por Parênteses ( ).
//BNF na Computação
<valor> ::= "+" <numero> | "-" <numero>
//BNF na Matemática Discreta
<valor> → + <numero> | - <numero>
//EBNF na Computação 
<valor> ::= ( "+" | "-" ) <numero>
//EBNF na Matemática Discreta
<valor> → ( + | - ) <numero>

//Opcional, representado na computação por ( )?, ou Colchetes [ ] na matemática discreta.
//BNF na Computação 
<valor> ::= "-" <numero> | <numero>
//BNF na Matemática Discreta 
<valor> → - <numero> | <numero>
//EBNF na Computação
<valor> ::= ("-")? <numero>
//EBNF na Matemática Discreta
<valor> → [-] <numero>

//Repetição, representado na computação por ( )*, ( )+ ou Chaves { }, na matemática discreta.
//BNF na Computação 
<list_inst> ::= <inst> | <inst> "," <list_inst>
//BNF na Matemática Discreta 
<list_inst> → <inst> | <inst> , <list_inst>
//EBNF na Computação 
<list_inst> ::= <inst> ("," <inst>)*
//EBNF na Matemática Discreta
<list_inst> → <inst> { , <inst>}