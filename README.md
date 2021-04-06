# Encurtador de URL

<!-- SOBRE O PROJETO -->
## SOBRE O PROJETO

Encurtador de URL utilizando Java, Spring Boot e JPA, para aprendizado. 

Você manda a URL desejada para ser encurtada, e o programa te devolve um código. 

```
  Exemplo:
  url: https://github.com/Raeski
  
  Retorno:
  "den8fk46al"
```

Mandando esse código na url localhost:8080/find/den8fk46al - Você é redirecionado automaticamente para o link https://github.com/Raeski

Tecnologias utilizadas:
* Java
* JPA
* Hibernate
* Lombok
* MapStruct
* MySQL
* Junit

<!-- GETTING STARTED -->
## Instalação

### Pré requisitos

* Insomnia/Postman/Google Chrome ( Para testar os endpoints ) 

* Alguma IDE que rode Java como Eclipse, Intellij... 

* Mysql (Caso queira utilizar a aplicação, mas pode ser testada com os teste feitos em Junit)


### Instalação

1. Pegue o link do repositório https://github.com/Raeski/url-encurtator.git
2. Clone o repo
   ```sh
   git clone https://github.com/Raeski/url-encurtator.git
   ```
3. Abra o projeto em sua IDE de prefêrencia

4. Configure o arquivo application.yml alterando o usuario e senha (Utilize o seu usuário e senha do Mysql), quando o projeto for executado ele se encarregará de criar a tabela e suas colunas no banco de dados

5. Na IDE execute o arquivo UrlApplication

6. No insomnia teste os endpoins no localhost:8080

```
    Exemplo de JSON :
    {
    "url": "https://github.com/Raeski"
    }
 ```

   ```JS
   POST /url - para criar a url encurtada
   
   GET /find/{códigoDaUrlEncurtada} - Redireciona para o link da página vinculado com o código gerado na hora de enviar a URL
   
   GET /url/{id} - Redireciona para o link da página vinculado com o ID
   ```

<!-- CONTACT -->
## Contato


<p>Feito por <b>Gustavo Raeski</b>  :octocat: | - gustavoraeski@outlook.com

<a href="https://www.linkedin.com/in/gustavo-raeski/">Entre em contato</a></p>
