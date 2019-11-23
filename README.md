# Flash Cursos

Projeto final de Web IV - www.flashcursos.com

## Começando

Essas instruções vão te dar uma cópia do projeto funcionando e rodando na sua maquina local para fins de desenvolvimento e testes. Veja as notas de importação para saber como importar o projeto em um sistema ativo.

### Pré requisitos

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

### Instalando

Um passo a passo de como ter um ambiente de desenvolvimento env rodando.


Primeiro instale o JDK 7 ou superior, lembre-se de criar uma conta na Oracle.

* [JDK 7](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)

Instale o STS 3+ ou superior.

* [STS 3+](https://spring.io/tools)

Baixe o lombok.jar e procure a pasta onde foi instalado o STS.

* [Lombok.jar](https://projectlombok.org/download)

Baixe o PgAdmin e no meio da instalação marque o checkbox do PostgreSQL

* [PgAdmin/PostgreSQL](https://www.pgadmin.org/download/)

Clone o repositório para a pasta Workspace do STS

```
git clone https://github.com/jlenon7/projeto-final.git
```

## Importe o projeto como Maven

![Captura de Tela 2019-11-23 às 12 50 51](https://user-images.githubusercontent.com/52840235/69481480-80a79580-0df0-11ea-8973-0ebe017ced52.png)


![Captura de Tela 2019-11-23 às 12 51 23](https://user-images.githubusercontent.com/52840235/69481492-93ba6580-0df0-11ea-8bf7-41ec9d32775f.png)

## E faça um Maven Update

![Captura de Tela 2019-11-23 às 12 59 33](https://user-images.githubusercontent.com/52840235/69481559-22c77d80-0df1-11ea-9a5c-2cd9ddcb0b03.png)

# Rodando os testes

## Para rodar os testes entre na pasta src/test/java

![Captura de Tela 2019-11-23 às 13 01 30](https://user-images.githubusercontent.com/52840235/69481588-6326fb80-0df1-11ea-984e-e54b3339f60f.png)

## Escolha um dos testes e rode ele como JUnit

![Captura de Tela 2019-11-23 às 13 03 22](https://user-images.githubusercontent.com/52840235/69481625-a6816a00-0df1-11ea-9896-86f66e8ea134.png)

### Por que dos testes

Estes testes foram necessários para não dependermos do Front-end da nossa aplicação e para testarmos todos os tipos de possibilidades que podem acontecer no uso do nosso sistema.

### Um exemplo dos nossos testes

Aqui temos um teste básico de um cadastro de professor

```
/**
	 * ====================================== (CREATE)RUD ===========================================
	 */
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

## Feito com

* [Spring Boot](https://spring.io/projects/spring-boot) - Back-end framework usado
* [Maven](https://maven.apache.org/) - Gerenciador de dependencias
* [Angular](https://spring.io/projects/spring-boot) - Front-end framework usado


## Versionamento

Por enquanto não estamos trabalhando com versionamento mas pretendemos colocar em atualizações futuras 

## Autores

* **João Lenon** - *Trabalho Inicial* - [jlenon7](https://github.com/jlenon7)

* **Adryell Nathann** - *Trabalho Inicial* - [adryell](https://github.com/Adryell)

## Licença

Este projeto está licenciado sob a MIT License - veja a [LICENSE.md](LICENSE.md) para mais detalhes

## Agradecimentos

* Agradecimentos ao Gabriel Ulysses que foi essencial no desenvolvimento desse projeto.
