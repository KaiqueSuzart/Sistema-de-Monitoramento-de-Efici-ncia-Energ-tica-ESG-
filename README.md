# Sistema de Monitoramento de Eficiência Energética ESG

## Sobre o Projeto

Este sistema foi desenvolvido para monitorar sensores, consumos e alertas de eficiência energética, com interface web moderna e responsiva.

## Banco de Dados

- **Banco utilizado:** H2 (em memória)
- **Motivo:** Não foi possível utilizar o Oracle devido a problemas de configuração/conexão. O H2 foi adotado para facilitar o desenvolvimento e testes.
- **Acesso ao console H2:**
  - URL: [http://localhost:8082/h2-console](http://localhost:8082/h2-console)
  - JDBC URL: `jdbc:h2:mem:devdb`
  - Usuário: `sa` (sem senha)

## Funcionalidades Implementadas

- Cadastro, edição, visualização e remoção de sensores
- Cadastro, edição, visualização e remoção de consumos
- Cadastro, visualização e remoção de alertas
- Acionamento/desligamento de sensores (status ATIVO/INATIVO)
- Relatórios com filtros e gráficos
- Login com autenticação
- Interface moderna com Bootstrap e DataTables
- Modal de detalhes e edição para entidades
- Integração completa com backend Spring Boot

## Como rodar o projeto

1. Clone o repositório
2. Execute `mvn spring-boot:run`
3. Acesse [http://localhost:8082](http://localhost:8082) no navegador

## Observações

- O sistema está pronto para ser adaptado para outros bancos relacionais (basta ajustar o `application.properties`).
- Caso queira usar Oracle, será necessário revisar a configuração do driver e permissões.

---

Se precisar de mais informações ou encontrar algum problema, abra uma issue ou entre em contato com o time de desenvolvimento.