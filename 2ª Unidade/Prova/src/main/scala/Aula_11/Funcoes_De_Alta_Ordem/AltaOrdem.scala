package Aula_11.Funcoes_De_Alta_Ordem

//************* DEFINIÇÃO DOS DADOS
case class Funcionario(
                        nome: String,
                        salario: Double,
                        cargo: Cargo.Value,
                        departamentos: List[Departamento.Value]
                      )
case class Produto(
                    nome: String,
                    preco: Double,
                    estoque: Int
                  )
case class Cliente(
                    nome: String,
                    totalGasto: Double
                  )
case class Venda(
                  data: String,
                  valor: Double
                )
case class Projeto(
                    nome: String,
                    participantes: List[Funcionario]
                  )
//Tipos Enumerados
object Cargo extends Enumeration {
  type Cargo = Value
  val EngenheiroSoftware, GerenteProjetos, AnalistaNegocios, Desenvolvedor = Value
}
object Departamento extends Enumeration {
  type Departamento = Value
  val TI, Vendas, RH, Marketing, Financas = Value
}
object mainAltaOrdem extends App {
  //************* BASE DE DADOS
  // Lista de Funcionários
  val funcionarios = List(
    Funcionario("Alice", 7500.0, Cargo.EngenheiroSoftware, List(Departamento.TI)),
    Funcionario("Bob", 12000.0, Cargo.GerenteProjetos, List(Departamento.TI,
      Departamento.Financas)),

    Funcionario("Carlos", 6000.0, Cargo.AnalistaNegocios, List(Departamento.Vendas,
      Departamento.Marketing)),
    Funcionario("Diana", 5000.0, Cargo.Desenvolvedor, List(Departamento.TI,
      Departamento.Marketing)),
    Funcionario("Elena", 4500.0, Cargo.AnalistaNegocios, List(Departamento.RH)),
    Funcionario("Fernando", 10000.0, Cargo.EngenheiroSoftware, List(Departamento.TI,
      Departamento.Financas)),
    Funcionario("Gabriel", 5400.0, Cargo.AnalistaNegocios, List(Departamento.Vendas,
      Departamento.Marketing)),
    Funcionario("Helena", 10200.0, Cargo.GerenteProjetos, List(Departamento.Financas))
  )
  // Lista de Produtos
  val produtos = List(
    Produto("Notebook", 4500.0, 15),
    Produto("Smartphone", 2500.0, 30),
    Produto("Cadeira de Escritório", 800.0, 20),
    Produto("Teclado Mecânico", 300.0, 50),
    Produto("Monitor", 1200.0, 0)
  )
  // Lista de Clientes
  val clientes = List(
    Cliente("Lucas", 8500.0),
    Cliente("Mariana", 12000.0),
    Cliente("José", 4500.0),
    Cliente("Fernanda Abreu", 6000.0),
    Cliente("Roberta", 3500.0)
  )
  // Lista de Vendas
  val vendas = List(
    Venda("2024-01-01", 300.0),
    Venda("2024-02-15", 4500.0),
    Venda("2024-03-10", 2500.0),
    Venda("2024-04-20", 5200.0),
    Venda("2024-05-05", 4800.0)
  )
  // Lista de Projetos
  val projetos = List(
    Projeto("Sistema ERP", List(funcionarios(0), funcionarios(1), funcionarios(3))),
    Projeto("App de Vendas", List(funcionarios(4), funcionarios(2), funcionarios(7))),
    Projeto("Integração de Marketing", List(funcionarios(5), funcionarios(2), funcionarios(6))),
    Projeto("Portal de RH", List(funcionarios(4), funcionarios(7), funcionarios(1)))
  )
  // Meses

  val meses = List("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
    "Setembro", "Outubro", "Novembro", "Dezembro")
  // Faturamento Mensal
  val faturamentoMensal = List(3000.0, 4500.0, 2500.0, 5200.0, 4800.0, 5000.0, 6000.0,
    7000.0, 8000.0, 9000.0, 10000.0, 11000.0)


  // Questão 01 Dada uma lista de funcionários com seus salários, retorne uma lista contendo
  //apenas aqueles que recebem acima de um valor especificado (por exemplo, R$
  //10000,00). (Usar filter)

//  println("************* Questão 01 *************")
//
//  funcionarios.filter(_.salario > 7000.0 ).foreach(println)
//
//  val salarioLimite = 7000.0
//  val funcionariosSalarioAlto = funcionarios.filter(x => x.salario > salarioLimite)
//  funcionariosSalarioAlto.foreach(println)

//  // Questão 02 Dada uma lista de funcionários e seus salários, aplique um aumento de X% a todos
//  //e retorne a lista atualizada (por exemplo, 10%). (Usar map e copy)
//  println("************* Questão 02 *************")
//
//  val aumentoSalarialPercentual = 10 //10% de reajuste salarial
//  val funcionariosSalariosAumentados = funcionarios.map(x => x.copy(salario = x.salario *(100.0 + aumentoSalarialPercentual))).foreach(println)

//  // Questão 03 Dada uma lista de vendas mensais, calcule o total de vendas. (Usar map e reduce
//  //ou fold)
//  println("************* Questão 03 *************")
//
//  val totalVendas = vendas.map(x => x.valor).reduce((acc, valor) => acc + valor)
//  println(totalVendas)

