# Sistema de Monitoramento de Eficiência Energética ESG

Sistema desenvolvido para monitoramento de eficiência energética com foco em práticas ESG (Environmental, Social, and Governance).

## Requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose
- Oracle Database XE (opcional, pode usar Docker)

## Configuração do Ambiente

### Usando Docker (Recomendado)

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITÓRIO]
cd [NOME_DO_DIRETÓRIO]
```

2. Execute o ambiente com Docker Compose:
```bash
docker-compose up -d
```

A aplicação estará disponível em: http://localhost:8082

### Configuração Manual

1. Instale o Oracle Database XE
2. Configure as credenciais no `application.properties`
3. Execute o projeto:
```bash
mvn spring-boot:run
```

## Acesso ao Sistema

### Usuários Padrão

- **Administrador**
  - Usuário: admin
  - Senha: admin123

- **Usuário Comum**
  - Usuário: user
  - Senha: user123

## Funcionalidades

- Monitoramento de consumo energético
- Alertas de consumo excessivo
- Relatórios de eficiência
- Dashboard com gráficos
- Exportação de dados

## Endpoints da API

- GET /api/consumos - Lista todos os consumos
- POST /api/consumos - Registra novo consumo
- GET /api/alertas - Lista todos os alertas
- GET /api/relatorios - Gera relatórios

## Documentação da API

A documentação Swagger está disponível em:
http://localhost:8082/swagger-ui.html

### Exemplos de Uso da API

#### Registrar Consumo
```http
POST /api/consumos
Content-Type: application/json

{
    "sensorId": 1,
    "consumoKwh": 150.0,
    "dataMedicao": "2024-03-20T10:00:00",
    "observacao": "Consumo normal"
}
```

#### Buscar Consumos por Período
```http
GET /api/consumos/total/1?inicio=2024-03-01T00:00:00&fim=2024-03-20T23:59:59
```

## Testes

O projeto inclui testes unitários e de integração. Para executar os testes:

```bash
mvn test
```

### Cobertura de Testes

- Testes unitários para serviços
- Testes de integração para controllers
- Mocks para isolamento de dependências

## Validações

O sistema implementa validações em diferentes níveis:

### Validações de Dados
- Campos obrigatórios
- Formato de datas
- Valores numéricos positivos
- Validações de negócio

### Tratamento de Erros
- Mensagens de erro personalizadas
- Logs detalhados
- Respostas HTTP apropriadas

## Desenvolvimento

### Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/fiap/esg/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── exception/
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── db/migration/
└── test/
    └── java/
        └── br/com/fiap/esg/
            ├── controller/
            ├── service/
            └── repository/
```

### Executando Testes

```bash
mvn test
```

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT.