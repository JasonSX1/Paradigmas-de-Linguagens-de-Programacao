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
<instrucao> ::= <comentario> "\n"*
| <atribuicao> "\n"*
| <declaracao> "\n"*
| <inst_if> "\n"*
| <inst_print> "\n"*
| <inst_while> "\n"*
 
<comentario> ::= "#" <texto_comentario>
<texto_comentario> ::= <caractere>* "\n"?
<caractere> ::= "_" | [a-z] | [A-Z] | [0-9] | " " | "+" | "-" | "*" | "/"

<inst_print> ::= "print" <espaco_ou_quebra> "(" <espaco_ou_quebra> <expressao> <espaco_ou_quebra> ")" <espaco_ou_quebra> "\n"*
| "print" <espaco_ou_quebra> <expressao> <espaco_ou_quebra> "\n"*

/*Regra de declaracao de variáveis*/
<declaracao> ::= "int " <lista_vars>
| "float " <lista_vars>

<lista_vars> ::= <var> ( "," " "* <var> )* <inicializacao_opcional>

<inicializacao_opcional> ::= " = " <expressao> | "\n"*

/*Regra de atribuicao*/
<atribuicao> ::= <var> " = " <expressao>
	
/*Expressões possíveis*/
<expressao> ::= <exp_matematica> | <exp_logica>

/*Expressoes matematicas*/
<exp_matematica> ::= <numero>
| "(" <exp_matematica> ")"
| <exp_matematica> " "* <op_mat> " "* <exp_matematica>
| <var>

/*Expressoes logicas*/
<exp_logica> ::= " "* <bool>
| "(" " "* <exp_logica> " "* ")"
| "!" " "* <exp_logica>
| <exp_logica> " "* <op_logico> " "* <exp_logica>
| <exp_matematica> " "* <op_eq> " "* <exp_matematica>
| <exp_logica> " "* <op_eq> " "* <exp_logica>
| <exp_matematica> " "* <op_comp> " "* <exp_matematica>
| <var>

/* Definicao de número, operador matemático e variáveis*/
<numero> ::= [0-9]+
<op_mat> ::= "+" | "-" | "*" | "/"
<var> ::= ([a-z] | [A-Z] | [0-9])*

/* Operadores lógicos */
<op_logico> ::= "&&" | "||"
<bool> ::= "true" | "false"
<op_eq> ::= "==" | "!="
<op_comp> ::= ">" | "<" | ">=" | "<="

/*Regra para reconhecer espaços e quebras de linha*/
<espaco_ou_quebra> ::= (" " | "\n")*

/*Estrutura condicional if/else com suporte a espaços e quebras de linha*/
<inst_if> ::= "if" <espaco_ou_quebra>? "(" <espaco_ou_quebra>? <expressao> <espaco_ou_quebra>? ")" <espaco_ou_quebra>? "{" <espaco_ou_quebra>? <instrucao>* <espaco_ou_quebra>? "}" <espaco_ou_quebra>? <inst_else>? <espaco_ou_quebra>?

<inst_else> ::= "else" <espaco_ou_quebra>? "{" <espaco_ou_quebra>? <instrucao>* <espaco_ou_quebra>? "}" <espaco_ou_quebra>?

/*Estrutura de loop while com suporte a espaços e quebras de linha*/
<inst_while> ::= "while" <espaco_ou_quebra>? "(" <espaco_ou_quebra>? <expressao> <espaco_ou_quebra>? ")" <espaco_ou_quebra>? "{" <espaco_ou_quebra> <instrucao>* <espaco_ou_quebra> "}" <espaco_ou_quebra>?
