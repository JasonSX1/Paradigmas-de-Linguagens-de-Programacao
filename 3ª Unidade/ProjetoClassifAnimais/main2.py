from pyswip import Prolog

prolog = Prolog() # Inicialização do Prolog
prolog.consult("fatos_regras.pl")  # Carrega o arquivo com as regras

def obter_caracteristicas(prolog, consulta):
    """Consulta o Prolog e retorna uma lista de características."""
    resultado = list(prolog.query(consulta))
    if resultado:
        lista = resultado[0][list(resultado[0].keys())[0]]
        return [item.replace("_", " ") for item in lista] + ["Não sei"]
    return ["Não sei"]  # Se der erro, retorna apenas "Não sei"

# Obtém listas dinamicamente do Prolog
HABITATS = obter_caracteristicas(prolog, "listar_habitats(Habitats)")
COMPORTAMENTOS = obter_caracteristicas(prolog, "listar_comportamentos(Comportamentos)")
DIETAS = obter_caracteristicas(prolog, "listar_dietas(Dietas)")

def escolher_opcoes(lista, mensagem):
    """Mostra um menu numérico para o usuário escolher uma opção válida."""
    while True:
        print(f"\n{mensagem}")
        print("0. Voltar")  # Opção para voltar
        for i, opcao in enumerate(lista, 1):
            print(f"{i}. {opcao.capitalize()}")

        escolha = input("Escolha uma opção: ").strip()

        if escolha == "0":
            return None  # Retorna None para indicar que o usuário quer voltar
        
        if escolha.isdigit():
            indice = int(escolha)
            if 1 <= indice <= len(lista):
                return lista[indice - 1]  # Retorna a opção escolhida

        print("Entrada inválida! Escolha um número válido.")

def identificar_animal():
    """Obtém características do usuário e consulta o Prolog."""
    print("\n=== Identificação de Animal ===")

    habitat = escolher_opcoes(HABITATS, "Escolha o habitat do animal:")
    if habitat is None: return  # Se o usuário escolher voltar, sai da função

    comportamento = escolher_opcoes(COMPORTAMENTOS, "Escolha o comportamento do animal:")
    if comportamento is None: return  

    dieta = escolher_opcoes(DIETAS, "Escolha a dieta do animal:")
    if dieta is None: return  

    # Filtra "Não sei" para não enviar valores inválidos ao Prolog
    respostas = [habitat, comportamento, dieta]
    respostas = [r for r in respostas if r != "Não sei"]  # Remove "Não sei"

    if not respostas:  # Se todas as respostas forem "Não sei"
        print("\n Você não selecionou nenhuma característica válida.")
        return
    
    # Substituir espaços por underlines e adicionar aspas para evitar erro no Prolog
    respostas_formatadas = "[" + ", ".join([f"'{r.replace(' ', '_')}'" for r in respostas]) + "]"

    # Consulta ao Prolog
    consulta = f"identificar_animal({respostas_formatadas}, AnimaisComProbabilidades)"
    
    try:
        resultado = list(prolog.query(consulta))
    except Exception as e:
        print(f"\n Erro ao consultar o Prolog: {e}")
        return

    # Verificar e exibir os resultados da query
    if resultado and "AnimaisComProbabilidades" in resultado[0]:
        animais_probabilidades = resultado[0]["AnimaisComProbabilidades"]
    else:
        animais_probabilidades = []

    print("\n=== Animais prováveis ===")
    if not animais_probabilidades:
        print("Nenhum animal encontrado com essas características.")
        return

    for item in animais_probabilidades:
        item = item.strip().strip(",")  # Remove espaços extras e vírgulas indesejadas

        # Remove parênteses da string recebida pela query no prolog
        if item.startswith("(") and item.endswith(")"):
            item = item[1:-1]  # Removendo o primeiro e o último caractere (Os parênteses)

        partes = item.rsplit(",", 1)  # Divide a string na última vírgula, para separar o animal da porcentagem

        if len(partes) == 2:
            animal = partes[0].strip()
            try:
                probabilidade = float(partes[1].strip())  # Converte a probabilidade para float
                if probabilidade > 0:  # Apenas exibe animais com probabilidade maior que 0%
                    print(f"- {animal.capitalize()} (Probabilidade: {probabilidade:.2f}%)")
            except ValueError:
                continue  # Ignora qualquer erro de conversão silenciosamente


