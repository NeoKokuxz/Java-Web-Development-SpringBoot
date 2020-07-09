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
