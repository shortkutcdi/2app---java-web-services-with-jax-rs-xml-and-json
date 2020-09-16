# @Context and @BeanParam

## @Context - récupérer infos du context

````java
@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN) // use @Consumes to specify the expected request body format
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	
	@GET
	@Path("context")
	public String getParamqUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		
		return "Path : " + path + "\nCookies : " + cookies;
	}
}

````

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/injectdemo/context - GET

Affiche : 

	Path : http://localhost:8082/30-restfull-jax-rs-messenger/webapi/injectdemo/context
	Cookies : {name=$version=0; name=hello}
	
## 	MessageFilterBean with @QueryParam properties

````java
package org.koushik.javabrains.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	private @QueryParam("year") int year;
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;

	// getters/setters
````

## màj MessageResource - @BeanParam MessageFilterBean filterBean

````java
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // use @Consumes to specify the expected request body format
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

//	@GET
//	public List<Message> getMessages(	@QueryParam("year") int year,
//										@QueryParam("start") int start,
//										@QueryParam("size") int size) {
//		if (year > 0) {
//			System.out.println("***** INSIDE ***** " + year);
//			return messageService.getAllMessagesForYear(year);
//		} 
//		if (start >= 0 && size > 0) {
//			return messageService.getAllMessagesPaginated(start, size);
//		}
//
//			System.out.println("***** OUTSIDE ***** " + year);
//			return messageService.getAllMessages();
//	}
	
	@GET
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
````

