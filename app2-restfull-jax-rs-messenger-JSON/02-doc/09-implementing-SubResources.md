# Implementing SubResources

 	/messages/{messageId}/comments/{commentId}
 	
## màj MessageResource - getCommentResource

Quand "/{messageId}/comments" est appelé => instance de CommentResource -new CommentResource()-


````java
package org.koushik.javabrains.messenger.resources;



@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	// methodes...

	@Path("/{messageId}/comments")						//<<<<<<<<<<<<<< 
	public CommentResource getCommentResource() {		//<<<<<<<<<<<<<<
		return new CommentResource();					//<<<<<<<<<<<<<<
	} 	
````

## create model class Comment

````java
package org.koushik.javabrains.messenger.model;

import java.util.Date;

public class Comment {

	private long id;
	private String message;
	private Date created;
	private String author;
	
	public Comment() {
	}
	
	public Comment(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	
	// getters/setters
````

## Message - add comments property - getter/setter

````java
@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long, Comment> comments = new HashMap<>();  //<<<<<<<<<

	// getters/setters
	
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	
````


## create CommentService

````java
public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	

	public List<Comment> getAllComments(Long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(Long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
}
````

 	
## create class CommentResource -- test

````java
package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

//@Produces(MediaType.TEXT_PLAIN)
@Path("/")
public class CommentResource {
	
	@GET											// correspond à : /webapi/messages/10/comments
	public String test() {
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")											// correspond à : /webapi/messages/10/comments/15
	public String test2(@PathParam("messageId") long messageId,
						@PathParam("commentId") long commentId) {
		return "Method to return comment ID: " + commentId + " for message: " + messageId;
	}
	

}
````

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/1/comments - GET

affiche :

	new sub resource


http://localhost:8082/30-restfull-jax-rs-messenger/webapi/messages/1/comments/15 - GET

affiche :

	Method to return comment ID: 15 for message: 1
	
## màj CommentResource

````java
package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.service.CommentService;

//@Produces(MediaType.TEXT_PLAIN)
@Path("/")
@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(	@PathParam("messageId") long messageId,
									@PathParam("commentId") long commentId, 
									Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(	@PathParam("messageId") long messageId,
								@PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(	@PathParam("messageId") long messageId,
								@PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}


}
````