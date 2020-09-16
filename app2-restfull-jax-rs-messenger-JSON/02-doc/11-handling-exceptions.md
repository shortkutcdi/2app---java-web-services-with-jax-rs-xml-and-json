# handling exceptions

si la requête ne renvoie pas de données (exception)

ex : http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/10 - GET

le message d'id 10 n'existe pas

## créer class DataNotFoundException

````java
package org.koushik.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -6328286661536343936L;

	public DataNotFoundException(String message) {
		super(message);
	}
	
}

````

## màj class MessageService - getMessage throw exception

````java
public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Koushik"));
		messages.put(2L, new Message(2, "Hello Jersey!", "Koushik"));
	} 
	
	// methosd...
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}
````