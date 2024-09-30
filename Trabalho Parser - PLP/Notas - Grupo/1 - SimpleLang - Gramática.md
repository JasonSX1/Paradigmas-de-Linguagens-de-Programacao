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

TABELA DE SÍMBOLOS  - ANALISADOR LÉXICO

ID       Token                Lexema(lido)     

1    $INST_IF$        if

--------------------------------------------------
/**/
/*Inicio do programa, podendo receber uma ou mais instrucoes*/
<simpleLang> ::= <instrucao>+

/*Tipos de intrucao permitidos no programa*/
<instrucao> ::= <atribuicao>
| <inst_if>
| <inst_else>
| <int_while>
| "print" "(" <expressao> ")"

<atribuicao> ::= <variavel> " = " <expressao>

<expressao> ::= <matematica> | <logica>

<matematica> ::= <numero>

<numero>

<op_logico> ::= "&&" | "||"
<op_relacional> ::= "==" | "!=" | "<" | ">" | "<=" | ">="
<bool> ::= "true" | "false"

<variavel> ::= <tipo>

<keyWord> ::= "int"
| "float"
| "print"
| "if"
| "else"
| "while"

<operators> ::= "+"
| "-"
| "*"
| "/"