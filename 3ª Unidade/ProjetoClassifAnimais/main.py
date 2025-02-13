from pyswip import Prolog

# Inicialização do Prolog
prolog = Prolog()
prolog.consult("animais.pl")

# Opções fixas para evitar erros de digitação
TIPOS = ["mamifero", "reptil", "ave", "anfibio"]
DIETAS = ["carnivoro", "herbivoro", "insetivoro", "onivoro"]
HABITATS = ["savana", "floresta", "pantano", "montanhas", "antartida", "domestico", "oceano", "caatinga", "cerrado"]

def escolher_opcao(lista, mensagem):
    """Mostra um menu numérico para o usuário escolher uma opção."""
    if "Não sei" not in lista:  # Evita duplicação de "Não sei"
        lista = lista + ["Não sei"]

    print(f"\n{mensagem}")
    
    for i, opcao in enumerate(lista, 1):
        print(f"{i}. {opcao.capitalize()}")

    while True:
        try:
            escolha = int(input("Escolha uma opção: "))
            if 1 <= escolha <= len(lista) - 1:  # Retorna a opção válida
                return lista[escolha - 1]
            elif escolha == len(lista):  # Última opção ("Não sei")
                return None
        except ValueError:
            pass
        print("Opção inválida. Escolha um número da lista.")
        
def listar_todos_animais():
    total_animais = len(list(prolog.query("animal(Nome, _, _, _)")))
    print(f"\n Existem {total_animais} animais cadastrados no banco de dados, e eles são:")
    animais = set(resultado['Nome'].capitalize() for resultado in prolog.query("animal(Nome, _, _, _)"))
    if animais:
        for nome in sorted(animais):
            print(f"- {nome}")
    else:
        print("Nenhum animal encontrado.")

def listar_animais_por_tipo():
    tipo = escolher_opcao(TIPOS, "Escolha o tipo de animal:")
    if tipo:
        print(f"\nAnimais do tipo {tipo}:")
        animais = sorted({resultado['Nome'].capitalize() for resultado in prolog.query(f"animal_por_categoria(Nome, {tipo})")})
        for nome in animais:
            print(f"- {nome}")

def listar_animais_por_dieta():
    dieta = escolher_opcao(DIETAS, "Escolha a dieta do animal:")
    if dieta:
        print(f"\nAnimais com dieta {dieta}:")
        animais = sorted({resultado['Nome'].capitalize() for resultado in prolog.query(f"animal_por_dieta(Nome, {dieta})")})
        for nome in animais:
            print(f"- {nome}")

def listar_animais_por_habitat():
    habitat = escolher_opcao(HABITATS, "Escolha o habitat do animal:")
    if habitat:
        print(f"\nAnimais que vivem em {habitat}:")
        animais = sorted({resultado['Nome'].capitalize() for resultado in prolog.query(f"animal_por_habitat(Nome, {habitat})")})
        for nome in animais:
            print(f"- {nome}")

# Modo "Akinator"
def identificar_animal():
    print("\n=== Modo de Advinhação ===")

    respostas = {}

    print("\nEscolha as características do animal:")

    # Pergunta sobre pele (somente uma delas será considerada)
    pelos = escolher_opcao(["Sim", "Não"], "O animal tem pelos?")
    if pelos == "Sim":
        respostas["tem_pelo"] = True
    elif pelos == "Não":
        penas = escolher_opcao(["Sim", "Não"], "O animal tem penas?")
        if penas == "Sim":
            respostas["tem_penas"] = True
        elif penas == "Não":
            escamas = escolher_opcao(["Sim", "Não"], "O animal tem escamas?")
            if escamas == "Sim":
                respostas["tem_escamas"] = True

    # Pergunta se o animal vive na água
    agua = escolher_opcao(["Sim", "Não"], "O animal vive na água?")
    if agua == "Sim":
        respostas["vive_na_agua"] = True

    # Pergunta sobre voo apenas se tiver penas
    if respostas.get("tem_penas"):
        voa = escolher_opcao(["Sim", "Não"], "O animal pode voar?")
        if voa == "Sim":
            respostas["pode_voar"] = True

    # Pergunta sobre dieta e habitat
    respostas["dieta"] = escolher_opcao(DIETAS, "Escolha a dieta do animal:")
    respostas["habitat"] = escolher_opcao(HABITATS, "Escolha o habitat do animal:")

    # Monta a consulta Prolog
    query_parts = []

    if respostas.get("tem_pelo"):
        query_parts.append("tem_pelo(Nome)")
    if respostas.get("tem_penas"):
        query_parts.append("tem_penas(Nome)")
    if respostas.get("tem_escamas"):
        query_parts.append("tem_escamas(Nome)")
    if respostas.get("vive_na_agua"):
        query_parts.append("vive_na_agua(Nome)")
    if respostas.get("pode_voar"):
        query_parts.append("pode_voar(Nome)")
    if respostas["dieta"]:
        query_parts.append(f"animal(Nome, _, {respostas['dieta']}, _)")
    if respostas["habitat"]:
        query_parts.append(f"vive_em(Nome, {respostas['habitat']})")

    query = ", ".join(query_parts) if query_parts else "animal(Nome, _, _, _)."

    # Consulta ao Prolog
    possiveis_animais = list(prolog.query(query))

    # Remove duplicatas usando um conjunto
    animais_unicos = {resultado['Nome'].capitalize() for resultado in possiveis_animais}

    if len(animais_unicos) == 1:
        print(f"\n O animal que você está pensando é um **{list(animais_unicos)[0]}**!")
    elif len(animais_unicos) > 1:
        print("\n Os animais que correspondem às características são:")
        for nome in sorted(animais_unicos):  # Ordena para melhor legibilidade
            print(f"- {nome}")
    else:
        print("\n Nenhum animal encontrado com essas características.")

def calcular_ecossistema():
    habitat = escolher_opcao(HABITATS, "Escolha o bioma para calcular o ecossistema:")
    if habitat:
        print(f"\n📊 Relatório do Ecossistema para {habitat.capitalize()}")

        # Busca herbívoros, carnívoros e onívoros do bioma
        herbivoros = set()
        carnivoros = set()
        onivoros = set()

        # Processa cada animal na lista retornada pelo Prolog
        for resultado in prolog.query(f"herbivoros_no_bioma({habitat}, Herbivoros)"):
            if isinstance(resultado['Herbivoros'], list):  # Se for uma lista, itera sobre os itens
                herbivoros.update(resultado['Herbivoros'])
            else:
                herbivoros.add(resultado['Herbivoros'])

        for resultado in prolog.query(f"carnivoros_no_bioma({habitat}, Carnivoros)"):
            if isinstance(resultado['Carnivoros'], list):
                carnivoros.update(resultado['Carnivoros'])
            else:
                carnivoros.add(resultado['Carnivoros'])

        for resultado in prolog.query(f"onivoros_no_bioma({habitat}, Onivoros)"):
            if isinstance(resultado['Onivoros'], list):
                onivoros.update(resultado['Onivoros'])
            else:
                onivoros.add(resultado['Onivoros'])

        # Converte e ordena os nomes para exibição
        herbivoros = sorted({nome.capitalize() for nome in herbivoros})
        carnivoros = sorted({nome.capitalize() for nome in carnivoros})
        onivoros = sorted({nome.capitalize() for nome in onivoros})

        # Exibe os resultados
        print("\n Herbívoros:")
        if herbivoros:
            for nome in herbivoros:
                print(f"- {nome}")
        else:
            print("Nenhum herbívoro encontrado.")

        print("\n Carnívoros:")
        if carnivoros:
            for nome in carnivoros:
                print(f"- {nome}")
        else:
            print("Nenhum carnívoro encontrado.")

        print("\n Onívoros:")
        if onivoros:
            for nome in onivoros:
                print(f"- {nome}")
        else:
            print("Nenhum onívoro encontrado.")

        # Verifica equilíbrio ecológico
        num_herbivoros = len(herbivoros)
        num_carnivoros = len(carnivoros)

        if num_carnivoros > num_herbivoros:
            print("\n Ecossistema em desequilíbrio: Muitos predadores para poucas presas!")
        else:
            print("\n Ecossistema equilibrado!")

def menu():
    while True:
        print("\n=== Sistema de Classificação de Animais ===")
        print("1. Listar todos os animais")
        print("2. Listar animais por tipo")
        print("3. Listar animais por dieta")
        print("4. Listar animais por habitat")
        print("5. Advinhar um animal")
        print("6. Calcular ecossistema")
        print("7. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            listar_todos_animais()
        elif opcao == "2":
            listar_animais_por_tipo()
        elif opcao == "3":
            listar_animais_por_dieta()
        elif opcao == "4":
            listar_animais_por_habitat()
        elif opcao == "5":
            identificar_animal()
        elif opcao == "6":
            calcular_ecossistema()
        elif opcao == "7":
            print("Saindo...")
            break
        else:
            print("Opção inválida, tente novamente!")

# Executa o menu principal
if __name__ == "__main__":
    menu()
