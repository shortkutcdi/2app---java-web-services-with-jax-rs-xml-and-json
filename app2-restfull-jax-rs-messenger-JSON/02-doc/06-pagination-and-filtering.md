# pagination and filtering


## MessageService - getAllMessagesForYear(), getAllMessagesPaginated


````java
public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Koushik"));
		messages.put(2L, new Message(2, "Hello Jersey!", "Koushik"));
	} 
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		return list.subList(start, start + size);
	}
	
````

## MessageResource

````java
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(	@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size) {
		if (year > 0) {
			System.out.println("***** INSIDE ***** " + year);
			return messageService.getAllMessagesForYear(year);
		} 
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}

		System.out.println("***** OUTSIDE ***** " + year);
		return messageService.getAllMessages();
	}

````


http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages?year=2017 - GET

affiche : []

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages?year=2018 - GET

affiche :

	[
	    {
	        "author": "Koushik",
	        "created": "2018-08-03T07:44:12.506Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-08-03T07:44:12.506Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    }
	]
	
http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages?start=0&size=2
	
affiche :

	[
	    {
	        "author": "Koushik",
	        "created": "2018-08-03T07:44:12.506Z[UTC]",
	        "id": 1,
	        "message": "Hello World!"
	    },
	    {
	        "author": "Koushik",
	        "created": "2018-08-03T07:44:12.506Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    }
	]

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages?start=1&size=1	

affiche :

	[
	    {
	        "author": "Koushik",
	        "created": "2018-08-03T07:44:12.506Z[UTC]",
	        "id": 2,
	        "message": "Hello Jersey!"
	    }
	]