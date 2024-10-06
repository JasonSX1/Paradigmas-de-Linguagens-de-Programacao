public enum State {
    //Estado inicial e final
    q0,

    //Comentarios
    q1, //Após receber "#"
    q2, //Após receber o texto_comentario

    //Declaracao
    q3, //Após receber "int" ou "float" 
    q4, //Após receber a lista_vars

    //Atribuicao
    q5, //Após receber a var
    q6, //Após receber "="
    q7, //Após receber a expressao

    //inst_print
    q8, //após receber "print"
    q9, //Após receber "("
    q10, //Após receber a expressao
    q11,  //Após receber ")"
    qAux1, //Estado que recebe as quebras de linha ou espaço e retorna tudo para os estados de q9 a q11

    //inst_if/else
    q12, //Após receber "if"
    q13, //Após receber "("
    q14, //Após receber a expressao
    q15, //Após receber ")"
    q16, //Após receber "{"
    q17, //Após receber a instrucao
    q18, //Após receber "}"
    q19, //Após receber "else"
    q20, //Após receber "{"
    q21, //Após receber a instrucao
    q22, //Após receber "}"
    qAux2, //Estado que recebe as quebras de linha ou espaço e retorna tudo para os estados de q13 a q22

    //inst_while
    q23, //Após receber "while"
    q24, //Após receber "("
    q25, //Após receber a expressao
    q26, //Após receber ")"
    q27, //Após receber "{"
    q28, //Após receber a instrucao
    q29, //Após receber "}"
    qAux3; //Estado que recebe as quebras de linha ou espaço e retorna tudo para os estados de q24 a q29
}
