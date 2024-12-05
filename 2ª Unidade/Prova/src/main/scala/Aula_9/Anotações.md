**Def** - Definição de função - Não suporta diretamente a recursão, pode ser passada como argumento para outras funções e armazenada em EDs como Listas
* Ideal para funções simples e curtas
* Imutável ao ser definido com val
* Sempre inferido dinamicamente, mais propenso a erros

**Val** - Função Armazenada 
* Menos flexível, pois é é necessário a conversão da função como valor usando _,para uso como cidadão de primeira ordem
* Suporta recursão
* Mais claro para funções complexas
* def pode ser redefinido em subclasses
* Pode ser inferido,mas em geral é mais seguro