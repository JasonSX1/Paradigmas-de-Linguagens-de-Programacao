# Classificador de Animais - Paradigmas de Linguagens de Programação

Este é um projeto desenvolvido para a disciplina de **Paradigmas de Linguagens de Programação**, focado na **utilização do paradigma lógico** através da linguagem **Prolog**. A aplicação permite classificar animais, identificar suas características, calcular ecossistemas e analisar o equilíbrio ambiental de diferentes biomas.

- Atividade realizada pelo grupo 2, componentes:
    
    -Caroline Feitosa
    
    -David Macedo
    
    -Elis Weiss
   
    -Geison Ferreira
    
    -Igor de Eça
    
    -Sarah Borges
    
    -Wendel Coelho

## **Objetivo**
O principal objetivo deste projeto é **demonstrar a aplicação do paradigma lógico** na modelagem de conhecimento e inferência sobre o reino animal, explorando conceitos como:
- **Fatos e regras** para descrever animais e seus atributos.
- **Consultas e inferências** para responder perguntas sobre os animais.
- **Integração do Prolog com Python**, utilizando a biblioteca [`pyswip`](https://pypi.org/project/pyswip/).

---

## **Tecnologias Utilizadas**
- **Prolog** → Linguagem baseada no paradigma lógico para modelagem do conhecimento.
- **Python** → Responsável pela interface do usuário e integração com o Prolog.
- **SWI-Prolog** → Interpretador Prolog utilizado na execução do código lógico.
- **pyswip** → Biblioteca Python que permite comunicação com o Prolog.

---

## **Funcionalidades**
### **Consultas sobre Animais**
- Listagem de animais por **tipo** (mamífero, réptil, ave, anfíbio).
- Classificação de animais por **dieta** (carnívoro, herbívoro, onívoro).
- Identificação de animais pelo **habitat** (savana, floresta, oceano, etc.).

### **Modo "Akinator"**
- O sistema faz perguntas interativas para tentar **adivinhar um animal** com base nas características fornecidas pelo usuário.
- Utiliza **regras lógicas** para inferir o tipo e possíveis espécies.

### **Cálculo de Ecossistema**
- Dado um bioma (savana, floresta, oceano, etc.), o sistema:
  - **Identifica os animais que vivem nesse bioma**.
  - **Separa-os em herbívoros, carnívoros e onívoros**.
  - **Verifica o equilíbrio ecológico** (se há muitos predadores e poucas presas).

---

## **Estrutura do Projeto**
ProjetoClassifAnimais/ 
│── README.md # Documentação do projeto 
│── main.py # Código principal em Python 
│── animais.pl # Base de conhecimento e regras em Prolog 

---

## **Como Executar**    
### **1. Instalar Dependências**
Certifique-se de ter o **Python 3** e o **SWI-Prolog** instalados.  
Depois, instale a biblioteca `pyswip`:
``` pip install pyswip ```

### **2. Executar o Programa**
No terminal ou prompt de comando, execute:

``` python main.py ```

---

## Exemplo de Código Prolog
O arquivo animais.pl contém fatos e regras para definir os animais e suas características.
Exemplo:


``` % Fatos sobre os animais
animal(cachorro, mamifero, carnivoro, terrestre).
animal(elefante, mamifero, herbivoro, terrestre).
% Características
tem_pelo(cachorro).
tem_pelo(elefante).

% Inferências
eh_mamifero(X) :- tem_pelo(X). 
```

Essa regra permite inferir que um animal é mamífero se ele tiver pelo.

---

## Integração Python + Prolog
O Python consulta o Prolog usando a biblioteca pyswip.
Exemplo de consulta que retorna todos os mamíferos:

``` from pyswip import Prolog 

prolog = Prolog()
prolog.consult("animais.pl")

for resultado in prolog.query("eh_mamifero(X)"):
    print(resultado["X"])  # Exibe todos os mamíferos
```
---

## Conceitos de Paradigma Lógico
Este projeto explora o paradigma lógico, que se baseia em fatos, regras e inferência para resolver problemas.

### Principais conceitos aplicados

- Fatos → Declaram informações verdadeiras (animal(elefante, mamifero, herbivoro, terrestre).).

- Regras → Definem inferências (eh_mamifero(X) :- tem_pelo(X).).

- Consultas → Perguntas sobre a base de conhecimento (?- eh_mamifero(X).).

- Backtracking → O Prolog busca diferentes soluções possíveis de forma automática.

- Unificação → Permite a correspondência de variáveis e padrões (vive_em(Nome, floresta).).


 Diferente de linguagens imperativas, no paradigma lógico não escrevemos "como fazer", mas sim "o que é verdadeiro".

## Conclusão

Este projeto mostra como o Prolog pode ser usado para representar conhecimento sobre o mundo real, e como pode ser integrado ao Python para criar aplicações interativas e inteligentes.

Demonstração prática do paradigma lógico na modelagem de um sistema de classificação de animais!
