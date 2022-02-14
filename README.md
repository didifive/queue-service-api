# Queue Service API

## Controller for queues - Controle de senhas e filas

Este projeto foi desenvolvido para controle de senhas para atendimento com filas personalizadas, podendo cadastrar filas como "Prioritário" e "Convencional" ou então "Atendimento X" e "Atendimento Y", ficando a critério da necessidade.

O projeto consiste em primeiramente cadastrar empresa(s), após isso cadastrar uma ou mais filas ligadas à(s) empresa(s) e assim gerar e atualizar o atendimento das senhas para cada fila. Para conhecer os endpoints basta ir na seção "Endpoints criados na API".
Também já foi configurado para permitir que os dados sejam persistidos em SQLite para uso local ou PostgreSQL para uso na nuvem, aqui utilizei o [Heroku] para disponibilizar o projeto online e o [ElephantSQL] como base na nuvem.

Qualquer dúvida, sugestão ou crítica é só entrar em contato (https://github.com/didifive).  
#EnjoyThis #MakeITHappen

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


---

#### Endpoints criados na API

* Empresa: `/api/v1/company`
* Fila: `/api/v1/queue`
* Número (Senha): `/api/v1/number`

Para testar localmente os Endpoints, foi adicionado ao projeto uma coleção do Postman que já possuí modelos e testes de requisições HTTP. O arquivo está na pasta [postman](https://github.com/didifive/queue-service-api/tree/master/postman)

Swagger do projeto carregado localmente: `http://localhost:8080/swagger-ui.html`

Swagger do projeto no [Heroku]: [https://queue-service-api-didi.herokuapp.com/swagger-ui.html](https://queue-service-api-didi.herokuapp.com/swagger-ui.html)

---

#### Frontend em React para este projeto

*Em desenvolvimento.*

---

Links Interessantes:

* [Java 17 - Documentation]
* [IntelliJ]
* [ElephantSQL]
* [spring]
* [spring initializr]
* [SQLite Database with Spring Boot]
* [Heroku]
* [Jakarta Validation]
* [Lombok]
* [Maven]

[Heroku]: https://www.heroku.com/
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
