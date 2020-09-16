# developing RESTful APIs with JAX-RS

## new jersey project

Jersy is JAX-RS implementation

[maven jersey configuration](https://jersey.github.io/download.html)

new maven project

GroupId=org.glassfish.jersey.archetypes
ArtifactId=jersey-quickstart-webapp
Version=2.27

## web.xml

````xml
    <servlet>
        <servlet-name>Jersey Web Application</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>org.koushik.javabrains.messenger</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Jersey Web Application</servlet-name>
        <url-pattern>/webapi/*</url-pattern>   // si cette route est renseignée la servlet jersey sera appelée
    </servlet-mapping>
````

Les classes se trouvant dans le package __org.koushik.javabrains.messenger__
Pourrons utiliser l'api Jersey 

## MyResource


````java
package org.koushik.javabrains.messenger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN) // retourne du contenu texte
    public String getIt() {
        return "Got it!";	
    }
}

````

run / run on server (appache)

/webapi/... va appeler la servlet Jersey - org.glassfish.jersey.servlet.ServletContainer
webapi/myresource -- va appeler la classe (MyResource )avec @path("myresource") et la méthode @Get


http://localhost:8081/30-restfull-jax-rs-messenger/
													webapi					// servlet-mapping><url-pattern> webapi/*
															/myresource    // voir MyResource.java
																				// @path("myresource") 
affiche : Got it!		

## nouvelle classe MessageResource REST

1- create a new Java class MessageResource
2- add a methode that returns the response
3- Make sure your class is in the package configured in Jersey servlets's __init-param__
4- Annotate class with __@Path__ annotation - @Path("/resources")
5- Annotate method with the right HTTP method annotation - @GET
6- Annotate method with the __@Produces__ specifying response format - @Produces(MediaType.TEXT_PLAIN)


S'assurer que la classe se trouve dans le package décrit dans web.xml servlet/init-param/param-value

    <servlet>
        <servlet-name>Jersey Web Application</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>org.koushik.javabrains.messenger</param-value>			//<<<<<<<<<<<
        </init-param>

ajout classe __MessageResource__ dans le sous-package de org.koushik.javabrains.messenger : __resources__

la classe est annotées __@Path("resources")__

la route __/webapi/resources__ : appelera la classe annotée @Path("resources") -__MessageResource__-, 
et sa méthode __@GET__

ici la méthode est __getMessages()__ annotée :
- __@GET__ - reponds à une methode HTTP GET
- __@Produces(MediaType.TEXT_PLAIN)__ - enverra une réponse de type text
La méthode retourne "Hello World!" c'est ce qui est retourné avec l'url "webapi/resources"

````java
package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("resources")
public class MessageResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages() {
		return "Hello World!";
	}

}
													
````													

webapi/resources ==> affiche :  Hello World!