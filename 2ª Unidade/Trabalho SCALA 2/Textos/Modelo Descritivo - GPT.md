## **Modelo de Aplicação: Sistema de Gerenciamento de Pedidos para Restaurantes**

### **1. Visão Geral**

O sistema permitirá que:

1. Clientes façam pedidos de itens no cardápio.
2. O restaurante gerencie pedidos em tempo real.
3. Funcionários atualizem o status dos pedidos (ex.: "Preparando", "Pronto", "Entregue").
4. Notificações sejam enviadas para os clientes quando o pedido estiver pronto.

---

### **2. Arquitetura do Sistema**

**Componentes principais**:

1. **Front-End** (Aplicação Web/SPA ou Mobile App):
    - Interface que os clientes e funcionários utilizam para interagir com o sistema.
2. **API Gateway**:
    - Exposição de um ponto único para comunicação entre o front-end e os microserviços.
3. **Microserviços**:
    - **Serviço de Pedidos**: Responsável por gerenciar o ciclo de vida dos pedidos.
    - **Serviço de Cardápio**: Fornece informações sobre itens do cardápio (ex.: descrição, preço).
    - **Serviço de Notificações**: Envia notificações push ou por e-mail para os clientes.

---

### **3. Detalhamento dos Componentes**

#### **Front-End**

- **Tecnologias**: React, Angular, Vue.js (para web) ou Flutter/React Native (para mobile).
- **Funcionalidades**:
    - **Cliente**:
        - Visualiza itens do cardápio.
        - Seleciona itens e envia pedidos.
        - Recebe atualizações do status do pedido.
    - **Funcionário**:
        - Visualiza pedidos em andamento.
        - Atualiza o status dos pedidos.
- **Comunicação com a API**:
    - Envia solicitações HTTP (ex.: POST para criar pedidos, GET para listar pedidos).

---

#### **API Gateway**

- **Função**:
    - Atua como intermediário entre o front-end e os microserviços.
    - Unifica os endpoints dos microserviços, fornecendo uma interface simplificada.
- **Exemplo de Rotas**:
    - `/api/pedidos` → Redireciona para o **Serviço de Pedidos**.
    - `/api/cardapio` → Redireciona para o **Serviço de Cardápio**.
    - `/api/notificacoes` → Redireciona para o **Serviço de Notificações**.

---

#### **Microserviços**

##### **1. Serviço de Pedidos**

- **Responsabilidades**:
    - Receber novos pedidos do cliente.
    - Armazenar informações no banco de dados.
    - Emitir eventos de atualização (ex.: "Pedido criado", "Pedido atualizado").
- **Endpoints**:
    - `POST /pedidos` → Cria um novo pedido.
    - `GET /pedidos/{id}` → Retorna informações de um pedido.
    - `PUT /pedidos/{id}` → Atualiza o status do pedido.
- **Banco de Dados**:
    - Tabelas: `Pedidos` (ID, cliente, itens, status, total, data/hora).

##### **2. Serviço de Cardápio**

- **Responsabilidades**:
    - Gerenciar os itens do cardápio.
    - Expor os dados para que clientes possam visualizar os produtos disponíveis.
- **Endpoints**:
    - `GET /cardapio` → Lista todos os itens.
    - `POST /cardapio` → Adiciona um novo item (usado pelo administrador).
    - `PUT /cardapio/{id}` → Atualiza informações de um item.
- **Banco de Dados**:
    - Tabelas: `Cardapio` (ID, nome, descrição, preço, categoria).

##### **3. Serviço de Notificações**

- **Responsabilidades**:
    - Receber eventos de atualização dos outros microserviços.
    - Enviar notificações para o cliente quando o status de um pedido mudar.
- **Funcionamento**:
    - Consumidor de eventos do **Serviço de Pedidos** via **Kafka**.
    - Integração com APIs de envio de notificações push ou e-mail.

---

### **4. Fluxo de Dados**

1. **Cliente realiza um pedido**:
    
    - O front-end envia os dados para o endpoint `/api/pedidos` no **API Gateway**.
    - O gateway redireciona a solicitação para o **Serviço de Pedidos**.
    - O serviço cria o pedido, persiste no banco de dados e publica um evento "Pedido Criado" no Kafka.
2. **Funcionário atualiza o status do pedido**:
    
    - O front-end do funcionário faz uma solicitação `PUT /api/pedidos/{id}`.
    - O **Serviço de Pedidos** atualiza o status no banco e publica o evento "Pedido Atualizado".
3. **Notificação de atualização**:
    
    - O **Serviço de Notificações** consome o evento "Pedido Atualizado" no Kafka.
    - Envia uma notificação para o cliente com o novo status do pedido.
4. **Cliente recebe a notificação**:
    
    - O front-end do cliente exibe a notificação em tempo real.

---

### **5. Benefícios do Modelo**

- **Escalabilidade**: Cada microserviço pode ser escalado independentemente.
- **Desacoplamento**: Os serviços são independentes, permitindo facilidade de manutenção.
- **Resiliência**: Mensagens assíncronas via Kafka garantem que eventos sejam processados mesmo com falhas temporárias.