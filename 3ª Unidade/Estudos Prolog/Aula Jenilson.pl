homem(lima).
homem(hamilton).
mulher(ana).
mulher(maria).
marido(lima, maria).
marido(hamilton, ana).
casado(hamilton, ana).

pai(lima, rodrigo).
pai(lima, teresa).
pai(rodrigo, marta).
pai(borge, lima).
pai(borge, hamilton).

mae(maria, rodrigo).
mae(maria, teresa).
mae(rosa, marta).
mae(paula, lima).
mae(paula, hamilton).
mae(joana, rosa).

filho(X,Y) :- pai(Y,X).

irmao(X,Y) :- pai(P,X),pai(P,Y),X\=Y.

tio(X,Y) :- pai(P,X), pai(P,Z),pai(Z,Y),X\=Z.

avô(X,Y) :- pai(X,P), pai(P,Y),X\=Y.

% agr é hora de recursividade

verificar_vazia([]).
verificar_vazia([Cabeca | Cauda]) :- false.

% Caso base: a soma da lista vazia é 0.
soma_lista([], 0).

% Caso recursivo: a soma de [H|T] é H mais a soma de T.
soma_lista([H|T], Soma) :-
    soma_lista(T, SomasT),
    Soma is H + SomaT.


%Contar quantos elementos tem numa lista
contar_elememtos([], 0).
contar_elementos([Cabeca | Cauda], Contador) :-
    contar_elementos([Cauda | RestoCauda]), Contador is Contador +1.

