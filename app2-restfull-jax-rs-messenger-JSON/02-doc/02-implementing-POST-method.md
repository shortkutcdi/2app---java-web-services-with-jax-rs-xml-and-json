# implementing POST method

````java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
//	@Produces("application/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	
	@POST									//<<<<<<<<<<<<<<
	@Produces(MediaType.APPLICATION_JSON)	//<<<<<<<<<<<<<<
	public String addMessage() {			//<<<<<<<<<<<<<<
		return "POST works!";				//<<<<<<<<<<<<<<
	}
````

## test POST method - postman

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/2 -- GET

copier une entrée json supprimer son id et modifier
    {
        "author": "Koushik",
        "created": "2018-07-30T19:03:56.781Z[UTC]",
        "message": "Hello Jersey pullover!"          //<<<<< modif
    }


http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages -- POST

- __header__ : Content-type / value : application/json

Ajouter à __raw__ (json) :

    {
        "author": "Koushik",
        "created": "2018-07-30T19:03:56.781Z[UTC]",
        "message": "Hello Jersey pullover!"         
    }

- __send__

Affiche : 

	{
	    "author": "Koushik",
	    "created": "2018-07-30T19:03:56.781Z[UTC]",
	    "id": 3,
	    "message": "Hello Jersey pullover!"
	}

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages - GET - SEND

affiche :

	[
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T06:38:47.69Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-31T06:38:47.69Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-07-30T19:03:56.781Z[UTC]",
	        "id": 3,
	        "message": "Hello Jersey pullover!"
	    }
	]