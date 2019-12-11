<p align="center">
	<img width="400px" height="180px" src="https://scontent.fagt1-1.fna.fbcdn.net/v/t1.0-9/79474203_158579002163689_6017096216435752960_o.png?_nc_cat=111&_nc_ohc=adrJqjyUnuYAQnNaWhpyq6SFhnh5uqbkLHsdybZwanXJGiDYXsXhpJkIQ&_nc_ht=scontent.fagt1-1.fna&oh=d9d37c31739157fad6b0a7e8438cd90b&oe=5E865B08">
</p>
# Flash Cursos

Projeto final de Web IV - www.flashcursos.com

## Começando

Essas instruções vão te dar uma cópia do projeto funcionando e rodando na sua maquina local para fins de desenvolvimento e testes. Veja as notas de importação para saber como importar o projeto em um sistema ativo.

## Pré requisitos

Ferramentas que você deve instalar e como instalar.

```
JDK 7+
```
```
STS 3+
```
```
Lombok.jar
```
```
PostgreSQL
```
```
PgAdmin 3+
```
```
Visual Studio Code
```
```
Node JS
```
```
Angular CLI
```


## Instalando Spring Boot

Um passo a passo de como ter um ambiente de desenvolvimento env do back-end rodando.


Primeiro instale o JDK 7 ou superior, lembre-se de criar uma conta na Oracle.

* [JDK 7](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)

Instale o STS 3+ ou superior.

