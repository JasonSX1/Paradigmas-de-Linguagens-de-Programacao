Sistema de Classificação de Animais
Desenvolver um sistema que sugere possíveis animais com base nas características fornecidas, como comportamento, dieta e habitat.

Prolog:
Trabalhar com características dos animais como comportamento, dieta e habitat.
Definir os animais e suas características, com regras que determinam o animal mais provável com base nas características fornecidas.
Validar se as características fornecidas correspondem a vários animais e listar os mais prováveis com porcentagens.

Testes diretamente no SWI-Prolog
Entrada:
identificar_animal([solitario, herbivoro], AnimaisComProbabilidades).
Saída:
AnimaisComProbabilidades = [(urso, 33.33333333333333), (gato, 0), (peixe, 33.33333333333333), (aguia, 33.33333333333333), (elefante, 33.33333333333333)].

Entrada:
identificar_animal([floresta, solitario, onivoro], AnimaisComProbabilidades).
Saída:
AnimaisComProbabilidades = [(urso, 100), (gato, 0), (peixe, 0), (aguia, 33.33333333333333), (elefante, 0)].

---

### Python:

[] Interface para o usuário inserir as informações e gerar as respostas apropriadas.

[] Carrega a base de dados de regras e fatos em Prolog.

[x] Usa o motor de inferência do Prolog para inferir o animal mais provável com base nas características.

[] Exibe os possíveis animais com probabilidades.

Exemplo de Entrada: 
Forneça as características do animal separadas por vírgula: floresta, solitario, onivoro

Exemplo de Saída: 
Características fornecidas:
Habitat: Floresta
Comportamento: Solitario
Dieta: Onívoro

Animais possíveis:
Urso (Probabilidade: 100.00%)
Águia (Probabilidade: 33.33%)