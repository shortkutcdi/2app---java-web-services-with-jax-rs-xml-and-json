# Send status codes and Location Headers

On retourne une Response


.created() - 201
.ok() 200

````java
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();


	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message); // http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/
		
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); // ajout à uriInfo newId - getAbsolutePathBuilder pour construire l'uri
		return Response
				.created(uri)          // status 201 - created
//				.status(Status.CREATED)
				.entity(newMessage)
				.build(); //  envoi d'une response tjrs terminer par buil()
	}
````

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/ - POST

    {
        "author": "Koushik",
        "comments": {},
        "message": "Test message"
    }
    
Affiche 

	Headers : 
	content-type => application/json
	location	 => http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/3
	
	Status: 201 (created)