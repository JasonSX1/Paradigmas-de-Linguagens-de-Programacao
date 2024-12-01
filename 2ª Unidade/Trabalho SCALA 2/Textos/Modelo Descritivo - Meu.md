## **Modelo de Aplicação: Sistema de Auditoria 5S**

### **1. Visão Geral**

A aplicação de monitoramento de auditorias para a metodologia 5S tem como objetivo garantir que os setores de uma empresa sejam auditados regularmente por todos os funcionários, promovendo organização, limpeza e disciplina. A aplicação facilita a gestão e execução de auditorias, a análise de resultados e o envio de notificações para funcionários e gestores.
Em resumo, o sistema permitirá que:

1. - Funcionários registrem auditorias facilmente por meio de um aplicativo.
2. - Gestores acompanhem os resultados das auditorias.
	(Desenvolver até isso pro trabalho de plp)
---
4. - Notificações sejam enviadas automaticamente para lembrar prazos e ações pendentes.
5. - Relatórios sejam gerados para análise contínua de desempenho e conformidade.

---

### **2. Arquitetura do Sistema**

**Componentes principais**:

- **Front-End** (Mobile e Web):
    - Interfaces amigáveis para funcionários e gestores realizarem auditorias e consultarem resultados.
- **API Gateway**:
    - Ponto central de comunicação entre o front-end e os microserviços.
- **Microserviços**:
    - **Serviço de Auditorias**: Gerencia o ciclo de vida das auditorias realizadas.
    - **Serviço de Resultados**: Consolida e disponibiliza relatórios e análises de desempenho.
---
    - **Serviço de Notificações**: Gerencia alertas e lembretes automáticos para os usuários.
	**IMPLEMENTAÇÃO POSTERIOR
	
---

### **3. Detalhamento dos Componentes**

#### **Front-End**

- **Tecnologias**: React Native (Mobile) e React.js (Web).
- **Funcionalidades**:
    - Login e autenticação dos usuários.
    - Registro e consulta de auditorias realizadas.
    - Interface para adicionar observações e anexos às auditorias.
    - Visualização de gráficos e relatórios de desempenho.
    - Notificações push para lembretes de auditorias pendentes.
- **Comunicação com a API**:
    - Consumo de endpoints REST para interagir com os microserviços.
---

#### **API Gateway**

- **Função**:
    - Centraliza e gerencia todas as requisições do front-end.
    - Simplifica a comunicação entre o front-end e os microserviços.
- **Exemplo de Rotas**:
    - `/api/auditorias` → Redireciona para o **Serviço de Auditorias**.
    - `/api/resultados` → Redireciona para o **Serviço de Resultados**.

---

#### **Microserviços**

##### **1. Serviço de Auditorias**

- **Responsabilidades**:
    - Registrar auditorias realizadas.
    - Armazenar informações sobre itens avaliados, notas e observações.
    - Gerar eventos para os demais serviços.
- **Endpoints**:
    - `POST /auditorias` → Cria uma nova auditoria.
    - `GET /auditorias/{id}` → Retorna detalhes de uma auditoria.
    - `PUT /auditorias/{id}` → Atualiza informações de uma auditoria existente.
- **Banco de Dados**:
    - Tabelas: `Auditorias` (ID, setor, responsável, data, resultados).

##### **2. Serviço de Resultados**

- **Responsabilidades**:
    - Consolida dados das auditorias realizadas.
    - Gera relatórios detalhados para análise.
- **Endpoints**:
    - `GET /resultados` → Retorna relatórios consolidados.
    - `GET /resultados/{setor}` → Detalha o desempenho de um setor específico.
- **Banco de Dados**:
    - Tabelas: `Resultados` (ID, setor, nota média, observações).


---
##### **3. Serviço de Notificações**

- **Responsabilidades**:
    - Enviar lembretes sobre auditorias pendentes.
    - Notificar gestores sobre não conformidades.
- **Funcionamento**:
    - Consumidor de eventos gerados pelo **Serviço de Auditorias**.
    - Integração com serviços de envio de notificações (push, e-mail).
- **Banco de Dados**:
    - Tabelas: `Notificacoes` (ID, usuário, tipo, status, data de envio).

---

### **4. Fluxo de Dados**
- **Registro de auditoria**:
    - O funcionário registra uma auditoria no aplicativo, enviando dados para o endpoint `/api/auditorias`.
    - A API Gateway redireciona a solicitação para o **Serviço de Auditorias**.
    - A auditoria é registrada no banco de dados e um evento "Auditoria Realizada" é emitido.
- **Consolidação de resultados**:
    - O **Serviço de Resultados** consome o evento e atualiza os relatórios.
    - O gestor pode consultar os resultados consolidados via o front-end.
- **Notificações de lembrete**:
    - O **Serviço de Notificações** consome eventos de auditorias pendentes.
    - Lembretes são enviados aos funcionários via push ou e-mail.

---

### **5. Benefícios do Modelo**

- **Escalabilidade**: Cada microserviço pode ser escalado independentemente.
- **Desacoplamento**: Os serviços são independentes, permitindo facilidade de manutenção.
- **Resiliência**: Mensagens assíncronas via Kafka garantem que eventos sejam processados mesmo com falhas temporárias.

### 6. Banco de Dados**

- Dados principais:
- Funcionários (ID, Nome, Papel, Setor).
- Auditorias (ID, Setor, Data, Status, Pontuação).
- Setores (ID, Nome, Responsável).
- Resultados (ID Auditoria, Pontuação por Pilar 5S, Observações).

# **PAPO TECNICO**

- **Auditorias** e **resultados** precisam ser separados em serviços independentes, cada um com sua responsabilidade.
- É necessário implementar **persistência** nos serviços (ex.: armazenamento de logs de auditoria ou resultados em um banco de dados).
- Deve haver uma **comunicação entre os microsserviços**, por exemplo, o microsserviço de auditoria pode consumir mensagens do microsserviço de resultados para registrar ações realizadas.

O KAFKA CONSISTE NO SERVICE DESCRIPTORE DO LAGOM PARA EXPOR AS APIS RESTFUL/ MENSAGENS ENTRE OS MICROSERVIÇOS

Exemplo: O microsserviço de auditoria consome mensagens do microsserviço de resultados sempre que um resultado é atualizado.

- **Auditoria**: Salvar logs de ações em um banco de dados.
- **Resultados**: Armazenar os dados de resultados no banco de dados.

- **Microsserviço de Auditoria:**
    - Registra ações, como "resultado criado" ou "resultado atualizado".
    - Persiste logs no banco de dados (ex.: MongoDB ou Cassandra).
- **Microsserviço de Resultados:**
    - Gerencia CRUD de resultados (criação, leitura, atualização, exclusão).
    - Expõe uma API para outros sistemas (incluindo auditoria).
- Comunicação: O microsserviço de resultados envia mensagens para o de auditoria ao realizar operações.