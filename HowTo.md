# How to make this application working
## Documentation purpose
The goal of this document is explaining various informations about this project. It may contains : 
- basics informations like "how launching the application" or "how to configure it"
- explanations about some concepts for learning purposes
- indications about tricky issues and how to solve them

**What this documentation won't contains :** explanations about the code. All needed indications (including clarification given in teaching perspective) will be included in code itself as commentaries.

## Application informations
#### What Spring Boot offer
Spring Boot provide a fast way to build applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure. Here are some examples :
- Is Spring MVC on the classpath? There are several specific beans you almost always need, and Spring Boot adds them automatically. A Spring MVC application also needs a servlet container, so Spring Boot automatically configures embedded Tomcat.
- Is Jetty on the classpath? If so, you probably do NOT want Tomcat but instead want embedded Jetty. Spring Boot handles that for you.
- Is Thymeleaf on the classpath? If so, there are a few beans that must always be added to your application context. Spring Boot adds them for you.

These are just a few examples of the automatic configuration Spring Boot provides. Keeo in mind that Spring Boot does not generate code or make edits to your files. Instead, when you start your application, Spring Boot dynamically wires up beans and settings and applies them to your application context. 

#### How to launch the application (for Windows)
- With command prompt, go to project's folder.
- Check it well contains <span style="color: green;">*mvnw*</span> file (provided by default if project was created using <span style="color: green;">*spring initializr*</span>).
- Use <span style="color: green;">*mvnw spring-boot:run*</span>. The application will listen by default on <a href="http://localhost:8080">http://localhost:8080</a>.


This works because Spring Boot embed an Apache Tomcat server that is acting as a webserver and is listening for requests on localhost port 8080.