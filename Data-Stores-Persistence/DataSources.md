# Data Sources

## Data Source vs. DataSource
- Data source
  - A source of data, such as database
- DataSource
  - Java utility class that manages connections to physical or logical databases
- In particular, Spring injects a Hikari DataSource by default for JDBC connections, which is a lightweight JDBC DataSource that supports connection pooling.

### DataSource
> Java utility class that manages connections to physical or logical databases

#### Basic DataSource
> Produces Connection objects

#### Connection-Pooling DataSource
> Pools Connection objects to maintain a list of open connections and reuse closed connections

#### Distributed Transaction DataSource
> Uses transaction manager to manage distributed requests oftern pools connections as well

## Database URL structure
- jdbc:mysql://localhost:3306/exampledb
- subprotocol // serverName[:port] / databaseInstanceName: properties

## Configure Spring with MySQL
- Configure application.properties
```java
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/plant
spring.datasource.username=sa // can be any username
spring.datasource.password=sa1234 // can be any password
```
- Configure dependency in pom file
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
- SQL execution
```sql
CREATE SCHEMA `plant` ; -- Create the plant database

CREATE USER 'sa'@'localhost' IDENTIFIED BY 'sa1234'; -- Create the user if you haven’t yet
GRANT ALL ON plant.* TO 'sa'@'localhost'; -- Gives all privileges to the new user on plant
```
## Programmatic configuration for MySQL in Spring with @Configuration
- @Configuration annotation at class level
- @Bean for fetch dataSource object from database
- use DataSourceBuilder to create a DataSource
- Set properties after create the DataSource
- 

```java
@Configuration //Set configuration annotation here
public class DatasourceConfig {

   @Bean //Set Bean 
   public DataSource getDatasource() {
       DataSourceBuilder dsb = DataSourceBuilder.create(); //Create DataSource using DataSourceBuilder
       dsb.username("sa2"); //username
       dsb.password(securePasswordService()); //password
       dsb.url("jdbc:mysql://localhost:3306/exercise1"); //JDBC URL here
       return dsb.build(); //Return DataSource
   }

   private String securePasswordService() {
       return "sa1234";
   }
}
```

### @ConfigurationProperties 
> You can also combine properties and programmatic configuration by using the @ConfigurationProperties annotation. Spring will attempt to inject any properties found beginning with the provided prefix into the returned @Bean.
- This will overwrites any property set by programmatic code by using the values preset in property file
```java
@Configuration
public class DatasourceConfig {

   @Bean
   @ConfigurationProperties("foo.datasource") // <- Here, when everything finish in bean, then the ConfigurationProperties overwrites whatever the value stored in property
   public DataSource getDatasource() {
       DataSourceBuilder dsb = DataSourceBuilder.create();
       dsb.password(securePasswordService());
       return dsb.build();
   }

   private String securePasswordService() {
       return "sa1234";
   }
}
```

### DataSourceProperties
> Different DataSource providers sometimes have different names for their properties. You can use a DataSourceProperties object to manage converting between the standard spring.datasource properties and your desired DataSource type by creating a @Bean that returns a DataSourceProperties object you populate from your properties file.

> We will annotate both the DataSourceProperties and DataSource beans with @Primary so that spring knows which beans to use by default of that type.

```java
@Bean
@Primary
@ConfigurationProperties("spring.datasource")
public DataSourceProperties getDataSourceProperties(){
   return new DataSourceProperties();
}

@Bean
@Primary
@ConfigurationProperties(prefix = "spring.datasource.configuration")
public DataSource getDatasource(DataSourceProperties properties) {
   return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
}
```

#### Mutiple DataSource
> If your data is stored in multiple locations, you can access it by creating multiple DataSource @Beans. You will have to provide beans for an EntityManagerFactory and a TransactionManager as well. To support Spring Data repositories, we also use the @EnableJpaRepositories annotation to reference the specific classes.

> Here are the definitions for two different config classes. The first one defines everything we need to load spring.datasource properties into one DataSource for storing our Humanoid Entities. The next one uses the properties from spring.datasource2 to access our Outfit Entities.

- DataSourceConfig.java
```java
//DataSourceConfig.java
@Configuration
@EnableJpaRepositories(basePackageClasses = Humanoid.class, entityManagerFactoryRef = "humanoidFactory")
public class DatasourceConfig {

   @Bean
   @Primary
   @ConfigurationProperties("spring.datasource")
   public DataSourceProperties getDataSourceProperties(){
       return new DataSourceProperties();
   }

   @Bean
   @Primary
   @ConfigurationProperties(prefix = "spring.datasource.configuration")
   public DataSource getDatasource(DataSourceProperties properties) {
       return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean(name = "humanoidFactory")
   @Primary
   public LocalContainerEntityManagerFactoryBean humanoidEntityManagerFactory(
           EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
       return entityManagerFactoryBuilder
               .dataSource(getDatasource(getDataSourceProperties()))
               .packages(Humanoid.class)
               .persistenceUnit("humanoid")
               .build();
   }

   @Bean(name = "humanoidTx")
   @Primary
   public PlatformTransactionManager humanoidTransactionManager(@Qualifier("humanoidFactory")EntityManagerFactory entityManagerFactory){
       return new JpaTransactionManager(entityManagerFactory);
   }

}
```
- DataSourceConfig2.java
```java

@Configuration
@EnableJpaRepositories(basePackageClasses = {Outfit.class, OutfitRepository.class}, entityManagerFactoryRef = "outfitFactory")
public class Datasource2Config {

   @Bean
   @ConfigurationProperties("spring.datasource2")
   public DataSourceProperties getDataSource2Properties(){
       return new DataSourceProperties();
   }

   @Bean
   @ConfigurationProperties(prefix = "spring.datasource2.configuration")
   public DataSource getDatasource2(DataSourceProperties properties) {
       return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean(name = "outfitFactory")
   public LocalContainerEntityManagerFactoryBean outfitEntityManagerFactory(
           EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
       return entityManagerFactoryBuilder
               .dataSource(getDatasource2(getDataSource2Properties()))
               .packages(Outfit.class)
               .persistenceUnit("outfit")
               .build();
   }

   @Bean(name = "outfitTx")
   public PlatformTransactionManager outfitTransactionManager(@Qualifier("outfitFactory")EntityManagerFactory entityManagerFactory){
       return new JpaTransactionManager(entityManagerFactory);
   }

}
```

