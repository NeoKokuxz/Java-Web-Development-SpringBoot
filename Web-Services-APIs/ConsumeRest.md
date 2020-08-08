# Consume RESTful API - SOAP & Spring Web Services

- Jackson 
library that serializes or maps Java objects to JSON and vice versa

- Jackson dependency
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
</dependency>
```

## SOAP Web Service

## Spring Web Service
Auto generates the WSDL file for the SOAP web service
- WSDL stand for Web Services Description Language
- Dependencies
```xml
spring-boot-starter-web-services
```
- Generate Java Files needs following plugins 
```xml
<plugin>
   <groupId>org.jvnet.jaxb2.maven2</groupId>
  <artifactId>maven-jaxb2-plugin</artifactId>
  <version>0.14.0</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
       <configuration>
        <schemaDirectory>${project.basedir}/src/main/resources/wsdl</schemaDirectory>
         <schemaIncludes>
            <include>*.wsdl</include>
        </schemaIncludes>
    </configuration>
</plugin>
```
>This plugin uses JAXB, which generates the Java classes and handles the mapping of XML to Java and vice versa. In order to generate the Java files, run the mvn generate-sources  Maven command. This can easily be done via the command line or through IntelliJ. This results in a number of generated Java classes under /target/generated-sources/xjc. Once you have the generated code, you can create a web service client by simply extending the WebServiceGatewaySupport class and coding your operations.