  // Questão 04 Dada uma lista de produtos, cada um com uma contagem em estoque, retorne a
  //quantidade total de produtos disponíveis. (Usar count)

//  val totalProdutosDisponiveis = produtos.count(_.estoque > 0)
//  println(s"Quantidade total de produtos disponíveis: $totalProdutosDisponiveis")
//  val totalEstoque = produtos.map(_.estoque).sum
//  println(s"Quantidade total de unidades em estoque: $totalEstoque")


//5. Verifique se a lista de produtos contém algum item cujo estoque é zero. (Usar exists)

//  val estoqueZerado = produtos.exists(_.estoque == 0)
//  println(estoqueZerado)

//Dada uma lista de clientes com seus nomes, filtre e retorne apenas aqueles cujos
  //nomes têm mais de X caracteres (por exemplo, 8 caracteres). (Usar filter)

//  val qtdCaracteres = 5
//  val nomesFiltrados = clientes.filter(_.nome.length > qtdCaracteres).foreach(println)

  //7. Dada uma lista de faturamentos mensais, calcule o faturamento anual total da
  //empresa. (Usar reduce ou fold)

//  val faturamentosAnuais = faturamentoMensal.reduce((x,y) => x+y)
//  println(faturamentosAnuais)

  //8. Dada uma lista de nomes de produtos, retorne apenas os que contêm uma letra
  //específica (por exemplo, letra 'e'). (Usar filter)

//   val nomesFiltrados = produtos.filter(_.nome.contains("e"))
//   println(nomesFiltrados)

  ////Dada uma lista de produtos com contagem de estoque, dobre o estoque de cada um
  //e calcule o total geral de produtos em estoque. (Usar map e sum)

//  val produtosDobrados = produtos.map(_.estoque * 2).sum()
//  println(produtosDobrados)

  //Dada uma lista de valores de faturamento mensal, imprima cada valor com uma
  //indicação do mês (ex: "Janeiro: R$ 10.000,00"). (Usar foreach)

  //val mesesCasados =

  //20 - 20. Dada uma lista de produtos com contagem de estoque, retorne uma lista que
  //contém apenas os produtos disponíveis. (Usar filter)

  //val produtosFiltrados = produtos.filter(_.estoque>0)


  //21 - Dado um faturamento mensal, crie uma lista onde cada elemento representa o
  //faturamento acumulado até aquele mês. (Usar scanLeft)

//  val faturamentoAcumulado = faturamentoMensal.scanLeft(0.0)((acc, valor) => valor + acc)
//
//  faturamentoAcumulado.foreach(println)

  //Dada uma lista de funcionários e seus cargos, conte quantos funcionários possuem
  //um cargo específico (ex: "Engenheiro de Software"). (Usar count)

//  val funcionariosCargo = funcionarios.count(_.cargo==Cargo.EngenheiroSoftware)

//23. Dada uma lista de valores de compra, calcule o produto das vendas até encontrar
  //uma transação com valor menor que um valor determinado (por exemplo, R$
  //500,00). (Usar takeWhile, map e product)

//  val venda500 = 500
//  val produtoVendasAteLimite = vendas.takeWhile(x => x.valor >= venda500).map(y => y.valor).product
//  println(f"$produtoVendasAteLimite%.2f")

  //24. Dada uma lista de faturamento mensal, calcule a soma dos valores na ordem
  //inversa (do último mês ao primeiro). (Usar foldRight)

//  val somaFaturamentos = faturamentoMensal.foldRight(0.0)((acc, valor) => acc + valor)

  //25. Dada uma lista de produtos e seus estoques, filtre os produtos disponíveis e calcule
  //o produto do estoque desses itens. (Usar filter, map e reduce)

//  val vendasFiltradas = produtos.filter(_.estoque>0).map(_.estoque).reduce((acc, valor) => acc * valor)

  //26. Dada uma lista de vendas mensais, retorne o valor mais alto de venda registrado.
  //(Usar map e reduce)

//  val vendaMaisAlta = vendas.map(_.valor).reduce((acc, valor) => if(valor>acc) valor else acc )
//  println(vendaMaisAlta)

  //Dadas duas listas de faturamento mensal, verifique se elas têm o mesmo
  //comprimento (ou seja, ambas contêm 12 valores). (Usar zip e forall)

  //28. Dada uma lista de valores de faturamento mensal, imprima cada valor com uma
  //indicação do mês (ex: "Janeiro: R$ 10.000,00"). (Usar foreach)

//  val listaconcatenada = meses.zip(faturamentoMensal).foreach(println)
}