<p align="center">
 <img alt="Repository language count" src="https://img.shields.io/github/languages/count/didifive/queue-service-api">
    <a href="https://www.linkedin.com/in/luis-carlos-zancanela/">
        <img alt="Made by Didi" src="https://img.shields.io/badge/made%20by-Didi-green">
    </a> 
    <a href="https://github.com/didifive/queue-service-api/commits/master">
        <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/didifive/queue-service-api?color=blue">
    </a>
    <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen?color=blue">
</p>

<p align="center">
 <a href="https://dev.java/">
   <img alt="Java" src="https://img.shields.io/static/v1?color=red&label=Dev&message=Java&style=for-the-badge&logo=Java">
 </a>
</p>

# 🚶🚶🚶 Queue Service API - Filas 

## 💾 Controle de senhas para atendimento

Este projeto foi desenvolvido para controle de senhas para atendimento com filas personalizadas, podendo cadastrar filas
com Tipos de Prioridades Dinâmicos, permitindo ir além do padrão "Prioritário e Normal", ficando a critério da necessidade.
Com as senhas salvas em banco é possível também construir relatórios de como foi o atendimento.

## 📼 Escopo

O sistema, considerando back e front-end deve atender os seguintes requisitos:

- Ter cadastro de empresa;
- Para os usuários, ter os perfis de Administrador, Atendente e Usuário;
- Ter cadastro de filas com nome e sigla, exemplo: nome "Caixa" e sigla "CX";
- As filas devem permitir prioridades personalizadas (Normal, Prioritário, Idoso 80+ etc);
- As senhas devem seguir sequencia numérica com o prefixo sendo a sigla da fila, exemplo: "CX001";
- Senhas devem possuir um sufixo conforme abreviação do Tipo de Atendimento, exemplo: "CX001N", onde o "N" é abreviatura
  para Tipo de Atendimento Normal;
- As senhas devem possuir data e hora de geração e de finalização, se foi atendida, deve possuir quem foi o atendente;
- O Administrador tem acesso total ao sistema, podendo inclusive alterar ou desativar outros usuários;
- O Atendente apenas chama e finaliza as senhas que ele chamou marcando como atendida ou não atendida;
- O Atendente pode ver apenas senhas das filas em que foi autorizado;
- O Usuário pode fazer a configuração do sistema, como criar filas, zerar número da fila, vincular (ou desvincular)
  atendente de uma fila e editar dados da empresa que o Usuário faz parte;
- Deve possuir um terminal para emissão de senhas, este deve ser logado por um alguém com perfil de Usuário para
  disponibilizar as filas para emissão de senhas da empresa em que está vinculado;
- Gerar saída para emissão de senha em equipamento de impressão térmica.

## 📜 Queue Service API - Back-End

