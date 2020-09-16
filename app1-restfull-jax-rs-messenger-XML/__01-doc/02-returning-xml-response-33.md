# returning XML response

# step 1- create the necessary model and service classes

step 2- insure the model classes have a no-argument constructor

Model class Message

````java
package org.koushik.javabrains.messenger.model;

import java.util.Date;

public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;
	
	public Message() {
	}

	public Message(long id, String message, String author) {
		super();
		this.created = new Date();
		this.id = id;
		this.message = message;
		this.author = author;
	}	

	// getters/setters
````

Service class MessageService 

````java
package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	
	public List<Message> getAllMessages() {
		Message m1 = new Message(1L, "Hello World!", "Koushik");
		Message m2 = new Message(2L, "Hello Jersey!", "Koushik");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		
		return list;
	}
}

````

## step3 call the service from MessageResource and return response

step4 update the @Produces annotation to XML format



````java
package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("resources")				//<<<<<<<<<<<
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML) //<<<<<<<<<<<<<<<
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}

}
````

## step5 annotate the model class Message with @XMLRootElement

@XMLRootElement - Maps a class or an enum type to an XML element

````java
package org.koushik.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement			//<<<<<<<<<<<<<<<
public class Message {
````

http://localhost:8081/30-restfull-jax-rs-messenger/webapi/messages

affiche :

	<?xml version="1.0" encoding="UTF-8" standalone="true"?>
	
	<messages>
		<message>
			<author>Koushik</author>
			<created>2018-07-30T17:24:13.381+02:00</created>
			<id>1</id>
			<message>Hello World!</message>
		</message>
		
		<message>
			<author>Koushik</author>
			<created>2018-07-30T17:24:13.381+02:00</created>
			<id>2</id>
			<message>Hello Jersey!</message>
		</message>
	</messages>
	
	