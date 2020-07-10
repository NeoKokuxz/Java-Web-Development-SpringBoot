# MVC
Model-View-Controller
- Model
  - responsible for maintaining the state of an application
- View
  - responsible for displaying the UI to the user
- Controller
  - responsible for processing user actions (sent from the View) to update the Model, and for forwarding those updates back to the View
  
## Model
- Model are the objects, every controller method can take an optional Model argument, by reading and changing the data inside of it, the controller can read user-submitted data and populate the template with the changes. 
- Act like data-transfer object, transfer the data between user and application.
```html
<h1 th:text="${welcomeMessage}">  <h1>
```

```java
@RequestMapping("/home")
public String getHomePage(Model model){
  model.addAttribute("welcomeMessage" , "Hi, Hello");
  return "home";
}
```

## Controller
- Bean object can be controllers, just add @Controller annotation to allow it handle requests.
  
## HTML templates
- This is the view.
- Each one represents a specific screen or screen componenet that user is shown.

## Spring beans
- this is the controllers, @Controller annotation that can use to register beans as controllers.
- The methods inside bean are responsible for choosing HTML template and populate Model object for that template.

# Spring MVC
- User action -> HTTP requests
- View update -> HTTP responses
- The controller is responsible for choosing the template and data a view is generated from. 

# Work FLow
- The web server receives an HTTP request and uses it to call a method in the Spring controller. The Spring controller populates a model object and returns a String with the view id. The template corresponding to the view id is populated with data from the model object.
- The controller connects data to template
- User data can be either encoded into request URL or sent in body of request message

# Thymeleaf
is the HTML template engine

## Iteration 
```html
<h1  th:each="msg : ${greetings}" th:text="${msg}">Hello, homepage!</h1>
```
same as:
```html
<h1>Hi</h1>
<h1>Hello</h1>
```
## Conditional rendering
```html
<h1  th:unless="${msg.contains('goodbye')}" th:text="${msg}">Hello, homepage!</h1>
```

## Endpoint calling
```html
th:action="@{'/simplehome'}" //This calls the /simplehome endpoint controller, 
```

## if
```html
<h1 th:if="${firstVisit}">Hello First visit</h1>
or
<h1 th:unless="${firstVisit}">Welcome back</h1> //if not first visit, cout welcome back
```

## Dynmaic 
- Every form has a simple POJO that represents the type of data in the form.
- Spring instantiates and populates this form data class when a request comes in.
- A controller method returns a string that represents the template to render.
```html
//The th:object
//The th:field auto reference back to th:object
// action -> send to whichever page that specs

<form th:object="${newMessage}" th:action="@{/home}" action="#" method="POST">
    <input th:field="*{text}" type="text">
    <input type="submit">
</form>
```
- th:field
- th:action

# Form-Backing Object 
Form-Backing Object: This is a term used by Spring MVC and Thymeleaf to mean an object that represents the data contained in a form. On the Spring side, this is usually an additional argument to the relevant Controller method, and on the Thymeleaf side, this is referred to in the th:object attribute on the form.

# @ModelAttribute
This allows us to specify that Spring should add the object to our Model before asking Thymeleaf to render the template. That means we don't have to add it manually!

# Exercise Connecting Controller to Templates
```html
<body>
<form action="#" th:action="@{'/animal'}" th:object="${messageForm}" method="POST">
   <label for="animalText">Enter an Animal: </label>
   <input type="text" id="animalText" name="animalText" th:field="*{animalName}">
   <label for="adjective">Enter an Adjective:</label>
   <input type="text" id="adjective" name="adjective" th:field="*{adjective}">
   <input type="submit">
</form>
   <h1 th:unless="${#lists.size(greetings) > 5}" th:each="msg : ${greetings}" th:text="${msg}">Hello, homepage!</h1>
   <h1 th:if="${#lists.size(greetings) > 5}">I think that's enough!</h1>
</body>

```

## File upload
```html
<form action="#" enctype="multipart/form-data">
    <input type="file" name="fileupload">
</form>
```
in Spring
```java
@PostMapping("/file-upload")
public string handleFileUpload(@RequestParam("fileUpload") MutipartFile fileupload, Model model){
  InputStream fis = fileupload.getInputStream();
}
```
We need @RequestParam to link to the html form with correct data file


