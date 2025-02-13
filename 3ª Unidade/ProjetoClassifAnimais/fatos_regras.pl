% Fatos sobre animais
animal(aguia, [habitat(montanha), comportamento(solitario), dieta(carnivoro)]).
animal(antilope, [habitat(savana), comportamento(em_bando), dieta(herbivoro)]).
animal(arara, [habitat(amazonia), comportamento(social), dieta(frugivoro)]).
animal(camelo, [habitat(deserto), comportamento(em_grupo), dieta(herbivoro)]).
animal(canguru, [habitat(australia), comportamento(em_grupo), dieta(herbivoro)]).
animal(cavalo, [habitat(campo), comportamento(social), dieta(herbivoro)]).
animal(cervo, [habitat(floresta), comportamento(em_grupo), dieta(herbivoro)]).
animal(cobra, [habitat(floresta), comportamento(solitario), dieta(carnivoro)]).
animal(coelho, [habitat(campo), comportamento(em_grupo), dieta(herbivoro)]).
animal(coruja, [habitat(floresta), comportamento(solitario), dieta(carnivoro)]).
animal(elefante, [habitat(savana), comportamento(social), dieta(herbivoro)]).
animal(falacao, [habitat(amazonia), comportamento(social), dieta(frugivoro)]).
animal(flamingo, [habitat(pantanal), comportamento(em_grupo), dieta(herbivoro)]).
animal(gato, [habitat(urbano), comportamento(independente), dieta(carnivoro)]).
animal(golfinho, [habitat(oceano), comportamento(social), dieta(carnivoro)]).
animal(guaxinim, [habitat(urbano), comportamento(noturno), dieta(onivoro)]).
animal(jacare, [habitat(pantanal), comportamento(solitario), dieta(carnivoro)]).
animal(leao, [habitat(savana), comportamento(social), dieta(carnivoro)]).
animal(lobo, [habitat(floresta), comportamento(em_bando), dieta(carnivoro)]).
animal(ornitorrinco, [habitat(australia), comportamento(solitario), dieta(onivoro)]).
animal(panda, [habitat(bambu), comportamento(solitario), dieta(herbivoro)]).
animal(peixe, [habitat(aquatico), comportamento(cardume), dieta(herbivoro)]).
animal(pinguim, [habitat(polo_sul), comportamento(em_colonia), dieta(carnivoro)]).
animal(raposa, [habitat(campo), comportamento(solitario), dieta(onivoro)]).
animal(tatu, [habitat(cerrado), comportamento(noturno), dieta(insetivoro)]).
animal(tigre, [habitat(floresta), comportamento(solitaria), dieta(carnivoro)]).
animal(tubarao, [habitat(oceano), comportamento(solitario), dieta(carnivoro)]).
animal(urso, [habitat(floresta), comportamento(solitario), dieta(onivoro)]).
animal(urso_polar, [habitat(polo_norte), comportamento(solitario), dieta(carnivoro)]).
animal(zebra, [habitat(savana), comportamento(em_grupo), dieta(herbivoro)]).

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
    (Lista \= [] -> sort(2, @>=, Lista, AnimaisComProbabilidades) ; AnimaisComProbabilidades = []).

% Calcula a probabilidade de um animal ser identificado
calcular_probabilidade(Respostas, Caracteristicas, Probabilidade) :-
    length(Respostas, Total), % Quantidade de características fornecidas
    contar_correspondencias(Respostas, Caracteristicas, Match),
    Probabilidade is (Match / Total) * 100. % Cálculo da probabilidade

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

