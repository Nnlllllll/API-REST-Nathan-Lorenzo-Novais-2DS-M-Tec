API de Controle de Estoque e Vendas

API REST desenvolvida em Spring Boot para gerenciamento de estoque, produtos, clientes e vendas com baixa automática de estoque.

Tecnologias Utilizadas

· Java 17
· Spring Boot 3.x
· Spring Data JPA
· Maven
· MariaDB
· Jakarta Validation
· Lombok

Funcionalidades

· Cadastro e gerenciamento de produtos, categorias, fornecedores e clientes
· Controle de estoque com atualização automática
· Registro de vendas com baixa automática no estoque
· Validação de estoque insuficiente e rollback de transações
· Endpoints REST para todas as operações CRUD

Entidades Principais

1. Produto - Produtos disponíveis para venda
2. Categoria - Categorias de produtos
3. Estoque - Controle de quantidade disponível (1:1 com Produto)
4. Cliente - Clientes cadastrados
5. Venda - Registro de vendas
6. ItemVenda - Itens de uma venda (tabela intermediária)
7. Fornecedor - Fornecedores dos produtos

Instalação e Configuração

Pré-requisitos

· Java 17 ou superior
· Maven 3.6+
· MariaDB/MySQL

Passos para Execução

1. Clone o repositório:

git clone https://github.com/seu-usuario/ControleEstoque-20240024.git
cd ControleEstoque-20240024

1. Configure o banco de dados:
   · Crie um banco de dados no MariaDB/MySQL chamado estoque_db
   · Configure as credenciais no arquivo src/main/resources/application.properties
2. Execute a aplicação:

mvn clean spring-boot:run
(no diretorio da api)

1. A API estará disponível em: http://localhost:8080

Endpoints da API

Clientes

· GET /api/clientes - Lista todos os clientes
· GET /api/clientes/{id} - Busca cliente por ID
· POST /api/clientes - Cria novo cliente
· PUT /api/clientes/{id} - Atualiza cliente
· DELETE /api/clientes/{id} - Remove cliente

Vendas

· GET /api/vendas - Lista todas as vendas
· GET /api/vendas/{id} - Busca venda por ID
· POST /api/vendas - Registra nova venda (com baixa automática no estoque)

Produtos

· GET /api/produtos - Lista todos os produtos
· GET /api/produtos/{id} - Busca produto por ID
· POST /api/produtos - Cria novo produto
· PUT /api/produtos/{id} - Atualiza produto
· DELETE /api/produtos/{id} - Remove produto

Categorias

· GET /api/categorias - Lista todas as categorias
· GET /api/categorias/{id} - Busca categoria por ID
· POST /api/categorias - Cria nova categoria
· PUT /api/categorias/{id} - Atualiza categoria
· DELETE /api/categorias/{id} - Remove categoria

Fornecedores

· GET /api/fornecedores - Lista todos os fornecedores
· GET /api/fornecedores/{id} - Busca fornecedor por ID
· POST /api/fornecedores - Cria novo fornecedor
· PUT /api/fornecedores/{id} - Atualiza fornecedor
· DELETE /api/fornecedores/{id} - Remove fornecedor

Configuração do Banco de Dados

Edite src/main/resources/application.properties:

spring.datasource.url=jdbc:mariadb://localhost:3306/estoque_db
spring.datasource.username=root
spring.datasource.password=COLOQUESUASENHA
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
