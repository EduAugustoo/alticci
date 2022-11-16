# Exercício Altice Labs

## Tecnologias
Utiliza Java 11, Spring Framework e seus projetos:
<ul>
    <li>Spring Boot</li>
    <li>Spring Web</li>
    <li>Spring Cache</li>
    <li>Spring Data Redis</li>
</ul>

## Descrição
Aplicação desenvolvida para calcular o valor da sequência Alticci para
um determinado índice passado pelo usuário.<br />
A aplicação foi desenvolvida pensando na utilização dos princípios SOLID.

### Tratamento de exceções
O tratamento de exceções é feito utilizando um RestControllerAdvice,
centralizando em uma classe a lógica referente a resposta enviada após
uma exceção ser lançada pela aplicação, deixando classes mais limpas
e evitando que a lógica de negócio e o tratamento de erros fiquem espalhados
pela aplicação.
Foi criada uma única classe de exceção (BusinessException) que é lançada
sempre que houver um erro de lógica de negócio.

### Testes
O JUnit juntamente com Mockito e Testcontainers são utilizados para a
criação dos testes unitários e de integração da aplicação.<br />

### Documentação
O Swagger foi utilizado como fonte de documentação da API.<br />
Para acesso ao Swagger, basta acessar o link a seguir com a aplicação em
execução:
http://localhost:8080/swagger-ui.html

## Código Fonte
O código fonte da aplicação pode ser encontrado no GitHub através do link abaixo:
https://github.com/EduAugustoo/alticci

## Execução
Para execução da aplicação, é necessário possuir apenas o Docker e 
Docker Compose instalados na máquina.<br />
Seguir os passos abaixo para iniciar a aplicação:
- Clonar o repositório do GitHub ou baixar o projeto compactado;
- Rodar o comando a seguir no diretório pai da aplicação:<br />
  `docker-compose up -d`<br />
  Por padrão, a aplicação irá rodar na porta 8080