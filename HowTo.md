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

#### Packages tree
Each package will content tutorials about a given point :
- <u>*tutorial.springboot*</u> : Explanations about Spring boot main class, and launching of the application.
- <u>*tutorial.springboot.basics*</u> : Show of to create basic HelloWorld rest application.
- <u>*tutorial.springboot.restful*</u> : Explain how to build a RESTful Web Service.

#### Launch the application (for Windows)
- With command prompt, go to project's folder.
- Check it well contains <span style="color: green;">*mvnw*</span> file (provided by default if project was created using <span style="color: green;">*spring initializr*</span>).
- Use <span style="color: green;">*mvnw spring-boot:run*</span>. The application will listen by default on <a href="http://localhost:8080">http://localhost:8080</a>.

This works because Spring Boot embed an Apache Tomcat server that is acting as a webserver and is listening for requests on localhost port 8080.

#### Add Production-grade Services
 Spring Boot provides several management services (such as health, audits, beans, and more) with its actuator module (documentation at <span style="color: green;">*https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#actuator*</span>).
 
To use this services, we need to add some maven dependency :
<dependency>
&nbsp;&nbsp;&nbsp;&nbsp;<groupId>org.springframework.boot</groupId>
&nbsp;&nbsp;&nbsp;&nbsp;<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

The actuator will exposes the following :
- <a href="http://localhost:8080/actuator/health">actuator/health</a>
- <a href="http://localhost:8080/actuator">actuator</a>

All details about each available REST endpoints and how we can tune their settings with an application.properties file (in src/main/resources) are available in documentation located at <span style="color: green;">*https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#actuator.endpoints*</span>.

As a conclusion, we will note that there also is a */actuator/shutdown* endpoint, visible only through JMX by default. To enable it as an HTTP endpoint, add <span style="color: green;">*management.endpoint.shutdown.enabled=true*</span> to application.properties file and expose it with <span style="color: green;">*management.endpoints.web.exposure.include=health,info,shutdown*</span>.
<br/><u>Please beware that we should not enable the shutdown endpoint for a publicly available application.</u>

#### Build an executable JAR
- With command prompt, go to project's folder.
- Check it well contains <span style="color: green;">*mvnw*</span> file (provided by default if project was created using <span style="color: green;">*spring initializr*</span>).
- Use <span style="color: green;">*mvnw clean package*</span>.

Then, if you wan't to run the application using this jar, just place yourself into the folder containing the jar and execute <span style="color: green;">*java -jar {place name of jar here}.jar*</span>

#### Spring Boot Devtools


A common feature of developing web applications is coding a change, restarting the application, and refreshing the browser to view the change. This entire process can eat up a lot of time. To speed up this refresh cycle, Spring Boot offers with a handy module known as <span style="color: green;">spring-boot-devtools</span>. Spring Boot Devtools:
- Enables hot swapping.
- Switches template engines to disable caching.
- Enables LiveReload to automatically refresh the browser.
- Other reasonable defaults based on development instead of production.


