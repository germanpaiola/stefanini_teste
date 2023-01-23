Teste de Conhecimentos para Desenvolvedor Java

Introdução

Este sistema tem por finalidade apresentar estruturas de desenvolvimento para servir de base à avaliação pela Stefanini.

Descrição do projeto

O sistema foi desenvolvido com base na arquitetura MVC, separando as camadas de model, service e controller.
Foi utilizado Gradle 2.2.1 para build e teste diretamente pelo CLI. Por ser uma versão antiga, as IDEs não têm mais suporte, apesar do build pela CLI funcionar corretamente a IDE não reconhece as dependências. A solução foi adicionar manualmente as dependências na IDE para que facilitasse o desenvolvimento;
O desenvolvimento de testes foi utilizado JUnit, executado pelo Gradle;
Banco de dados utilizado foi o MySql em um container Docker e para persistência foi utilizado o framework Hibernate/JPA;

Entidades

Foram apresentadas duas entidades, Pedido e Item, em uma relação um-pra-muitos.
Para atender a esta relação, foi adicionado um novo atributo na entidade Pedido, que representa uma coleção de Item. A partir dessa coleção, foi mapeado uma tabela relacional pedido_item para organizar essa relação.
Outro detalhe, foi na chave primária da entidade Item, em que o nome constava como IdProduto, que não tem relação com o nome da entidade e nem com outra entidade. Sendo assim para atender melhores práticas, modei para apenas id, assim como na entidade Pedido.

Override/Overload

Neste projeto, houveram inúmeras aplicações de Override, pelo uso de interfaces. Como exemplo pode-se observar as classes ItemRepository e ItemRepositoryImpl;
Agora para Overload, foi desenvolvida apenas uma aplicação na classe Calculadora, apenas por demonstração. Entendo que a nomenclatura do método deve ser o mais claro possível, preferindo dividir em dois métodos diferentes e, por ser um projeto simples, faltaram oportunidades para criação de um método com sobrecarga limpo e conciso.

Padrões de Projeto

Novamente, por ser um projeto pequeno, adicionar padrões apenas para demonstração apenas geraria mais complexidade ao código. 
No entanto, apliquei um builder, por entender que facilita e deixa o código mais legível ao desenvolver, removendo chamadas de construtores cheios de parâmetros e retirando a responsabilidade das entidades de apresentar construtores para todas as combinações de parâmetros.
Para o JSP, a idéia era desenvolver um facade, escondendo a regra de negócio nos controllers e services.

Front e JSP

Como solicitado, o teste exigia a implementação de um front em JSP, sem frameworks, em um servidor Tomcat. Devido a dificuldade de desenvolver e de achar documentações sem aplicações de frameworks (como Spring e JSF), decidi por não desenvolver essa parte. 
A versão do Gradle 2.2.1, também conta com pouquíssima documentação e dificultou para esse desenvolvimento.

Fatores Limitantes

Alguns fatores limitaram o melhor andamento do desenvolvimento do projeto, o maior destaque fica para as tecnologias com versões mais antigas, que demanda muito tempo para entender e codificar, além da pouca documentação. O segundo é o tempo: para desenvolvimento com testes e garantir a qualidade do código (como em TDD) o tempo é sempre escasso, devido a esse motivo poucos testes foram escritos apenas para averiguar o correto funcionamento do caminho feliz, sem testar os possíveis desvios.
SQLs

create database stefanine_teste
use stefanine_teste

create table itens (
id integer not null auto_increment,
descricao varchar(255),
quantidade integer,
valor double precision,
primary key (id)
)


create table pedidos (
	id integer not null auto_increment,
	nome_cliente varchar(255),
	tipo varchar(255),
	cnpj_ou_cpf varchar(255),
	data_compra date,
	valor_total double precision,
	primary key (id)
)

create table pedido_item (
	pedido_id integer,
	item_id integer,
	foreign key(pedido_id) references pedidos(id),
	foreign key(item_id) references itens(id)
)

	Arquivo de Persistência

<?xml version="1.0" encoding="UTF-8" ?>

<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
                   http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
                   version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">

   <persistence-unit name="all-classes">
       <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
       <class>com.stefanine.model.Item</class>
       <class>com.stefanine.model.Pedido</class>
       <properties>
           <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
           <property name="javax.persistence.jdbc.url" value="jdbc:mysql://172.17.0.2:3306/stefanine_teste" />
           <property name="javax.persistence.jdbc.user" value="admin" />
           <property name="javax.persistence.jdbc.password" value="123" />
           <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
           <property name="hibernate.show_sql" value="true" />
           <property name="hibernate.format_sql" value="true" />
       </properties>
   </persistence-unit>
</persistence>
