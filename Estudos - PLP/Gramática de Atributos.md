Atributos em uma gramática de atributos são informações adicionais associada a um símbolo ou nó da gramática, que enriquece a análise sintática com dados semânticos. Podem ser usados tanto para verificações semânticas (como tipagem e escopo de variáveis) quanto para cálculos (como o valor de uma expressão).

Podem ser do tipo:
I = Inherited (Herdado). I(X),atributos herdados do nó X.
S = Syntesized (Sintetizado). S(X), atributos sintetizados do nó X.

Sintetizados = Calculados a partir dos atributos dos filhos de um nó na árvore de derivação.
Herdados = Passados de um nó para seus filhos na árvore de derivação.

Função Predicativa tem a forma de expressão boleana, com valor resultado Verdadeiro ou Falso. As derivações permitidas devem ter predicados, associados aos nós não terminais, como verdadeiro. Uma função predicada falsa indica uma violação das regras de sintaxe ou semântica estática da linguagem.

Atributos Intrínsecos são sintetizados de vértices-folhas, cujos valores determinaram-se fora da árvore de análise, como por exemplo, o tipo de uuma variável, que pode ser consultado em uma tabela de símbolos.

Exemplo do livro:
```
<atribuicao> -> <var> = <expr> {<expr>.tipo_esperado <- <var>.tipo_efetivo}
<expr> -> <var>                {<expr>.tipo_efetivo <- <var>.tipo_efetivo} 
<expr> -> <var> + <var>'       {<expr>.tipo_efetivo <- if ( <var>.tipo_efetivo = num_int) e ( <var>'.tipo_efetivo = num_int) then int else num_real endif}
<var> -> A | B | C             {<var>.tipo_efetivo <- lookup(<var>.token)}
```


Exercícios:
Criar Gramática de Atributos para os seguintes cenários, com base na Gramática fornecida:

1. Avaliação de expressões aritméticas simples.
```
<E> -> <E>' + <T>
<E> -> <T>
<T> -> <T>' * <F>
<T> -> <F>
<F> -> ( <E> )
<F> -> num
```

2. Cálculo do comprimento de uma string.
```
<S> -> <S>' <C>
<S> -> <C>
<C> -> letra | digito
```

3. Verificação de tipos em expressões.
```
<E> -> <E>' + <E>''
<E> -> num_int
<E> -> num_real
```

4. Cálculo de valores booleanos para expressões lógicas.
```
<E> -> <E>' AND <E>''
<E> -> <E>' OR <E>''
<E> -> NOT <E>'
<E> -> true
<E> -> false
```

5. Verificação de variáveis declaradas.
```
<S> -> <S>' <V>
<S> -> decl <V>
<V> -> id
```

6. Avaliação de expressões com precedência de operadores.
```
<E> -> <E>' + <T>
<E> -> <T>
<T> -> <T>' * <F>
<T> -> <F>
<F> -> num
```

7. Análise de uma linguagem de controle de fluxo.
```
<S> -> if ( <E> ) <S>' else <S>''
<E> -> true
<E> -> false 
```

8. Cálculo de níveis de aninhamento de blocos de código.
```
<S> -> { <S>' }
<S> -> statement
```

9. Validação de identificadores em diferentes escopos.
```
<S> -> decl <V>
<S> -> <V>
<V> -> id
```

10. Reconhecimento de números inteiros e reais.
```
<N> -> num_int
<N> -> num_real
```
