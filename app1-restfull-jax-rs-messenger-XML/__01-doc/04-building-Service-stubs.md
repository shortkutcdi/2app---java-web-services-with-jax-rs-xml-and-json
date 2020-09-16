# building Service Stubs

## model classe Profile

classe Profile annotée de @XMLRootElement

````java
package org.koushik.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date created;

	public Profile() {
	}

	public Profile(long id, String profileName, String firstName, String lastName) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.created = new Date();
	}
	
	// getters/setters
````

## create DatabaseClass

````java
package org.koushik.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	
}
````

## màj class MessageService

````java
package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Koushik"));
		messages.put(2L, new Message(1, "Hello Jersey!", "Koushik"));
	} 
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
````

## messageResource

````java
package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
}

````

http://localhost:8081/30-restfull-jax-rs-messenger/webapi/messages

affiche :

	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<messages>
	    <message>
	        <author>Koushik</author>
	        <created>2018-07-30T18:26:32.180+02:00</created>
	        <id>1</id>
	        <message>Hello World!</message>
	    </message>
	    <message>
	        <author>Koushik</author>
	        <created>2018-07-30T18:26:32.180+02:00</created>
	        <id>1</id>
	        <message>Hello Jersey!</message>
	    </message>
	</messages>