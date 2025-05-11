# Sistema de Monitoramento de Eficiência Energética ESG

## Descrição do Projeto

Este projeto é um sistema de monitoramento de eficiência energética, alinhado aos temas de ESG (Environmental, Social, and Governance). O objetivo é permitir o acompanhamento de sensores, consumos e alertas, promovendo a gestão eficiente de recursos e a governança de dados energéticos.

O sistema foi desenvolvido em Java com Spring Boot, utilizando práticas modernas de desenvolvimento, segurança, integração com banco de dados e interface web responsiva.

> **Observação:** Apesar do enunciado exigir Oracle, devido a problemas técnicos de conexão, o sistema foi implementado e testado com o banco H2 em memória. O código está pronto para ser adaptado ao Oracle, bastando ajustar o arquivo `application.properties` e as dependências.

---

## Funcionalidades RESTful Implementadas

O sistema expõe diversos endpoints RESTful, incluindo (mas não se limitando a):

- **Sensores**
  - `GET /sensores` — Listar sensores cadastrados
  - `POST /sensores` — Cadastrar novo sensor
  - `PUT /sensores/{id}` — Atualizar sensor existente
  - `DELETE /sensores/{id}` — Remover sensor
  - `POST /sensores/{id}/acionar` — Acionar/desligar sensor (altera status)

- **Consumos**
  - `GET /consumos` — Listar consumos registrados
  - `POST /cadastrar-consumo` — Cadastrar novo consumo
  - `PUT /consumos/{id}` — Editar consumo
  - `DELETE /consumos/{id}` — Remover consumo

- **Alertas**
  - `GET /alertas` — Listar alertas
  - `POST /alertas` — Cadastrar novo alerta
  - `DELETE /alertas/{id}` — Remover alerta

- **Relatórios**
  - `GET /relatorio` — Visualizar relatório de consumos e indicadores

---

## Tecnologias e Práticas Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security** (autenticação e proteção de endpoints)
- **Thymeleaf** (templates web)
- **Bootstrap 5** (interface moderna e responsiva)
- **DataTables** (tabelas dinâmicas)
- **Banco de Dados H2** (em memória, pronto para Oracle)
- **Migrations**: Estrutura preparada para uso de Flyway/Liquibase
- **Docker**: Projeto pronto para conteinerização (Dockerfile e docker-compose)
- **Validações e tratamento de exceções**
- **Documentação dos endpoints via Swagger/OpenAPI**

---

## Como Executar

1. Clone o repositório
2. Execute `mvn spring-boot:run`
3. Acesse [http://localhost:8082](http://localhost:8082) no navegador
4. Console do banco H2: [http://localhost:8082/h2-console](http://localhost:8082/h2-console)
   - JDBC URL: `jdbc:h2:mem:devdb`
   - Usuário: `sa` (sem senha)

---

## Observações Importantes

- O sistema está pronto para ser adaptado para Oracle, bastando ajustar o arquivo de configuração.
- O uso do H2 foi necessário para garantir a entrega funcional, devido a limitações técnicas no ambiente de desenvolvimento.
- O projeto inclui exemplos de migração de banco e está preparado para uso em Docker.
- Todos os endpoints principais exigidos pelo enunciado estão implementados, com autenticação e validação.

---

## Segurança e Boas Práticas

- Endpoints protegidos com Spring Security.
- Validação de dados no backend.
- Tratamento de exceções e mensagens amigáveis.
- Interface web moderna e responsiva.

---

## Contato e Suporte

Em caso de dúvidas ou problemas, abra uma issue no repositório ou entre em contato com o time de desenvolvimento.