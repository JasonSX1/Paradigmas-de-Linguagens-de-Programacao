from pyswip import Prolog

# Inicialização do Prolog
prolog = Prolog()
prolog.consult("animais.pl")  # Certifique-se de que este arquivo existe

# Opções fixas para evitar erros de digitação
TIPOS = ["mamifero", "reptil", "ave", "anfibio"]
DIETAS = ["carnivoro", "herbivoro", "insetivoro"]
HABITATS = ["savana", "floresta", "pantano", "montanhas", "antartida", "domestico"]

def escolher_opcao(lista, mensagem):
    """Mostra um menu numérico para o usuário escolher uma opção."""
    opcoes = lista + ["Não sei"]  # Adiciona "Não sei" automaticamente uma única vez
    print(f"\n{mensagem}")
    
    for i, opcao in enumerate(opcoes, 1):
        print(f"{i}. {opcao.capitalize()}")
    
    while True:
        try:
            escolha = int(input("Escolha uma opção: "))
            if 1 <= escolha <= len(lista):
                return lista[escolha - 1]  # Retorna a opção escolhida
            elif escolha == len(opcoes):  # Última opção ("Não sei")
                return None
        except ValueError:
            pass
        print("Opção inválida. Escolha um número da lista.")


def listar_animais_por_tipo():
    tipo = escolher_opcao(TIPOS, "Escolha o tipo de animal:")
    if tipo:
        print(f"\nAnimais do tipo {tipo}:")
        for resultado in prolog.query(f"animal_por_categoria(Nome, {tipo})"):
            print(f"- {resultado['Nome'].capitalize()}")

def listar_animais_por_dieta():
    dieta = escolher_opcao(DIETAS, "Escolha a dieta do animal:")
    if dieta:
        print(f"\nAnimais com dieta {dieta}:")
        for resultado in prolog.query(f"animal_por_dieta(Nome, {dieta})"):
            print(f"- {resultado['Nome'].capitalize()}")

def listar_animais_por_habitat():
    habitat = escolher_opcao(HABITATS, "Escolha o habitat do animal:")
    if habitat:
        print(f"\nAnimais que vivem em {habitat}:")
        for resultado in prolog.query(f"animal_por_habitat(Nome, {habitat})"):
            print(f"- {resultado['Nome'].capitalize()}")

def adicionar_animal():
    nome = input("Nome do animal: ").strip().lower()
    tipo = escolher_opcao(TIPOS, "Escolha o tipo do animal:")
    dieta = escolher_opcao(DIETAS, "Escolha a dieta do animal:")
    habitat = escolher_opcao(HABITATS, "Escolha o habitat do animal:")

    if tipo and dieta and habitat:
        prolog.assertz(f"animal({nome}, {tipo}, {dieta}, terrestre)")
        prolog.assertz(f"vive_em({nome}, {habitat})")
        print(f"\n {nome.capitalize()} adicionado com sucesso!")

def identificar_animal():
    print("\n=== Modo Akinator ===")

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
    agua = escolher_opcao(["Sim", "Não", "Não sei"], "O animal vive na água?")
    if agua == "Sim":
        respostas["vive_na_agua"] = True

    # Pergunta sobre voo apenas se tiver penas
    if respostas.get("tem_penas"):
        voa = escolher_opcao(["Sim", "Não", "Não sei"], "O animal pode voar?")
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


def menu():
    while True:
        print("\n=== Sistema de Classificação de Animais ===")
        print("1. Listar animais por tipo")
        print("2. Listar animais por dieta")
        print("3. Listar animais por habitat")
        print("4. Adicionar um novo animal")
        print("5. Advinhar um animal")
        print("6. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            listar_animais_por_tipo()
        elif opcao == "2":
            listar_animais_por_dieta()
        elif opcao == "3":
            listar_animais_por_habitat()
        elif opcao == "4":
            adicionar_animal()
        elif opcao == "5":
            identificar_animal()
        elif opcao == "6":
            print("Saindo...")
            break
        else:
            print("Opção inválida, tente novamente!")

# Executa o menu principal
if __name__ == "__main__":
    menu()
