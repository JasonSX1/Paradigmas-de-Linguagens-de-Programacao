from pyswip import Prolog

# Inicialização do Prolog
prolog = Prolog()
prolog.consult("fatos_regras.pl")  # Carrega o arquivo com as regras

def obter_caracteristicas(prolog, consulta):
    """Consulta o Prolog e retorna uma lista de características."""
    resultado = list(prolog.query(consulta))
    if resultado:
        return resultado[0][list(resultado[0].keys())[0]]  # Pega o primeiro valor retornado
    return []

# Obtém as listas diretamente do Prolog
HABITATS = obter_caracteristicas(prolog, "listar_habitats(Habitats)")
COMPORTAMENTOS = obter_caracteristicas(prolog, "listar_comportamentos(Comportamentos)")
DIETAS = obter_caracteristicas(prolog, "listar_dietas(Dietas)")

def escolher_opcoes(lista, mensagem):
    """Mostra um menu numérico para o usuário escolher múltiplas opções."""
    while True:
        print(f"\n{mensagem}")
        
        for i, opcao in enumerate(lista, 1):
            print(f"{i}. {opcao.capitalize()}")

        escolhas = input("Escolha o número das características: ").strip()
        if not escolhas:  # Verifica se a entrada está vazia
            print("Entrada não pode ser vazia! Tente novamente.")
            continue

        try:
            indices = [int(i) for i in escolhas.split(",") if i.strip().isdigit()]
            opcoes_escolhidas = [lista[i - 1] for i in indices if 1 <= i <= len(lista)]
            
            if opcoes_escolhidas:  # Verifica se pelo menos uma opção foi escolhida
                return opcoes_escolhidas
            else:
                print("Nenhuma opção válida selecionada! Tente novamente.")
        
        except ValueError:
            print("Entrada inválida! Digite apenas números separados por vírgula.")


def identificar_animal():
    """Obtém características do usuário e consulta o Prolog."""
    print("\n**Identificação de Animal**")
    
    habitat = escolher_opcoes(HABITATS, "Escolha o habitat do animal:")
    comportamento = escolher_opcoes(COMPORTAMENTOS, "Escolha o comportamento do animal:")
    dieta = escolher_opcoes(DIETAS, "Escolha a dieta do animal:")

    # Monta a lista de características
    respostas = habitat + comportamento + dieta
    respostas_formatadas = "[" + ", ".join(respostas) + "]"

    # Consulta ao Prolog
    consulta = f"identificar_animal({respostas_formatadas}, AnimaisComProbabilidades)"

    resultado = list(prolog.query(consulta))

    if resultado and "AnimaisComProbabilidades" in resultado[0]:
        animais_probabilidades = resultado[0]["AnimaisComProbabilidades"]

    print("\n **Animais prováveis:**")
    for item in animais_probabilidades:
        # Limpar espaços em branco e caracteres indesejados
        item = item.strip().strip(",")  # Remove a vírgula inicial e espaços extras

        # Remover parênteses e dividir pelo último espaço para separar o animal da probabilidade
        if "(" in item and ")" in item:
            item = item.replace("(", "").replace(")", "")  # Remove parênteses
            partes = item.rsplit(",", 1)  # Divide pelo último espaço encontrado
            
            if len(partes) == 2:
                animal = partes[0].strip()
                try:
                    probabilidade = float(partes[1].strip())  # Converte a probabilidade para float
                    if probabilidade > 0:  # Só exibe se a probabilidade for maior que 0.00%
                        print(f"- {animal.capitalize()} (Probabilidade: {probabilidade:.2f}%)")
                except ValueError:
                    print(f" Erro ao processar probabilidade para: {item}")
            else:
                print(f" Formato inesperado encontrado: {item}")
        else:
            print(f" Formato inesperado encontrado: {item}")

def menu():
    """Menu principal do sistema."""
    while True:
        print("\n=== **Sistema de Classificação de Animais** ===")
        print("1. Identificar um animal")
        print("2. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            identificar_animal()
        elif opcao == "2":
            print("Saindo...")
            break
        else:
            print("Opção inválida, tente novamente!")

# Executa o menu principal
if __name__ == "__main__":
    menu()
