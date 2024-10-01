`<programa> ::= <instrucao> <instrucao>*`
`<instrucao> ::= <atribuicao> | <processamento>`
`<processamento> ::= `
`
```AA

int a = 1;
int b = 10;
if(a<b || a==b){
print ("A é menor ou engual que b");
}
--------------------------------------------------
/*Inicio do programa, podendo receber uma ou mais instrucoes*/
<simpleLang> ::= <instrucao>+ 

/*Tipos de instrucao permitidos no programa*/
<instrucao> ::= <atribuicao> "\n"*
| <declaracao> "\n"*
| <inst_if> "\n"*
| "print" "(" <expressao> ")" "\n"*

/*Regra de declaracao de variáveis*/
<declaracao> ::= "int " <lista_vars>
| "float " <lista_vars>

<lista_vars> ::= <var> ( "," <var> )* <inicializacao_opcional>

<inicializacao_opcional> ::= " = " <expressao> | "\n"*

/*Regra de atribuicao*/
<atribuicao> ::= <var> " = " <expressao>
	
/*Expressões possíveis*/
<expressao> ::= <exp_matematica> | <exp_logica>

/*Expressoes matematicas*/
<exp_matematica> ::= <numero>
| "(" <exp_matematica> ")"
| <exp_matematica> <op_mat> <exp_matematica>
| <var>

/*Expressoes logicas*/
<exp_logica> ::= <bool>
| "(" <exp_logica> ")"
| "!" <exp_logica>
| <exp_logica> <op_logico> <exp_logica>
| <exp_matematica> <op_eq> <exp_matematica>
| <exp_logica> <op_eq> <exp_logica>
| <exp_matematica> <op_comp> <exp_matematica>
| <var>

/* Definicao de número, operador matemático e variáveis*/
<numero> ::= [0-9]+
<op_mat> ::= "+" | "-" | "*" | "/"
<var> ::= [a-z]+ | [A-Z]+

/* Operadores lógicos */
<op_logico> ::= "&&" | "||"
<bool> ::= "true" | "false"
<op_eq> ::= "==" | "!="
<op_comp> ::= ">" | "<" | ">=" | "<="

<inst_if> ::= "if(" <exp_logica> "){" "\n"* <expressao> "}" "\n"*
| "if(" <exp_matematica> "){" "\n"* <expressao> "}" "\n"*
   "else {" <expressao> "}" "\n"*
/*Testar assim e depois testar com expressao diretamente ao inves de usar logica e matematica*/
/*Tem que corrigir um if (a>b) e tb tem q corrigir a instanciacao*/