# implementing Update and Delete

## update - MessageResource

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	
	// other methods
	
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
````
	
	
	
	
__afficher -GET__ les messages (GET) :	http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																								 /messages
	
	[
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:04:31.285Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:04:31.285Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T06:57:05.073Z[UTC]",
	        "id": 3,
	        "message": "Hello UPDATED UPDATED World!!!!!"
	    }
	]
	
__modifier PUT__ le message d'id:3 : http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																							  /messages/3

HEADER: Content-type / value: application/json

RAW:

    {
        "author": "Koushik",
        "created": "2018-07-31T06:57:05.073Z[UTC]",
        "id": 3,
        "message": "Hello UPDATED World!!!!!"
    },

SEND:

affiche

    {
        "author": "Koushik",
        "created": "2018-07-31T06:57:05.073Z[UTC]",
        "id": 3,
        "message": "Hello UPDATED World!!!!!"
    },


## Delete - messageResource

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	
	// other methods
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
````
	
__Afficher GET__les messages : http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																						/messages

SEND:
	
	[
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:26:58.141Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:26:58.141Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T06:57:05.073Z[UTC]",
	        "id": 3,
	        "message": "Hello UPDATED UPDATED World!!!!!"
	    }
	]
	
__Supprimer DELETE__ id:10:  http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																					  /messages/10

id:10 n'existe pas ne renvoie rien pas de modification

__Supprimer DELETE__ id:3 :  http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																					  /messages/3

SEND: affiche

	    {
	        "author": "Koushik",
	        "created": "2018-07-31T06:57:05.073Z[UTC]",
	        "id": 3,
	        "message": "Hello UPDATED UPDATED World!!!!!"
	    }

__Afficher GET__ les messages : http://localhost:8082/30-restfull-jax-rs-messenger/webapi
																						 /messages

SEND: Le message id:3 a bien été supprimé
	
	[
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:26:58.141Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T07:26:58.141Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    }	    
	    
	    