* [STS 3+](https://spring.io/tools)

Baixe o lombok.jar e procure a pasta onde foi instalado o STS.

* [Lombok.jar](https://projectlombok.org/download)

Baixe o PosgreeSQL e no meio da instalação marque o checkbox do PgAdmin

* [PostgreSQL/PgAdmin](https://www.postgresql.org/download/)

Clone o repositório para a pasta Workspace do STS

```
git clone https://github.com/jlenon7/projeto-final.git
```


### Importe o projeto como Maven
<div align="center">
![Captura de Tela 2019-11-23 às 12 50 51](https://user-images.githubusercontent.com/52840235/69481480-80a79580-0df0-11ea-8973-0ebe017ced52.png)
</div>
---
<p align="center">
![Captura de Tela 2019-11-23 às 12 51 23](https://user-images.githubusercontent.com/52840235/69481492-93ba6580-0df0-11ea-8bf7-41ec9d32775f.png)
</p>

### E faça um Maven Update
<p align="center">
![Captura de Tela 2019-11-23 às 12 59 33](https://user-images.githubusercontent.com/52840235/69481559-22c77d80-0df1-11ea-9a5c-2cd9ddcb0b03.png)
</p>
## Rodando os testes

### Entre no PgAdmin e crie a base de dados com o nome *flashcursos*
<p align="center">
![Captura de Tela 2019-12-11 às 09 23 29](https://user-images.githubusercontent.com/52840235/70621381-2f711180-1bf8-11ea-96a4-0dc52355d95c.png)
</p>
---
<p align="center">
![Captura de Tela 2019-12-11 às 09 23 57](https://user-images.githubusercontent.com/52840235/70621435-57607500-1bf8-11ea-9886-5a2e0378f97a.png)
</p>

### Para rodar os testes entre na pasta src/test/java
<p align="center">
![Captura de Tela 2019-11-23 às 13 01 30](https://user-images.githubusercontent.com/52840235/69481588-6326fb80-0df1-11ea-984e-e54b3339f60f.png)
</p>
### Escolha um dos testes e rode ele como JUnit
<p align="center">
![Captura de Tela 2019-11-23 às 13 03 22](https://user-images.githubusercontent.com/52840235/69481625-a6816a00-0df1-11ea-9896-86f66e8ea134.png)
</p>
### Por que dos testes

Estes testes foram necessários para não dependermos do Front-end da nossa aplicação e para testarmos todos os tipos de possibilidades que podem acontecer no uso do nosso sistema.

### Um exemplo dos nossos testes

Aqui temos um teste básico de um cadastro de professor

```
@Test
@Sql({ "/dataset/truncate.sql", 
	  "/dataset/usuarios.sql",
	 "/dataset/professor.sql" })

public void cadastrarProfessorMustPass() {
	Professor professor = new Professor();

	professor.setNome("João Lenon Lopes");
	professor.setCpf("092.862.989-90");
	professor.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
	professor.setEmail("lenonsec7@gmail.com");
	professor.setCelular("(45) 99955-3219");
	professor.setTipousuario(TipoUsuarioEnum.PROFESSOR);
	professor.setAreaConhecimento(AreaConhecimentoEnum.DEV_MOBILE);

	this.professorService.cadastrarProfessor(professor);		
	Assert.assertNotNull(professor);
	Assert.assertNotNull(professor.getId());		
}
```


## Instalando o Angular CLI

Um passo a passo de como ter um ambiente de desenvolvimento env do front-end rodando.

Baixe o Visual Studio Code para poder editar o nosso Front End

* [VS Code](https://code.visualstudio.com/download)

Instale o Node JS pelo terminal utilizando o comando;

```
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.1/install.sh | bash
```
```
nvm install node
```

Ou pelo site do Node JS;

* [Node JS](https://nodejs.org/en/download/)

Instale o Angular CLI pelo terminal utilizando o comando;

```
npm install -g @angular/cli
```

Abra a pasta clonada do repositório pelo VS Code, caso não tenha clonado, use o comando abaixo;

```
git clone https://github.com/jlenon7/projeto-final.git
```

### Abra o projeto no VS Code
<p align="center">
![Captura de Tela 2019-12-11 às 09 48 45](https://user-images.githubusercontent.com/52840235/70622842-8f1cec00-1bfb-11ea-9f05-c627a9cf727b.png)
</p>
---
<p align="center">
![Captura de Tela 2019-12-11 às 09 49 05](https://user-images.githubusercontent.com/52840235/70622893-a1972580-1bfb-11ea-8173-b646ce1743af.png)
</p>

### Rode o seu projeto no Spring Boot como um Spring Boot APP
<p align="center">
![Captura de Tela 2019-12-11 às 09 55 57](https://user-images.githubusercontent.com/52840235/70623256-7b25ba00-1bfc-11ea-8d6c-678f4bf34add.png)
</p>

### Seu Console no Spring deve estar assim;
<p align="center">
![Captura de Tela 2019-12-11 às 09 57 20](https://user-images.githubusercontent.com/52840235/70623343-af997600-1bfc-11ea-9452-83c7e190909a.png)
</p>

### Com seu Spring Boot rodando, entre na pasta FrontAngular pelo terminal e utilize o comando;

```
ng serve --proxy-config proxy.config.js
```

### Entre nessa porta local e seu projeto já vai estar rodando;

```
localhost:4200
```

## Feito com

* [Spring Boot](https://spring.io/projects/spring-boot) - Back-end framework usado
* [Maven](https://maven.apache.org/) - Gerenciador de dependencias do back
* [Node JS](https://nodejs.org/) - Gerenciador de dependencias do front
* [Angular](https://spring.io/projects/spring-boot) - Front-end framework usado


## Versionamento

Por enquanto não estamos trabalhando com versionamento mas pretendemos colocar em atualizações futuras 

## Autores

* **João Lenon** - *Trabalho Inicial* - [jlenon7](https://github.com/jlenon7)

* **Adryell Nathann** - *Trabalho Inicial* - [adryell](https://github.com/Adryell)

## Licença

Este projeto está licenciado sob a MIT License - veja a [LICENSE.md](LICENSE.md) para mais detalhes

## Agradecimentos

* Agradecimentos ao **Gabriel Ulisses** - [GabrielUlisses](https://github.com/GabrielUlisses) que foi essencial no desenvolvimento desse projeto.
