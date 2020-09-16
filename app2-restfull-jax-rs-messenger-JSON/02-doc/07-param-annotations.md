# params annotation

## 

````java
@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN) // use @Consumes to specify the expected request body format
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSession") String header) { // possibilité de passer des valeurs dans l'entête header
		return "Matrix param " + matrixParam + " Header Param " + header;
		
	}

}

````

http://localhost:8082/30-restfull-jax-rs-messenger/webapi/injectdemo/annotations;param=value -- GET

header : authSession  / value : 87tyaios7tf87tsd

Affiche :

Matrix param value Header Param 87tyaios7tf87tsd