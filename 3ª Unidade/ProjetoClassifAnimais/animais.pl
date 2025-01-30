% Fatos sobre os animais e suas categorias
animal(cachorro, mamifero, carnivoro, terrestre).
animal(gato, mamifero, carnivoro, terrestre).
animal(elefante, mamifero, herbivoro, terrestre).
animal(jacare, reptil, carnivoro, terrestre).
animal(pinguim, ave, carnivoro, terrestre).
animal(leao, mamifero, carnivoro, terrestre).
animal(elefante, mamifero, herbivoro, terrestre).
animal(pinguim, ave, carnivoro, aquatico).
animal(cobra, reptil, carnivoro, terrestre).
animal(tartaruga, reptil, herbivoro, terrestre).
animal(sapo, anfibio, insetivoro, aquatico).
animal(aguia, ave, carnivoro, voador).
animal(teiu, reptil, carnivoro, terrestre).
animal(capivara, mamifero, herbivoro, terrestre).
animal(calango, reptil, insetivoro, terrestre).
animal(periquito, ave, onivoro, voador).
animal(galinha, ave, onivoro, terrestre).
animal(leao, mamifero, carnivoro, terrestre).

% Características dos animais
tem_pelo(cachorro).
tem_pelo(gato).
tem_pelo(leao).
tem_pelo(capivara).
tem_penas(pinguim).
tem_penas(aguia).
tem_penas(periquito).
tem_penas(galinha).
tem_escamas(jacare).
tem_escamas(calango).
tem_escamas(tartaruga).
tem_escamas(teiu).
tem_escamas(cobra).
bota_ovos(cobra).
bota_ovos(jacare).
bota_ovos(pinguim).
bota_ovos(aguia).
bota_ovos(galinha)
bota_ovos(periquito).
bota_ovos(tartaruga).
bota_ovos(calango).

% Fatos sobre os habitats
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

% Regra para buscar animais por categoria
animal_por_categoria(Nome, Categoria) :- animal(Nome, Categoria, _, _).

% Regra para buscar animais por dieta
animal_por_dieta(Nome, Dieta) :- animal(Nome, _, Dieta, _).

% Regra para buscar animais por habitat
animal_por_habitat(Nome, Habitat) :- vive_em(Nome, Habitat).

% Regras para inferência
eh_mamifero(X) :- tem_pelo(X).
eh_ave(X) :- tem_penas(X), bota_ovos(X).
eh_reptil(X) :- bota_ovos(X), \+ tem_penas(X).

% Relacionamento entre características
tem_pelo(X) :- animal(X, mamifero, _, _), X \= elefante.
tem_penas(X) :- animal(X, ave, _, _).
tem_escamas(X) :- animal(X, reptil, _, _).

% Se tem penas, pode voar (salvo exceções como pinguim)
pode_voar(X) :- tem_penas(X), X \= pinguim.

% Vive na água se for anfíbio ou animal específico
vive_na_agua(X) :- animal(X, anfibio, _, _).
vive_na_agua(X) :- member(X, [pinguim, sapo, tartaruga, capivara, elefante]).

% Se um animal tem pelos, ele não tem penas nem escamas
nao_tem_penas(X) :- tem_pelo(X).
nao_tem_escamas(X) :- tem_pelo(X).

% Se um animal tem penas, ele não tem pelos nem escamas
nao_tem_pelo(X) :- tem_penas(X).
nao_tem_escamas(X) :- tem_penas(X).

% Se um animal tem escamas, ele não tem pelos nem penas
nao_tem_pelo(X) :- tem_escamas(X).
nao_tem_penas(X) :- tem_escamas(X).