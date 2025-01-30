% Fatos sobre os animais
animal(mamifero, cachorro).
animal(mamifero, gato).
animal(mamifero, elefante).
animal(reptil, jacare).
animal(reptil, cobra).
animal(ave, pinguim).
animal(ave, aguia).

% Características dos animais
tem_pelo(cachorro).
tem_pelo(gato).
tem_penas(pinguim).
tem_penas(aguia).
bota_ovos(cobra).
bota_ovos(jacare).
bota_ovos(pinguim).
bota_ovos(aguia).

% Regras para inferência
eh_mamifero(X) :- tem_pelo(X).
eh_ave(X) :- tem_penas(X), bota_ovos(X).
eh_reptil(X) :- bota_ovos(X), \+ tem_penas(X).
