Aqui está o arquivo `instrucoes_de_uso.md` formatado de maneira contínua para facilitar a cópia e colagem direta no seu repositório:

```markdown
# Instruções de Uso para LPM Comidinhas Veganas

## Configuração Inicial

### Pré-requisitos
- JDK 11 ou superior instalado.
- Maven instalado para gerenciamento de dependências.
- Clonar o repositório do projeto.

### Banco de Dados
O projeto suporta dois ambientes de banco de dados:
1. **H2 Database** (embutido, para desenvolvimento e testes)
2. **MySQL** (para produção)

### Arquivo de Configuração `application.properties`
Localize e configure o arquivo `src/main/resources/application.properties` de acordo com o ambiente desejado:

#### Para usar H2 Database (configuração padrão):
```properties
spring.datasource.url=jdbc:h2:mem:lpmcomidinhas;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

#### Para usar MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lpm_comidinhas_veganas?useSSL=false&serverTimezone=UTC
spring.datasource.username=<SEU_USUARIO>
spring.datasource.password=<SUA_SENHA>
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
```
*Substitua `<SEU_USUARIO>` e `<SUA_SENHA>` pelos seus dados de acesso ao MySQL.*

## Construção do Projeto
Execute o seguinte comando no terminal dentro do diretório do projeto para construir o projeto com Maven:
```bash
mvn clean install
```

## Execução
Após a construção, inicie a aplicação usando:
```bash
java -jar target/lpm-comidinhas-veganas-0.1.0.jar
```
A aplicação estará acessível via `http://localhost:8080`.

## Testando a Aplicação
Os testes podem ser executados com o Maven para verificar a integridade do código:
```bash
mvn test
```

## Dados de Teste
Você pode inserir dados de teste manualmente através da interface da aplicação ou utilizar scripts SQL para popular o banco de dados antes de iniciar a aplicação.
```
Lembre-se de substituir `<SEU_USUARIO>` e `<SUA_SENHA>` pelos seus dados de acesso ao MySQL. Este guia está pronto para ser usado como uma documentação completa para instalação e uso do seu sistema.
