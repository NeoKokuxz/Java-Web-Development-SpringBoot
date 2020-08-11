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

### Spring-ws-core
- Help creation and consumption of XML 
- XML mapping
- Parsing XML
- Contract-first only
- Generate domain object based on WSDL

- Mapping XML to Java and Vice Versa
- Set up dependency and plug in below
```xml
  
		<!--Marshalling/UnMarshalling Dependencies-->
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-core</artifactId>
			<version>2.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-impl</artifactId>
			<version>2.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.messaging.saaj</groupId>
			<artifactId>saaj-impl</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>javax.xml.ws</groupId>
			<artifactId>jaxws-api</artifactId>
			<version>2.2.6</version>
      
      <!--Plug in-->
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
### Create Client
- Extend WebServiceGatewaySupport
	- Code operation
```java
public class NumberClient extends WebServiceGatewaySupport {

    public NumberToWordsResponse getWords(String numbers) {
            //publicly accessible SOAP service
            String uri = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";

            //set the request
            NumberToWords numberRequest = new NumberToWords();
            numberRequest.setUbiNum(new BigInteger(numbers));

            //obtain and return the response
            NumberToWordsResponse response =
                    (NumberToWordsResponse) getWebServiceTemplate().marshalSendAndReceive(uri,numberRequest);

            return response;
    }

}
```
#### Jaxb2Marshaller 
- this is to serialize and de-serialize XML requests to and from Java

## Documentation for REST APIs
Documentation is a good way to communicate to others how they can use your APIs. Documentation communicates the operations available, the format for requesting data and the format data will be returned in.

### OpenAPI
OpenAPI allows to describe API using JSON or YAML. 
	- YAML is a mark up language used for configuration files
		- Available endpoints
		- Operations with parameters
		- Authentication methods
		- Contact information about API
		
### Swagger
Swagger is the name associated with some of the most well-known and widely used tools for implementing the OpenAPI specification. It helps design, build and consume APIs.
	- SpringFox - A swagger intergration for the Spring Framework.
	- Swagger Editor – A browser-based editor where you can write OpenAPI specs
	- Swagger UI – A web application that renders OpenAPI specs as interactive API documentation.
	- Swagger Codegen – A tool that generates server stubs and client libraries from an OpenAPI spec.
	
	- Dependencies
	```xml
	<dependency>
	    <groupId>io.springfox</groupId>
	    <artifactId>springfox-swagger-ui</artifactId>
	    <version>2.9.2</version>
	</dependency>

	<dependency>
	    <groupId>io.springfox</groupId>
	    <artifactId>springfox-swagger2</artifactId>
	    <version>2.9.2</version>
	    <scope>compile</scope>
	</dependency>
	```

#### Swagger UI
Dynamically generated documentation from a Swagger-compliant API is displayed in the Swagger UI, which consists of HTML, JavaScript, and CSS files. The documentation files are bundled by Swagger UI to display the API in a web browser.



