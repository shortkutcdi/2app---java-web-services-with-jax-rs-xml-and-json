# accessing Path params

[jersey examples](https://github.com/jersey/jersey/blob/2.27/examples/server-sent-events-jersey/src/main/java/org/glassfish/jersey/examples/sse/jersey/ServerSentEventsResource.java)

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/test/{messageId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(@PathParam("messageId") String messageId) {
		//messageService.getMessage(messageId);
		return "Got that param " + messageId;
	}
	
	@GET															//<<<<<<<<<<
	@Path("/{messageId}")											//<<<<<<<<<<
	@Produces(MediaType.APPLICATION_XML)							//<<<<<<<<<<
	public Message getMessage(@PathParam("messageId") long id) {	//<<<<<<<<<<
		return messageService.getMessage(id);						//<<<<<<<<<<								
	}
}

````

http://localhost:8081/30-restfull-jax-rs-messenger/webapi/messages/1

affiche :

	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<message>
	    <author>Koushik</author>
	    <created>2018-07-30T19:01:21.427+02:00</created>
	    <id>1</id>
	    <message>Hello World!</message>
	</message>
	
http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/test/unmessage

affiche : Got that param unmessage 


http://localhost:8081/30-restfull-jax-rs-messenger/webapi/messages/2

affiche :

	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<message>
	    <author>Koushik</author>
	    <created>2018-07-30T19:03:19.440+02:00</created>
	    <id>2</id>
	    <message>Hello Jersey!</message>
	</message>


## plusieurs paramètres

	/something/{id1}/name/{id2}

````java
	@GET
	@Path("/{id1}/name/{id2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(@PathParam("id1") int id1,
						@PathParam("id2") int id2) {
		//messageService.getMessage(messageId);
		return "Got that param " + id1 + " and " + id2;
	}
````
