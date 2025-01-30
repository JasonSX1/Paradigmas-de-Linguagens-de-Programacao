from pyswip import Prolog

# Inicialização do Prolog
prolog = Prolog()
prolog.consult("animais.pl")  # Certifique-se de que este arquivo existe

# Função para classificar se um animal é mamífero
def classificar_animal(nome):
    query = f"eh_mamifero({nome})"  # Passando o nome do animal corretamente
    resultados = list(prolog.query(query))

    if resultados:
        print(f"{nome.capitalize()} é um mamífero!")
    else:
        print(f"{nome.capitalize()} não é um mamífero ou não está registrado.")

if __name__ == "__main__":
    nome_animal = input("Digite o nome do animal: ").lower()  # Entrada do usuário
    classificar_animal(nome_animal)
