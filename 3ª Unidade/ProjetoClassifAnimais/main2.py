from pyswip import Prolog

# 📌 Inicialização do Prolog
prolog = Prolog()
prolog.consult("fatos_regras.pl")  # Carrega o arquivo com as regras

# Opções fixas para evitar erros de digitação
HABITATS = ["floresta", "urbano", "aquatico", "montanha", "savana", "campo", "pantanal", "oceano", "cerrado", "deserto"]
COMPORTAMENTOS = ["solitario", "social", "em_bando", "em_grupo", "independente", "noturno", "cardume"]
DIETAS = ["carnivoro", "herbivoro", "onivoro", "frugivoro", "insetivoro"]

def escolher_opcoes(lista, mensagem):
    """Mostra um menu numérico para o usuário escolher múltiplas opções."""
    print(f"\n{mensagem}")
    
    for i, opcao in enumerate(lista, 1):
        print(f"{i}. {opcao.capitalize()}")

    escolhas = input("Escolha os números das características separadas por vírgula: ")
    opcoes_escolhidas = []
    
    try:
        indices = [int(i) for i in escolhas.split(",")]
        opcoes_escolhidas = [lista[i - 1] for i in indices if 1 <= i <= len(lista)]
    except ValueError:
        print("Entrada inválida! Tente novamente.")

    return opcoes_escolhidas

def identificar_animal():
    """Obtém características do usuário e consulta o Prolog."""
    print("\n🔎 **Identificação de Animal**")
    
    habitat = escolher_opcoes(HABITATS, "Escolha o habitat do animal:")
    comportamento = escolher_opcoes(COMPORTAMENTOS, "Escolha o comportamento do animal:")
    dieta = escolher_opcoes(DIETAS, "Escolha a dieta do animal:")

    # Monta a lista de características
    respostas = habitat + comportamento + dieta
    respostas_formatadas = "[" + ", ".join([f"'{r}'" for r in respostas]) + "]"

    # 🛠️ Debug: Verificar a consulta que será enviada ao Prolog
    print(f"\n🛠️ DEBUG - Consulta formatada: identificar_animal({respostas_formatadas}, AnimaisComProbabilidades)")

    # Consulta ao Prolog
    consulta = f"identificar_animal({respostas_formatadas}, AnimaisComProbabilidades)"
    resultado = list(prolog.query(consulta))

    # 🛠️ Debug: Verificar a resposta bruta do Prolog
    print(f"\n🛠️ DEBUG - Resposta Prolog: {resultado}")

    if resultado and "AnimaisComProbabilidades" in resultado[0]:
        animais_probabilidades = resultado[0]["AnimaisComProbabilidades"]

        print("\n🎯 **Animais prováveis:**")
        for item in animais_probabilidades:
            if isinstance(item, tuple) and len(item) == 2:
                animal, probabilidade = item
                print(f"- {animal.capitalize()} (Probabilidade: {probabilidade:.2f}%)")
            else:
                print(f"⚠️ Formato inesperado encontrado: {item}")
    else:
        print("\n⚠️ Nenhum animal encontrado com essas características.")

def menu():
    """Menu principal do sistema."""
    while True:
        print("\n=== 🦁 **Sistema de Classificação de Animais** ===")
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
