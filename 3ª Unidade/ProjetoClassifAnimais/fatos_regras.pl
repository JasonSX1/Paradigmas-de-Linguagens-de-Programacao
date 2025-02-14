% Fatos sobre animais com características adicionais
animal(aguia, [habitat(montanha), comportamento(solitario), dieta(carnivoro), tem_penas, pode_voar, bota_ovos]).
animal(antilope, [habitat(savana), comportamento(em_bando), dieta(herbivoro), tem_pelo]).
animal(arara, [habitat(amazonia), comportamento(social), dieta(frugivoro), tem_penas, pode_voar, bota_ovos]).
animal(camelo, [habitat(deserto), comportamento(em_grupo), dieta(herbivoro), tem_pelo]).
animal(canguru, [habitat(australia), comportamento(em_grupo), dieta(herbivoro), tem_pelo]).
animal(cavalo, [habitat(campo), comportamento(social), dieta(herbivoro), tem_pelo]).
animal(cervo, [habitat(floresta), comportamento(em_grupo), dieta(herbivoro), tem_pelo]).
animal(cobra, [habitat(floresta), comportamento(solitario), dieta(carnivoro), tem_escamas, bota_ovos]).
animal(coelho, [habitat(campo), comportamento(em_grupo), dieta(herbivoro), tem_pelo]).
animal(coruja, [habitat(floresta), comportamento(solitario), dieta(carnivoro), tem_penas, pode_voar, bota_ovos]).
animal(elefante, [habitat(savana), comportamento(social), dieta(herbivoro), tem_pelo]).
animal(falcao, [habitat(amazonia), comportamento(social), dieta(frugivoro), tem_penas, pode_voar, bota_ovos]).
animal(flamingo, [habitat(pantanal), comportamento(em_grupo), dieta(herbivoro), tem_penas, pode_voar, bota_ovos]).
animal(gato, [habitat(urbano), comportamento(independente), dieta(carnivoro), tem_pelo]).
animal(golfinho, [habitat(oceano), comportamento(social), dieta(carnivoro), vive_na_agua]).
animal(guaxinim, [habitat(urbano), comportamento(noturno), dieta(onivoro), tem_pelo]).
animal(jacare, [habitat(pantanal), comportamento(solitario), dieta(carnivoro), tem_escamas, bota_ovos]).
animal(leao, [habitat(savana), comportamento(social), dieta(carnivoro), tem_pelo]).
animal(lobo, [habitat(floresta), comportamento(em_bando), dieta(carnivoro), tem_pelo]).
animal(ornitorrinco, [habitat(australia), comportamento(solitario), dieta(onivoro), tem_pelo, vive_na_agua, bota_ovos]).
animal(panda, [habitat(bambu), comportamento(solitario), dieta(herbivoro), tem_pelo]).
animal(peixe, [habitat(aquatico), comportamento(cardume), dieta(herbivoro), tem_escamas, vive_na_agua, bota_ovos]).
animal(pinguim, [habitat(polo_sul), comportamento(em_colonia), dieta(carnivoro), tem_penas, vive_na_agua, bota_ovos]).
animal(raposa, [habitat(campo), comportamento(solitario), dieta(onivoro), tem_pelo]).
animal(tatu, [habitat(cerrado), comportamento(noturno), dieta(insetivoro), tem_pelo]).
animal(tigre, [habitat(floresta), comportamento(solitaria), dieta(carnivoro), tem_pelo]).
animal(tubarao, [habitat(oceano), comportamento(solitario), dieta(carnivoro), tem_escamas, vive_na_agua]).
animal(urso, [habitat(floresta), comportamento(solitario), dieta(onivoro), tem_pelo]).
animal(urso_polar, [habitat(polo_norte), comportamento(solitario), dieta(carnivoro), tem_pelo, vive_na_agua]).
animal(zebra, [habitat(savana), comportamento(em_grupo), dieta(herbivoro), tem_pelo]).

% ==============================
% > Características dos animais
% ==============================

