# Developer notes

## Configuration

The application uses the [Descriptor-less deployment](https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3) pattern, which requires a Servlet 3.0 container. The `ParserResourceConfig` class specifies the application context, the resource package and registers a Jersey [logging feature](https://jersey.github.io/documentation/latest/logging_chapter.html). The CORS configuration is specified via a container response filter which is annotated as a JAX-RS provider for automatic discovery.

Application logging is made with Log4J2. Log4J2 [works automatically](https://logging.apache.org/log4j/2.x/manual/webapp.html#Servlet-3.0) with Servlet 3.0 containers (Tomcat needs additional configuration for versions before 7.0.43), but a [`log4j-web`](https://logging.apache.org/log4j/2.x/maven-artifacts.html) Maven dependency [must be added](https://logging.apache.org/log4j/2.x/manual/webapp.html).

## Web service to get an image of the parse tree

The `org.antlr.v4.gui.Trees` class contains methods that produce a PostScript (PS) file out of an ANTLR tree (see [Javadoc](https://www.antlr.org/api/JavaTool/org/antlr/v4/gui/Trees.html)).

*Attention:* the Antler runtime package also contains a `Trees` class (`org.antlr.v4.runtime.tree.Trees`), which does not contain the PS methods. The Maven [ANTLR 4 Runtime](https://mvnrepository.com/artifact/org.antlr/antlr4-runtime) dependency is not sufficient to use the `org.antlr.v4.gui.Trees` class: it has to be replaced by [ANTLR 4 Tool](https://mvnrepository.com/artifact/org.antlr/antlr4) (which requires the runtime).

The [Ghost4J library](http://www.ghost4j.org/) seems to be a solution for converting PS files to images.