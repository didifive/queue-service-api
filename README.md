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

# Queue Service API - Filas

## Controle de senhas para atendimento

Este projeto foi desenvolvido para controle de senhas para atendimento com filas personalizadas, podendo cadastrar filas com Tipos de Prioridades Específicos (Prioritário, Normal, etc), ficando a critério da necessidade.

## Escopo

O sistema, considerando back e front-end deve atender os seguintes requisitos:

- Ter cadastro de empresa;
- Para os usuários, ter os perfis de Administrador, Atendente e Usuário;
- Ter cadastro de filas com nome e sigla, exemplo: nome "Caixa" e sigla "CX";
- As filas devem permitir prioridades personalizadas (Normal, Prioritário, Idoso 80+ etc);
- As senhas devem seguir sequencia numérica com o prefixo sendo a sigla da fila, exemplo: "CX001";
- Senhas devem possuir um sufixo conforme abreviação do Tipo de Atendimento, exemplo: "CX001N", onde o "N" é abreviatura para Tipo de Atendimento Normal;
- As senhas devem possuir data e hora de geração e de finalização, se foi atendida, deve possuir quem foi o atendente;
- O Administrador tem acesso total ao sistema, podendo inclusive alterar ou desativar outros usuários;
- O Atendente apenas chama e finaliza as senhas que ele chamou marcando como atendida ou não atendida;
- O Atendente pode ver apenas senhas das filas em que foi autorizado;
- O Usuário pode fazer a configuração do sistema, como criar filas, zerar número da fila, vincular (ou desvincular) atendente de uma fila e editar dados da empresa que o Usuário faz parte;
- Deve possuir um terminal para emissão de senhas, este deve ser logado por um alguém com perfil de Usuário para disponibilizar as filas para emissão de senhas da empresa em que está vinculado;
- Gerar saída para emissão de senha em equipamento de impressão térmica.

## Queue Service API - Back-End

Este projeto aborda somente a API em Back-End.  
Foi criado para fins de estudos, prática e testes. Aproveite para fazer melhorias ou personalização.  
Apesar de este projeto ser público e não ter finalidade comercial, ainda assim foi pensado para resolver problema real, portanto é possível utilizar esta base para um projeto comercial.  
Licença: [MIT License](https://mit-license.org/).

### Configuração do Projeto

Para carregar a aplicação corretamente é necessário configurar as variáveis de ambientes no servidor ou informar na execução:
- `DATABASE_URL`: URL da base de dados, este valor é utilizado em `spring.datasource.url` no `application.properties`, exemplo de valor para esta variável: jdbc:postgresql://host.db.elephantsql.com:5432/database?user=usuario&password=senha
- `TOKEN_API_SECRET`: Token de segredo para o JWT, este valor é utilizado em `queue-service-api.jwt.secret` no `application.properties`, exemplo de valor da variável: 46070d4bf934fb0d4b06d9e2c46e346944e322444900a435d7d9a95e6d7435f5

A URL para o banco de dados normalmente é fornecida pelo serviço de banco de dados, caso a instalação seja local, deve-se confirmar os parâmetros.  
Sobre o token para segredo do JWT, este pode ser gerado em sites que geram tokens ou algum token particular criado.

Abaixo, seguem maneiras de executar o projeto com terminal ou com IDE:

#### Executar com terminal
Após configurar banco de dados e o segredo, basta se atentar em possuir o JDK do Java na versão 17 (vide versão na seção Tecnologias) e executar o comando:
Bash ou PowerShell:
```bash
./mvnw clean package spring-boot:repackage
java -jar target/queue-service-api-0.1.1-SNAPSHOT.jar
```
_OBS: para CMD, no primeiro comando, basta remover o "./" antes do mvnw_

#### Executar com IDE
A execução com a IDE é mais simples, primeiro deve carregar o projeto na IDE, verificar o JDK configurado, aguardar indexar e carregar as dependências do Maven, depois confirmar se as variáveis de ambiente existem no servidor, se não existirem, basta configurar as variáveis citadas acima na sua IDE, então é só fazer a execução.
## Tecnologias

- Java 17
- Springboot v.3.0.2
- Spring Security
- Lombok v.1.18.26
- Springdoc v.2.1.0 (OpenApi - Swagger)
- Mapstruct v.1.5.3.Final
- Java JWT v.4.3.0
- PostgreSQL - utilizando [ElephantSQL]
- Jacoco 0.8.8

IDE Utilizada: [IntelliJ] v.2022.3.2 (Community Edition)

---

## Endpoints da API

Para documentação dos Endpoints, basta acessar o Swagger que fica disponível em http://localhost:8080/swagger-ui.html quando o projeto é executado.

Para testar localmente os Endpoints, existe coleção do [Postman] que já possuí requisições HTTP configuradas. O arquivo [Queue Service API.postman_collection.json](https://github.com/didifive/queue-service-api/blob/master/postman/Queue%20Service%20API.postman_collection.json) está na pasta [postman](https://github.com/didifive/queue-service-api/tree/master/postman)

---

## Visuais
Logotipo:  
![Filas Logo](docs/logotipo.png?raw=true "Filas Logo")  
UML - Diagrama de Classes:  
![UML - Diagrama de Classes](docs/uml-diagram.drawio.png?raw=true "UML - Diagrama de Classes")  
OpenAPI - Swagger:  
![Screenshot da tela de OpenAPI - Swagger](docs/swagger.png?raw=true "Screenshot da tela de OpenAPI - Swagger")

---

## ~~Frontend em React para este projeto~~

~~Em desenvolvimento.~~

---

Qualquer dúvida, sugestão ou crítica é só entrar em contato ou abrir uma Issue (https://github.com/didifive).  
Feito com muita dedicação.
#EnjoyThis #MakeITHappen

---

Links Interessantes:

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