Este projeto aborda somente a API em Back-End.  
Foi criado para fins de estudos, prática e testes. Aproveite para fazer melhorias ou personalização.  
Apesar de este projeto ser público e não ter finalidade comercial, ainda assim foi pensado para resolver problema real,
portanto é possível utilizar esta base para um projeto comercial.  
Licença: [MIT License](https://mit-license.org/).

### 📗 Configuração do Projeto

Para carregar a aplicação corretamente é necessário configurar as variáveis de ambientes no servidor ou informar na
execução:

- `DATABASE_URL`: URL da base de dados, este valor é utilizado em `spring.datasource.url` no `application.properties`,
  exemplo de valor para esta variável: jdbc:postgresql://host.db.elephantsql.com:
  5432/database?user=usuario&password=senha
- `TOKEN_API_SECRET`: Token de segredo para o JWT, este valor é utilizado em `queue-service-api.jwt.secret`
  no `application.properties`, exemplo de valor da variável:
  46070d4bf934fb0d4b06d9e2c46e346944e322444900a435d7d9a95e6d7435f5

A URL para o banco de dados normalmente é fornecida pelo serviço de banco de dados, caso a instalação seja local,
deve-se confirmar os parâmetros.  
Sobre o token para segredo do JWT, este pode ser gerado em sites que geram tokens ou algum token particular criado.

Abaixo, seguem maneiras de executar o projeto com terminal ou com IDE:

#### 💻 Executar com terminal

Após configurar banco de dados e o segredo, basta se atentar em possuir o JDK do Java na versão 17 (vide versão na seção
Tecnologias) e executar o comando:
Bash ou PowerShell:

```bash
./mvnw clean package spring-boot:repackage
java -jar target/queue-service-api-0.1.1-SNAPSHOT.jar
```

_OBS: para CMD, no primeiro comando, basta remover o "./" antes do mvnw_

#### 💻 Executar com IDE

A execução com a IDE é mais simples, primeiro deve carregar o projeto na IDE, verificar o JDK configurado, aguardar
indexar e carregar as dependências do Maven, depois confirmar se as variáveis de ambiente existem no servidor, se não
existirem, basta configurar as variáveis citadas acima na sua IDE, então é só fazer a execução.

## 🔧 Tecnologias

- Java 17
- Springboot v.3.0.2
- Spring Security
- Lombok v.1.18.26
- Springdoc v.2.1.0 (OpenApi - Swagger)
- Mapstruct v.1.5.3.Final
- Java JWT v.4.3.0
- PostgreSQL - utilizando [ElephantSQL]
- Jacoco 0.8.8

🔨 IDE Utilizada: [IntelliJ] v.2022.3.2 (Community Edition)

---

## 🚪 Endpoints da API

Abaixo segue uma lista geral dos endpoints com resumo de suas funcionalidades:

### 🚦 Autenticação (Auth): Endpoints para autenticação do usuário

| Método | Endpoint                                        | Descrição                                                                                                            |
|--------|-------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| POST   | /api/v1/auth                                    | Realizar autenticação do Usuário informando nome de usuário e senha e se estiver ok retorna token de acesso.         |
| POST   | /api/v1/auth/refresh/{usuarioId}/{refreshToken} | Informar o Id de Usuário (usuarioID) e o Refresh Token que possui (refreshToken) para gerar um novo token de acesso. |
| POST   | /api/v1/auth/invalidate-refresh/{usuarioId}     | Invalida refresh token do usuário, normalmente utilizado ao usuário sair do sistema.                                 |

### 🏢 Empresa: Endpoints com CRUD para cadastro de empresa(s)

| Método | Endpoint             | Descrição                            |
|--------|----------------------|--------------------------------------|
| POST   | /api/v1/empresa      | Cadastrar nova empresa               |
| GET    | /api/v1/empresa      | Listar todas as empresas cadastradas |
| GET    | /api/v1/empresa/{id} | Detalhar empresa                     |
| PUT    | /api/v1/empresa/{id} | Atualizar empresa                    |
| DELETE | /api/v1/empresa/{id} | Apagar empresa                       |

### 📌 Departamento: Endpoints com CRUD para cadastro de departamento(s)

| Método | Endpoint                  | Descrição                                 |
|--------|---------------------------|-------------------------------------------|
| POST   | /api/v1/departamento      | Cadastrar novo departamento               |
| GET    | /api/v1/departamento      | Listar todos os departamentos cadastrados |
| GET    | /api/v1/departamento/{id} | Detalhar departamento                     |
| PUT    | /api/v1/departamento/{id} | Atualizar departamento                    |
| DELETE | /api/v1/departamento/{id} | Apagar departamento                       |

### 👤 Atendente: Endpoints com CRUD para cadastro de atendente(s)
Quando um atendente é criado, um usuário será automaticamente criado.

| Método | Endpoint                | Descrição                              |
|--------|-------------------------|----------------------------------------|
| POST   | /api/v1/atendente       | Cadastrar novo atendente               |
| GET    | /api/v1/atendente       | Listar todos os atendentes cadastrados |
| GET    | /api/v1/atendente/{id}  | Detalhar atendente                     |
| PUT    | /api/v1/atendente/{id}  | Atualizar atendente                    |
| DELETE | /api/v1/atendente/{id}  | Apagar atendente                       |

### 🔑 Usuário: Endpoints com CRUD para cadastro de usuário(s)
Os usuários são diretamente vinculados aos atendentes, nas operações é checado o atendente vinculado ao usuário.

| Método | Endpoint                                 | Descrição                                  |
|--------|------------------------------------------|--------------------------------------------|
| POST   | /api/v1/usuario                          | Cadastrar novo usuário                     |
| GET    | /api/v1/usuario                          | Listar todos os usuários cadastrados       |
| GET    | /api/v1/usuario/{id}                     | Detalhar usuário                           |
| PATCH  | /api/v1/atendente/{id}/novo-nome-usuario | Atualizar usuário com novo nome de usuário |
| PATCH  | /api/v1/atendente/{id}/atualizar-senha   | Atualizar senha de acesso do usuário       |
| PATCH  | /api/v1/atendente/{id}/perfil/adicionar  | Adicionar perfil ao usuário                |
| PATCH  | /api/v1/atendente/{id}/perfil/remover    | Remover perfil do usuário                  |
| PATCH  | /api/v1/atendente/{id}/ativar            | Ativar usuário no sistema                  |
| PATCH  | /api/v1/atendente/{id}/desativar         | Desativar usuário no sistema               |

### ♿ Tipo de Atendimento: Endpoints com CRUD para cadastro de tipo(s) de atendimento
O Tipo de Atendimento foi um recurso criado para que se possa incluir priorizações personalizadas às filas.

| Método | Endpoint                      | Descrição                                        |
|--------|-------------------------------|--------------------------------------------------|
| POST   | /api/v1/tipo-atendimento      | Cadastrar novo tipo de atendimento               |
| GET    | /api/v1/tipo-atendimento      | Listar todos os tipos de atendimento cadastrados |
| GET    | /api/v1/tipo-atendimento/{id} | Detalhar tipo de atendimento                     |
| PUT    | /api/v1/tipo-atendimento/{id} | Atualizar tipo de atendimento                    |
| DELETE | /api/v1/tipo-atendimento/{id} | Apagar tipo de atendimento                       |

### 🔜 Fila: Endpoints com CRUD para cadastro de fila(s)
Uma fila depende de ao menos um tipo de atendimento vinculado.

| Método | Endpoint                                                         | Descrição                           |
|--------|------------------------------------------------------------------|-------------------------------------|
| POST   | /api/v1/fila                                                     | Cadastrar nova fila                 |
| GET    | /api/v1/fila                                                     | Listar todas as filas cadastradas   |
| GET    | /api/v1/fila/{id}                                                | Detalhar fila                       |
| PUT    | /api/v1/fila/{id}                                                | Atualizar fila                      |
| PATCH  | /api/v1/fila/{id}/tipo-atendimento/{tipoAtendimentoId}/adicionar | Adiciona tipo de atendimento à fila |
| PATCH  | /api/v1/fila/{id}/tipo-atendimento/{tipoAtendimentoId}/remover   | Remove tipo de atendimento da fila  |
| DELETE | /api/v1/fila/{id}                                                | Apagar fila                         |

### 🔔 Senha: Endpoints com CRUD para gerar e operar senha(s)
Cada senha é vinculada à uma fila e um tipo de serviço que define a sua prioridade na fila. Possui endpoints para chamar próxima senha de uma fila, chamar/rechamar senha específica, operar para marcar uma senha como chamada, finalizada e atendida e também conseguir ver detalhe de senha e listar senhas conforme intervalo de dia(s)/data(s) e status.

| Método | Endpoint                                         | Descrição                                                                                                                 |
|--------|--------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| POST   | /api/v1/senha                                    | Gera uma nova senha para fila e tipo de atendimento                                                                       |
| GET    | /api/v1/senha                                    | Lista todas as senhas geradas                                                                                             |
| GET    | /api/v1/senha/nao-finalizadas                    | Lista todas as senhas geradas e não finalizadas                                                                           |
| GET    | /api/v1/senha/{id}                               | Detalha senha                                                                                                             |
| GET    | /api/v1/senha/{dataInicio}/{dataFim}             | Lista as senhas por intervalo de dias/datas                                                                               |
| GET    | /api/v1/senha/chamadas/{dataInicio}/{dataFim}    | Lista as senhas chamadas por intervalo de dias/datas                                                                      |
| GET    | /api/v1/senha/finalizadas/{dataInicio}/{dataFim} | Lista as senhas finalizadas por intervalo de dias/datas                                                                   |
| GET    | /api/v1/senha/atendidas/{dataInicio}/{dataFim}   | Lista as senhas atendidas por intervalo de dias/datas                                                                     |
| PATCH  | /api/v1/senha/{id}/chamar-senha                  | Chama senha especificada                                                                                                  |
| PATCH  | /api/v1/senha/fila/{filaId}/chamar-senha         | Chama próxima senha da fila especificada conforme definições de prioridade do(s) tipo(s) de atendimento                   |
| PATCH  | /api/v1/senha/{id}/finalizar-senha               | Marca a senha específicada como finalizada com respectivo motivo informado                                                |
| PATCH  | /api/v1/senha/finalizar-senhas                   | Marca as senhas como finalizadas conforme fila e tipo de atendimento especificados juntamente com devido motivo informado |
| PATCH  | /api/v1/senha/finalizar-todas-senhas             | Marca como finalizada todas as senhas que ainda não estavam finalizadas no sistema com motivo informado                   |
| PATCH  | /api/v1/senha/{id}/atender-senha                 | Marca a senha como atendida                                                                                               |
| PATCH  | /api/v1/senha/{id}/resetar-status                | Reseta status da senha, retira a marcação de que foi chamada, atendida e finalizada                                       |


Para documentação mais completa dos Endpoints, basta acessar o Swagger que fica disponível em http://localhost:8080/swagger-ui.html
quando o projeto é executado.

Para testar localmente os Endpoints, existe coleção do [Postman] que já possuí requisições HTTP configuradas. O
arquivo `Queue Service API.postman_collection.json` e `Queue Service API - Enviroments.postman_collection` estão na
pasta [postman](https://github.com/didifive/queue-service-api/tree/master/postman). Basta importar os dois arquivos no
aplicativo do Postman e selecionar o ambiente (environment) Localhost. A vantagem de utilizar a configuração do domínio
com ambientes (environments) é permitir uma rápida utilização da aplicação em local host e qualquer outro ambiente em
que foi feito deploy.

---

## 🎨 Visuais

Logotipo:  
![Filas Logo](docs/logotipo.png?raw=true "Filas Logo")  
UML - Diagrama de Classes:  
![UML - Diagrama de Classes](docs/uml-diagram.drawio.png?raw=true "UML - Diagrama de Classes")  
OpenAPI - Swagger:  
![Screenshot da tela de OpenAPI - Swagger](docs/swagger.png?raw=true "Screenshot da tela de OpenAPI - Swagger")

---

## ~~📱 Frontend em React para este projeto~~

~~Em desenvolvimento.~~

---

📋 Qualquer dúvida, sugestão ou crítica é só entrar em contato ou abrir uma Issue (https://github.com/didifive).  
💚 Feito com muita dedicação. #EnjoyThis

---

📎 Links Interessantes:

* [Java 17 - Documentation]
* [IntelliJ]
* [ElephantSQL]
* [spring]
* [spring initializr]
* [SQLite Database with Spring Boot]
* [Jakarta Validation]
* [Lombok]
* [Maven]

[Jakarta Validation]: https://beanvalidation.org/
[Lombok]: https://projectlombok.org/
[Java 17 - Documentation]: https://docs.oracle.com/en/java/javase/17/
[IntelliJ]: https://www.jetbrains.com/pt-br/idea/
[Maven]: https://maven.apache.org/
[H2 Database]: https://h2database.com/
[spring initializr]: https://start.spring.io/
[spring]: https://spring.io/
[ElephantSQL]: https://www.elephantsql.com/
[didifive/queue-service-api]: https://github.com/didifive/queue-service-api
[SQLite Database with Spring Boot]: https://fullstackdeveloper.guru/2020/05/01/how-to-integrate-sqlite-database-with-spring-boot/#:~:text=SQLite%20is%20the%20most%20used%20database%20engine%20in,you%20don%E2%80%99t%20have%20to%20do%20for%20other%20databases.
[Postman]: https://www.postman.com/