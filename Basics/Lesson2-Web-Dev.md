# 3 Main components in web development
- Data storage
- Application Logic
- Client Access

# Develope Roles
- Developers who write the code.
- Testers who write and run tests.
- Designers who create prototypes for the developers to implement.
- Project Architects who choose the technologies best suited to a project's requirements
- Business Analysts who write technical specifications for the developers to follow.
- Project Managers who plan development efforts.

# Automated User Testing
- Selenium is java standard AUT

# OutLine
- Basics of Java server architecture, dependency management in Java, and how Spring integrates with both.
- Core Spring principles. We'll be covering dependency injection, bean configuration, service development, and server-wide configuration.
- Spring MVC and Thymeleaf, an HTML template engine. We'll talk about Spring controllers, Thymeleaf template attributes, and connecting the two with the MVC pattern.
- Connecting your Spring app to a database and securing it with Spring Security. We'll cover the basics of ORM and MyBatis, an ORM tool for Java. We'll use the database to store user credentials securely and use them to authenticate users with Spring Security.
- Testing and web browser automation with Selenium. We'll cover how to set up and run tests with JUnit, how a web driver works, and how to simulate user actions in the browser with Selenium. We'll also discuss page objects, Selenium's powerful abstraction tool.

# Java Application Server
 - Application servers do the hard work of parsing and routing requests, passing them to individual applications through a common interface, the servlet. Those specialized applications can perform complex logic before sending a response back through the servlet for the server to pass on to the client.

# HTTP
- HTTP Request/Response: HTTP, or HyperTextTransferProtocol, is a binary data format for communication between programs over the web. 
  - It can be broken down into two basic message types: requests and responses. 
  - Clients send requests for resources to servers, which respond with the requested data
- Methods
  - GET - request for data from server
  - POST - request to post new data to the server
  - 
  
# Web Development Fundamentals 
- Processing HTTP Requests

## Java Application Servers
Application servers provide utilities and resources to the applications they host, but they don't perform any of what we call business logic
- GlassFish
- Apache Tomcat
- Netty
- WildFly
- Etc...

## Web apps
- Database Access
- Login Security
- Rendering HTML
- Logging
- Testing

# Java Servlet
- Servlets are the interface between an application and the java application server it  runs on. 
- Each servlet in an application represents a configurable endpoint for client connections. 
- Application Servers receive HTTP requests, parse the information, and decide which Servlet should receive a request object.

## Servlet Essentials
- must have service() method which encapsulates the servlet's client-handling logic
 - service() method invokes on an instantiated servlet to handle:
  - ServletRequest and ServletResponse for incoming request. 
- may have init() and/or destory() method, which server calls after creating the servlet or before removing it, respectively. 
- web.xml is the configuration file for application server, which maps specific request path to specific servlets. 

## Endpoints
is the address at which a client can reach a specific part of a server's functionality, usually the URL path as /helloworld/and/here that follows the domains of a URL like .com or .org

## JAR
A Java Archive file, which stores compiled .class files in a folder hierarchy that matches the code's package structure. Includes an optional manifest file.

## WAR
A variation on the JAR for web applications, which optionally includes web resources like HTML files and configuration files like web.xml for servlet registration/mapping.


