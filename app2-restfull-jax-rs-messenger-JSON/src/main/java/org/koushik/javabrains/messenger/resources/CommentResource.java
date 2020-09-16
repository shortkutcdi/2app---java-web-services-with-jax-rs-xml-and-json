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
	public Comment deleteComment(	@PathParam("messageId") long messageId,
								@PathParam("commentId") long commentId) {
		return commentService.removeComment(messageId, commentId);
	}


}