def listar_todos_animais():
    """Lista todos os animais cadastrados na base de dados com suas características."""
    animais = list(prolog.query("animal(Nome, Caracteristicas)"))  # Busca nome e características

    if not animais:
        print("\nNenhum animal encontrado no banco de dados.")
        return

    print(f"\nExistem {len(animais)} animais cadastrados:\n")

    for resultado in animais:
        nome = resultado["Nome"].capitalize()
        caracteristicas = resultado["Caracteristicas"] 

        # Inicializa valores padrão caso não sejam encontrados
        habitat = "Desconhecido"
        comportamento = "Desconhecido"
        dieta = "Desconhecido"

        # Extraindo os valores
        for c in caracteristicas:
            if "(" in c and ")" in c:  # Garante que a string tem um formato válido
                chave, valor = c.split("(", 1)  # Divide na primeira ocorrência de "("
                valor = valor.rstrip(")")  # Remove o ")" do final

                if chave == "habitat":
                    habitat = valor.replace("_", " ")
                elif chave == "comportamento":
                    comportamento = valor.replace("_", " ")
                elif chave == "dieta":
                    dieta = valor.replace("_", " ")

        print(f"- {nome}")
        print(f"  Habitat: {habitat.capitalize()}")
        print(f"  Comportamento: {comportamento.capitalize()}")
        print(f"  Dieta: {dieta.capitalize()}")
        print()

def listar_animais_por_caracteristica(consulta_prolog, lista_opcoes, mensagem):
    """Lista animais com base em um critério específico (dieta, habitat ou comportamento)."""
    escolha = escolher_opcoes(lista_opcoes, mensagem)
    if escolha is None:
        return  # Se o usuário escolheu voltar, sai da função

    print(f"\nAnimais que correspondem ao critério '{escolha}':")

    try:
        # Formatar a escolha para corresponder ao formato esperado no Prolog
        escolha_formatada = escolha.replace(" ", "_")

        # Executar consulta no Prolog
        animais = list(prolog.query(f"{consulta_prolog}('{escolha_formatada}', Nome)"))

        if animais:
            for resultado in animais:
                print(f"- {resultado['Nome'].capitalize()}")
        else:
            print("Nenhum animal encontrado para este critério.")
    except Exception as e:
        print(f"\nErro ao consultar o Prolog: {e}")

def listar_animais_por_dieta():
    """Lista animais por dieta."""
    listar_animais_por_caracteristica("animal_por_dieta", DIETAS, "Escolha a dieta do animal:")

def listar_animais_por_habitat():
    """Lista animais por habitat."""
    listar_animais_por_caracteristica("animal_por_habitat", HABITATS, "Escolha o habitat do animal:")

def listar_animais_por_comportamento():
    """Lista animais por comportamento."""
    listar_animais_por_caracteristica("animal_por_comportamento", COMPORTAMENTOS, "Escolha o comportamento do animal:")

def modo_akinator():
    """Tenta adivinhar um animal com base nas respostas do usuário."""
    print("\n=== Modo Akinator ===")

    respostas = []

    # Pergunta sobre características físicas (exclusivas entre si)
    tem_pelo = escolher_opcoes(["Sim", "Não"], "O animal tem pelo?")
    if tem_pelo is None:
        return  # Volta para o menu principal
    if tem_pelo == "Sim":
        respostas.append("tem_pelo")
    else:
        tem_penas = escolher_opcoes(["Sim", "Não"], "O animal tem penas?")
        if tem_penas is None:
            return
        if tem_penas == "Sim":
            respostas.append("tem_penas")
        else:
            tem_escamas = escolher_opcoes(["Sim", "Não"], "O animal tem escamas?")
            if tem_escamas is None:
                return
            if tem_escamas == "Sim":
                respostas.append("tem_escamas")

    # Pergunta sobre outras características
    vive_na_agua = escolher_opcoes(["Sim", "Não"], "O animal vive na água?")
    if vive_na_agua is None:
        return
    if vive_na_agua == "Sim":
        respostas.append("vive_na_agua")

    pode_voar = escolher_opcoes(["Sim", "Não"], "O animal pode voar?")
    if pode_voar is None:
        return
    if pode_voar == "Sim":
        respostas.append("pode_voar")

    bota_ovos = escolher_opcoes(["Sim", "Não"], "O animal bota ovos?")
    if bota_ovos is None:
        return
    if bota_ovos == "Sim":
        respostas.append("bota_ovos")

    # Pergunta sobre dieta e habitat
    dieta = escolher_opcoes(DIETAS, "Escolha a dieta do animal:")
    if dieta is None:
        return
    if dieta != "Não sei":
        respostas.append(f"dieta({dieta.replace(' ', '_')})")

    habitat = escolher_opcoes(HABITATS, "Escolha o habitat do animal:")
    if habitat is None:
        return
    if habitat != "Não sei":
        respostas.append(f"habitat({habitat.replace(' ', '_')})")

    # Verifica se há respostas válidas
    if not respostas:
        print("\nNenhuma característica foi selecionada.")
        return

    # Formata a consulta para o Prolog
    respostas_formatadas = "[" + ", ".join(respostas) + "]"

    # Consulta ao Prolog
    consulta = f"adivinhar_animal({respostas_formatadas}, AnimaisPossiveis)"
    
    try:
        resultado = list(prolog.query(consulta))
    except Exception as e:
        print(f"\nErro ao consultar o Prolog: {e}")
        return

    if resultado and "AnimaisPossiveis" in resultado[0]:
        animais_possiveis = resultado[0]["AnimaisPossiveis"]
    else:
        animais_possiveis = []

    print("\n=== Resultado ===")
    if not animais_possiveis:
        print("Nenhum animal encontrado com essas características.")
    elif len(animais_possiveis) == 1:
        print(f"\nO animal que você está pensando é um **{animais_possiveis[0].capitalize()}**!")
    else:
        print("\nOs possíveis animais são:")
        for animal in animais_possiveis:
            print(f"- {animal.capitalize()}")


