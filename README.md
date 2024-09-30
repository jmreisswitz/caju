# caju

## Como executar:
```bash
docker-compose up -d
```

## Arquitetura

Foi usado arquitetura hexagonal, com camadas de aplicação, domínio e infraestrutura.

### Domínio
Contém as entidades e regras de negócio. Não depende de nenhuma outra camada.

### Aplicação
Contém os casos de uso, que são as operações que podem ser realizadas no sistema. Depende da camada de domínio. Aqui ficam as regras de transações e autorização (não aplicável ao desafio)

### Infraestrutura
Contém as implementações dos repositórios e serviços que são necessários para a aplicação funcionar. Depende da camada de aplicação. Também aqui que fica a entrada e saída de dados do sistema (controllers, cron jobs, etc)

## Observações
Para o desafio foi criado algumas contas pré-cadastradas, e não foi implementado outra formas de adicionar ou editar essas contas (sem mexer no código). Para garantir que as contas tenham algum crédito há um cron que de tempos em tempos reseta todas as contas para o estado inicial. Há endpoints para visualizar as contas e seus saldos.

## Endpoints

### POST /transaction/

Executa uma transação em uma conta e retorna o código de autorização.
O código da resposta pode ser 3 casos:
- 00: Transação autorizada
- 51: Saldo insuficiente
- 07: Transação não autorizada por outro erro

body
```json
{
  "accountId": "1",
  "totalAmount": 100.00,
  "mcc": "5411",
  "merchant": "IFOOD               SAO PAULO BR"
}
```
response
```json
{
  "code": "51" || "00" || "07"
}
```

## GET /accounts/
Para lidar com as accounts dos usuários foi criado um repositório em memória com alguns dados pré-cadastrados. Para melhor visualização dos dados, foi criado um endpoint para listar todas as contas cadastradas.

response
```json
[ 
 {
  "id": "1234",
  "name": "Nome da Conta",
  "balances": {
    "FOOD": 100.0,
    "MEAL": 90.0,
    "CASH": 100.0
  }
 },
...

]
```
## GET /accounts/{id}
Para visualizar o saldo de uma conta específica, basta passar o id da conta no endpoint.

response
```json
{
  "id": "1234",
  "name": "Nome da Conta",
  "balances": {
    "FOOD": 100.0,
    "MEAL": 90.0,
    "CASH": 100.0
  }
}
```

## GET /merchants
Para lidar com as regras de merchants foram cadastrados algumas lojas e suas reespectivas categorias. Para melhor visualização dos dados, foi criado um endpoint para listar todos os merchants cadastrados.

resposne
```json
{
    "merchants": {
        "Amazon": "CASH",
        "Padaria do Ze": "FOOD",
        "Rabbibs": "MEAL",
        "Uber": "CASH",
        "Ifood": "MEAL"
    }
}
```

# Transações simultâneas
Para garantir que não haja problemas de concorrência, foi utilizado um lock distribuido usando Redis. Dessa forma, garantimos que apenas uma transação seja executada por vez.