% Animais que têm pelo
tem_pelo(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(tem_pelo, Caracteristicas).

% Animais que têm penas
tem_penas(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(tem_penas, Caracteristicas).

% Animais que têm escamas
tem_escamas(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(tem_escamas, Caracteristicas).

% Animais que botam ovos
bota_ovos(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(bota_ovos, Caracteristicas).

% Animais que vivem na água
vive_na_agua(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(vive_na_agua, Caracteristicas).

% Animais que podem voar
pode_voar(Nome) :- 
    animal(Nome, Caracteristicas), 
    member(pode_voar, Caracteristicas).

% ==============================
% > Regras para identificar animais
% ==============================

% Regras para calcular a correspondência de características
% Conta quantas características fornecidas aparecem na lista de um animal
contar_correspondencias(CaracteristicasUsuario, CaracteristicasAnimal, N) :-
    findall(X, 
            (member(X, CaracteristicasUsuario), 
             (member(habitat(X), CaracteristicasAnimal);
              member(comportamento(X), CaracteristicasAnimal);
              member(dieta(X), CaracteristicasAnimal))),
            Correspondentes),
    length(Correspondentes, N).

% Identifica os animais e ordena por probabilidade
identificar_animal(Respostas, AnimaisComProbabilidades) :-
    findall((Animal, Probabilidade),
        (animal(Animal, Caracteristicas),
        calcular_probabilidade(Respostas, Caracteristicas, Probabilidade)),
        Lista),
    (Lista \= [] -> sort(2, @>=, Lista, AnimaisComProbabilidades) ; AnimaisComProbabilidades = []). % Ordena por probabilidade pegando o segundo elemento da tupla

% Calcula a probabilidade de um animal ser identificado
calcular_probabilidade(Respostas, Caracteristicas, Probabilidade) :-
    length(Respostas, Total), % Conta a quantidade de características fornecidas
    contar_correspondencias(Respostas, Caracteristicas, Match), % Conta a quantidade de correspondências
    Probabilidade is (Match / Total) * 100. % Cálculo da probabilidade % Calcula a probabilidade de um animal ser identificado

% Retorna todos os habitats únicos na base de conhecimento
listar_habitats(Habitats) :- 
    findall(H, (animal(_, Caracteristicas), member(habitat(H), Caracteristicas)), Lista),
    sort(Lista, Habitats).

% Retorna todos os comportamentos únicos
listar_comportamentos(Comportamentos) :- 
    findall(C, (animal(_, Caracteristicas), member(comportamento(C), Caracteristicas)), Lista),
    sort(Lista, Comportamentos).

% Retorna todas as dietas únicas
listar_dietas(Dietas) :- 
    findall(D, (animal(_, Caracteristicas), member(dieta(D), Caracteristicas)), Lista),
    sort(Lista, Dietas).

% Regra para buscar animais por dieta
animal_por_dieta(Dieta, Nome) :- 
    animal(Nome, Caracteristicas), 
    member(dieta(Dieta), Caracteristicas).

% Regra para buscar animais por habitat
animal_por_habitat(Habitat, Nome) :- 
    animal(Nome, Caracteristicas), 
    member(habitat(Habitat), Caracteristicas).

% Regra para buscar animais por comportamento
animal_por_comportamento(Comportamento, Nome) :- 
    animal(Nome, Caracteristicas), 
    member(comportamento(Comportamento), Caracteristicas).

% ==============================
% > Regra para adivinhar um animal com base nas características fornecidas
% ==============================

adivinhar_animal(Respostas, AnimaisPossiveis) :-
    findall(Nome, 
        (animal(Nome, Caracteristicas), 
         verificar_caracteristicas(Respostas, Caracteristicas)), 
        Lista),
    list_to_set(Lista, AnimaisPossiveis). % Remove duplicatas

verificar_caracteristicas([], _). % Se não há características para verificar, retorna verdadeiro.
verificar_caracteristicas([Caracteristica | Resto], CaracteristicasAnimal) :-
    (member(Caracteristica, CaracteristicasAnimal) ; 
     Caracteristica = dieta('não_sei') ; 
     Caracteristica = habitat('não_sei')), 
    verificar_caracteristicas(Resto, CaracteristicasAnimal).

% ==========================================
% > Regras para cálculo do ecossistema
% ==========================================

% Lista todos os animais que vivem em um determinado habitat
ecossistema(Habitat, Animais) :-
    findall(Nome, (animal(Nome, Caracteristicas), member(habitat(Habitat), Caracteristicas)), Lista),
    list_to_set(Lista, Animais). % Remove duplicatas

% Retorna todos os herbívoros (incluindo frugívoros) em um habitat específico
herbivoros_no_bioma(Habitat, Herbivoros) :-
    findall(Nome, 
        (animal(Nome, Caracteristicas), 
         member(habitat(Habitat), Caracteristicas), 
         (member(dieta(herbivoro), Caracteristicas) ; member(dieta(frugivoro), Caracteristicas))),
        Lista),
    list_to_set(Lista, Herbivoros).

% Retorna todos os carnívoros em um habitat específico
carnivoros_no_bioma(Habitat, Carnivoros) :-
    findall(Nome, 
        (animal(Nome, Caracteristicas), 
         member(habitat(Habitat), Caracteristicas), 
         member(dieta(carnivoro), Caracteristicas)),
        Lista),
    list_to_set(Lista, Carnivoros).

% Retorna todos os onívoros em um habitat específico
onivoros_no_bioma(Habitat, Onivoros) :-
    findall(Nome, 
        (animal(Nome, Caracteristicas), 
         member(habitat(Habitat), Caracteristicas), 
         member(dieta(onivoro), Caracteristicas)),
        Lista),
    list_to_set(Lista, Onivoros).


% Regra para estimar o equilíbrio do ecossistema
equilibrio_ecossistema(Habitat, Estado) :-
    herbivoros_no_bioma(Habitat, Herbivoros),
    carnivoros_no_bioma(Habitat, Carnivoros),
    length(Herbivoros, NumHerbivoros),
    length(Carnivoros, NumCarnivoros),
    (NumCarnivoros > NumHerbivoros -> Estado = "Desequilibrado" ; Estado = "Equilibrado").
