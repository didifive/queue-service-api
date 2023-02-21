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

Este projeto deve atender os seguintes requisitos:

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

Este projeto é para fins de estudo e para ser usado, ou adaptado, para qualquer negócio, ver [MIT License](https://mit-license.org/).

## Visuais
Logotipo:  
![Filas Logo](docs/logotipo.png?raw=true "Filas Logo")  
UML - Diagrama de Classes:  
![UML - Diagrama de Classes](docs/uml-diagram.drawio.png?raw=true "UML - Diagrama de Classes")

Qualquer dúvida, sugestão ou crítica é só entrar em contato (https://github.com/didifive).  
#EnjoyThis #MakeITHappen

---

## Endpoints da API

* Empresa: `/api/v1/empresa`
* Fila: `/api/v1/queue`
* Número (Senha): `/api/v1/number`

Para testar localmente os Endpoints, foi adicionado ao projeto uma coleção do Postman que já possuí modelos e testes de requisições HTTP. O arquivo está na pasta [postman](https://github.com/didifive/queue-service-api/tree/master/postman)

---

## Frontend em React para este projeto

*Em desenvolvimento.*

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