def calcular_ecossistema():
    """Consulta o Prolog para calcular a biodiversidade de um habitat e verificar seu equilíbrio."""
    habitat = escolher_opcoes(HABITATS, "Escolha o habitat para calcular o ecossistema:")
    if habitat is None:
        return  # Se o usuário escolheu voltar, sai da função

    print(f"\n Relatório do Ecossistema para {habitat.capitalize()}")

    # Busca herbívoros, carnívoros e onívoros do bioma
    try:
        herbivoros = list(prolog.query(f"herbivoros_no_bioma('{habitat}', Herbivoros)"))[0].get("Herbivoros", [])
        carnivoros = list(prolog.query(f"carnivoros_no_bioma('{habitat}', Carnivoros)"))[0].get("Carnivoros", [])
        onivoros = list(prolog.query(f"onivoros_no_bioma('{habitat}', Onivoros)"))[0].get("Onivoros", [])
        equilibrio = list(prolog.query(f"equilibrio_ecossistema('{habitat}', Estado)"))[0].get("Estado", "Indefinido")

        # Conversão para string normal removendo bytes, se necessário
        if isinstance(equilibrio, bytes):
            equilibrio = equilibrio.decode("utf-8")

    except Exception as e:
        print(f"\nErro ao consultar o Prolog: {e}")
        return

    # Converte e ordena os nomes para exibição
    herbivoros = sorted({nome.capitalize() for nome in herbivoros})
    carnivoros = sorted({nome.capitalize() for nome in carnivoros})
    onivoros = sorted({nome.capitalize() for nome in onivoros})

    # Exibe os resultados
    print("\nHerbívoros:")
    if herbivoros:
        for nome in herbivoros:
            print(f"- {nome}")
    else:
        print("Nenhum herbívoro encontrado.")

    print("\nCarnívoros:")
    if carnivoros:
        for nome in carnivoros:
            print(f"- {nome}")
    else:
        print("Nenhum carnívoro encontrado.")

    print("\nOnívoros:")
    if onivoros:
        for nome in onivoros:
            print(f"- {nome}")
    else:
        print("Nenhum onívoro encontrado.")

    # Verifica equilíbrio ecológico
    print(f"\n Estado do ecossistema: {equilibrio}")

def menu():
    """Menu do programa."""
    while True:
        print("\n=== Sistema de Classificação de Animais ===")
        print("1. Identificar um animal")
        print("2. Listar todos os animais")
        print("3. Listar animais por dieta")
        print("4. Listar animais por habitat")
        print("5. Listar animais por comportamento")
        print("6. Modo Akinator")
        print("7. Calcular Ecossistema")
        print("8. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            identificar_animal()
        elif opcao == "2":
            listar_todos_animais()
        elif opcao == "3":
            listar_animais_por_dieta()
        elif opcao == "4":
            listar_animais_por_habitat()
        elif opcao == "5":
            listar_animais_por_comportamento()
        elif opcao == "6":
            modo_akinator()
        elif opcao == "7":
            calcular_ecossistema()
        elif opcao == "8":
            print("Saindo...")
            break
        else:
            print("Opção inválida, tente novamente!")

# Executa o menu principal
if __name__ == "__main__":
    menu()
