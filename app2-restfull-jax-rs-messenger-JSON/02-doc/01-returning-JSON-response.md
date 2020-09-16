# returning JSON response

## Ajouter la dépendance jersey

configurer le pom.xml

````xml
<!-- uncomment this to get JSON support -->
<dependency>
	<groupId>org.glassfish.jersey.media</groupId>
	<artifactId>jersey-media-json-binding</artifactId>
</dependency>
````
        
## MessageRessource

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
//	@Produces("application/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)				//<<<<<<<<<<<<<<
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)				//<<<<<<<<<<<<<<
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
	
}        
````

## résultats - postman client    
        
http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages

	[
	    {
	        "author": "Koushik",
	        "created": "2018-07-30T18:36:02.205Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-30T18:36:02.205Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    }
	]

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/1

	{
	    "author": "Koushik",
	    "created": "2018-07-30T18:36:53.912Z[UTC]",
	    "id": 1,
	    "message": "Hello World!"
	}