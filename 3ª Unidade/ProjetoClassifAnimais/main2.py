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
    
    # Substituir espaços por underscores e adicionar aspas para evitar erro no Prolog
    respostas_formatadas = "[" + ", ".join([f"'{r.replace(' ', '_')}'" for r in respostas]) + "]"

    # Consulta ao Prolog
    consulta = f"identificar_animal({respostas_formatadas}, AnimaisComProbabilidades)"
    
    try:
        resultado = list(prolog.query(consulta))
    except Exception as e:
        print(f"\n Erro ao consultar o Prolog: {e}")
        return

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
            item = item[1:-1]  # Removendo o primeiro e o último caractere

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

def menu():
    """Menu do programa."""
    while True:
        print("\n=== Sistema de Classificação de Animais ===")
        print("1. Identificar um animal")
        print("2. Listar todos os animais")
        print("3. Listar animais por dieta")
        print("4. Listar animais por habitat")
        print("5. Listar animais por comportamento")
        print("6. Sair")

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
            print("Saindo...")
            break
        else:
            print("Opção inválida, tente novamente!")



# Executa o menu principal
if __name__ == "__main__":
    menu()
