% ==========================================
% > Fatos sobre os animais e suas categorias
% ==========================================

animal(cachorro, mamifero, carnivoro, terrestre).
animal(gato, mamifero, carnivoro, terrestre).
animal(elefante, mamifero, herbivoro, terrestre).
animal(jacare, reptil, carnivoro, terrestre).
animal(pinguim, ave, carnivoro, terrestre).
animal(leao, mamifero, carnivoro, terrestre).
animal(cobra, reptil, carnivoro, terrestre).
animal(tartaruga, reptil, herbivoro, terrestre).
animal(sapo, anfibio, insetivoro, aquatico).
animal(aguia, ave, carnivoro, voador).
animal(teiu, reptil, carnivoro, terrestre).
animal(capivara, mamifero, herbivoro, terrestre).
animal(calango, reptil, insetivoro, terrestre).
animal(periquito, ave, herbivoro, voador).
animal(galinha, ave, onivoro, terrestre).
animal(golfinho, mamifero, carnivoro, aquatico).
animal(lobo-guara, mamifero, onivoro, terrestre).
animal(avestruz, ave, onivoro, terrestre).
animal(peixe-palhaco, peixe, carnivoro, aquatico).
animal(coruja, ave, carnivoro, voador).
animal(camaleao, reptil, insetivoro, terrestre).
animal(salamandra, anfibio, insetivoro, aquatico).

% ==============================
% > Características dos animais
% ==============================

% Animais que têm pelo (Exceto o elefante e o golfinho)
tem_pelo(X) :- animal(X, mamifero, _, _), X \= elefante, X \= golfinho.

% Animais que têm penas
tem_penas(X) :- animal(X, ave, _, _).

% Animais que têm escamas
tem_escamas(X) :- animal(X, reptil, _, _); animal(X, peixe, _, _).

% Animais que botam ovos
bota_ovos(cobra).
bota_ovos(jacare).
bota_ovos(pinguim).
bota_ovos(tartaruga).
bota_ovos(calango).
bota_ovos(peixe-palhaco).
bota_ovo(X) :- animal(X, ave, _, _).
% Não coloquei todos os repteis pq os viviparos nao colocam ovos (algumas especies de cobra e lagartixas), assim como existem
% os peixes que nao botam ovos .

% ==============================
% > Fatos sobre os habitats
% ==============================

vive_em(leao, savana).
vive_em(elefante, savana).
vive_em(pinguim, antartida).
vive_em(cobra, floresta).
vive_em(sapo, pantano).
vive_em(aguia, montanhas).
vive_em(cachorro, domestico).
vive_em(gato, domestico).
vive_em(jacare, pantano).
vive_em(tartaruga, oceano).
vive_em(sapo, floresta).
vive_em(teiu, caatinga).
vive_em(capivara, savana).
vive_em(calango, caatinga).
vive_em(periquito, floresta).
vive_em(galinha, domestico).
vive_em(galinha, floresta).
vive_em(golfinho, oceano).
vive_em(lobo-guara, cerrado).
vive_em(lobo-guara, floresta).
vive_em(avestruz, savana).
vive_em(peixe-palhaco, oceano).
vive_em(camaleao, floresta).
vive_em(coruja, floresta).
vive_em(coruja, montanhas).
vive_em(salamandra, floresta).
vive_em(salamandra, pantano).

% Regra para buscar animais por categoria
animal_por_categoria(Nome, Categoria) :- animal(Nome, Categoria, _, _).

% Regra para buscar animais por dieta
animal_por_dieta(Nome, Dieta) :- animal(Nome, _, Dieta, _).

% Regra para buscar animais por habitat
animal_por_habitat(Nome, Habitat) :- vive_em(Nome, Habitat).

% Regras para inferência de classes dos animais
eh_mamifero(X) :- tem_pelo(X).
eh_ave(X) :- tem_penas(X), bota_ovos(X).
eh_reptil(X) :- bota_ovos(X), \+ tem_penas(X).

% Se tem penas, pode voar (exceto o pinguim e a avestruz)
pode_voar(X) :- tem_penas(X), X \= pinguim, X \= avestruz.

% Vive na água se for anfíbio ou animal específico
vive_na_agua(X) :- animal(X, anfibio, _, _) ; animal(X, peixe, _, _) ; animal(X, _, _, aquatico).
vive_na_agua(X) :- member(X, [pinguim, sapo, tartaruga, capivara, elefante, golfinho]).

% ==========================================
% > Regras de exclusão entre características
% ==========================================

nao_tem_penas(X) :- tem_pelo(X).
nao_tem_penas(X) :- tem_escamas(X).

nao_tem_pelo(X) :- tem_penas(X).
nao_tem_pelo(X) :- tem_escamas(X).

nao_tem_escamas(X) :- tem_penas(X).
nao_tem_escamas(X) :- tem_pelo(X).

% Regra para listar todos os animais que vivem em um determinado bioma
ecossistema(Bioma, Animais) :-
    findall(Nome, vive_em(Nome, Bioma), Lista),
    list_to_set(Lista, Animais). % Remover duplicatas transformando a lista num set

% Regra para obter a lista de herbívoros do bioma
herbivoros_no_bioma(Bioma, Herbivoros) :-
    findall(Nome, (vive_em(Nome, Bioma), animal(Nome, _, herbivoro, _)), Lista),
    list_to_set(Lista, Herbivoros).

% Regra para obter a lista de carnívoros do bioma
carnivoros_no_bioma(Bioma, Carnivoros) :-
    findall(Nome, (vive_em(Nome, Bioma), animal(Nome, _, carnivoro, _)), Lista),
    list_to_set(Lista, Carnivoros).

% Regra para obter a lista de onívoros do bioma
onivoros_no_bioma(Bioma, Onivoros) :-
    findall(Nome, (vive_em(Nome, Bioma), animal(Nome, _, onivoro, _)), Lista),
    list_to_set(Lista, Onivoros).

% Regra para estimar o equilíbrio do ecossistema
equilibrio_ecossistema(Bioma) :-
    herbivoros_no_bioma(Bioma, Herbivoros),
    carnivoros_no_bioma(Bioma, Carnivoros),
    onivoros_no_bioma(Bioma, Onivoros),
    length(Herbivoros, NumHerbivoros),
    length(Carnivoros, NumCarnivoros),
    length(Onivoros, NumOnivoros),
    write('Relatório do Ecossistema para '), write(Bioma), nl,
    write('Herbívoros: '), write(Herbivoros), nl,
    write('Carnívoros: '), write(Carnivoros), nl,
    write('Onívoros: '), write(Onivoros), nl,
    (NumCarnivoros > NumHerbivoros ->
        write('Ecossistema desequilibrado: Muitos predadores para poucas presas!'), nl
        ; write('Ecossistema equilibrado!'), nl).
