package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

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
	
/*	@GET
	public List<Message> getMessages(	@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			System.out.println("***** INSIDE ***** " + filterBean.getYear());
			return messageService.getAllMessagesForYear(filterBean.getYear());
		} 
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		System.out.println("***** OUTSIDE ***** " + filterBean.getYear());
		return messageService.getAllMessages();
	}
*/	

//	public Message addMessage(Message message) {
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message); // http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/
		System.out.println(uriInfo.getAbsolutePath());
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); // ajout à uriInfo newId - getAbsolutePathBuilder pour construire l'uri
		return Response
				.created(uri)
//				.status(Status.CREATED)
				.entity(newMessage)
				.build(); //  envoi d'une response tjrs terminer par buil()
//		return messageService.addMessage(message);
	}

	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
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

	@Path("/{messageId}/comments") 
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	

}
