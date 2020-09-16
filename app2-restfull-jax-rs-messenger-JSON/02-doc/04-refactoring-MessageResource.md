# refactoring MessageResource


placer sur la classe directement les annotations :

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
AVANT :

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,
								 Message message) {
		if (message.getId() == id) {
			return messageService.updateMessage(message);
		}
		return null;
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
	
}
````

APRES modification :

````java
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) 	//<<<<<<<<<<<<<<<<																																																																									
@Produces(MediaType.APPLICATION_JSON)	//<<<<<<<<<<<<<<<<		
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	
	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,
								 Message message) {
		if (message.getId() == id) {
			return messageService.updateMessage(message);
		}
		return null;
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
	
}

````
