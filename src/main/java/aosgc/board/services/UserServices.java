package aosgc.board.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import aosgc.board.model.DataBaseUserBoard;
import aosgc.board.model.Uid;
import aosgc.board.model.User;

@Path("/usuario")

@Produces(MediaType.APPLICATION_JSON)
public class UserServices {

	private static DataBaseUserBoard board = new DataBaseUserBoard();


	@Context
	UriInfo uriInfo;
	
	@GET

	public Response get() {
		/*
		 * Obtenemos la lista completa de usuarios
		 */
		List<User> usuario = board.getUsers();
		System.out.println("lista user");

		return Response.ok(usuario).build();
	}

	@PUT

	public Response put() {

		// No es posible hacer un put sin un ID

		return Response.status(Status.NOT_FOUND).build();
	}

	@POST

	public Response post(
	
			@FormParam("username") String username,

			@FormParam("email") String email,
	
			@FormParam("country") String country,
		
			@FormParam("street") String street,
		
			@FormParam("province") String province,
	
			@FormParam("zipCode") String zipCode,
	
			@FormParam("name") String name,

			@FormParam("date") String date,
		
			@FormParam("description") String description)
	
	
	{

		// Comprobamos si el usuario existe 
		List<User> users = board.existsAlready(username);

		// Inicializamos la variable donde irá el usuario creado

		User user = null;

		// Inicializamos la variable que contendra el código de respuesta
		Response respuesta;

			//if (users.isEmpty()) {
			// Pasamos los datos al gestor para crear un nuevo usuario
			user = board.newUser(username, email, country, street, province, zipCode, date, name, description);
			
			if (user != null) {

				// Si el usuario no es nulo devolvemos el codigo 200 OK
				UriBuilder ub = uriInfo.getAbsolutePathBuilder();
				URI location = ub.path(user.getId()).build();
				respuesta = Response.created(location).build();
				return respuesta;
			} else {

				// Si el usuario viene vacio significa que hay algun error y devolvemos 404 NOT
				// FOUND
				respuesta = Response.status(Status.NOT_FOUND).build();
				
				return respuesta;
			}
			
		
	}
			
			

	@DELETE
	public Response delete() {

		// Declaramos la variable booleana que comprueba el estado de la operacion

		boolean estado;

		// Eliminamos todos los usuarios de la pizarra

		estado = board.deleteAll();

		// Inicializamos la variable que contendra el código de respuesta

		Response respuesta;

		// Comprobamos que el codigo que nos devuelve es correcto

		if (estado == true) {
			// Elimianmos todos los mensajes de la pizarra
		//	estado = board_user.deleteAll();

			//if (estado == true) {
				respuesta = Response.ok().build();
			} else {
				respuesta = Response.status(Status.NOT_FOUND).build();
			}
		

		return respuesta;
	}

	@GET
	@Path("{id}")

	public Response getWithId(
		
			@PathParam("id") String id) {

		// Mandamos la peticion GET al servidor con el identificador recibido
		User user = board.getUser(id);

		// Inicializamos la variable que contendra el código de respuesta

		Response respuesta;

		if (user != null) {
			// Si el usuario no es nulo devolvemos el codigo 200 OK
			respuesta = Response.ok(user).build();
		} else {
			// Si el usuario no existe devuelve el codigo 404 NOT FOUND.
			respuesta = Response.status(Status.NOT_FOUND).build();
		}

		return respuesta;
	}

	@PUT
	@Path("{id}")

	public Response putWithId(

			@PathParam("id") String id,
		
			@FormParam("username") String username,
			
			@FormParam("email") String email,
		
			@FormParam("country") String country)
		
	{

		// En primer lugar comprobamos si ya existe un usuario con ese nombre

		List<User> users = board.existsAlready(username);

		// Inicializamos la variable donde irá el usuario creado

		User user = null;

		// Inicializamos la variable que contendra el código de respuesta

		Response respuesta;

		// Comprobamos si el usuario encontrado es el que vamos a editar

		if (users.isEmpty()) {
			user = board.updateUser(id, username, email, country);

			return Response.status(Status.OK).build();
			

			} else {
				// Si el usuario no existe devuelve el codigo 404 NOT FOUND.
				respuesta = Response.status(Status.NOT_FOUND).build();
				return respuesta;
			}

		
	
		
	}

	
	//@TODO:WITH ADDRESS
	
//
//	@PUT
//@Path("{username}")
//////	
//	public Response putWithIdWithAdress(

//		@PathParam("username") String username,

//		@FormParam("id") String id,

//		@FormParam("email") String email,

//		@FormParam("country") String country,
//		@FormParam("street") String street,
//		@FormParam("province") String province,
//		@FormParam("zipCode") String zipCode)
//////		
//{
//////
//////		// En primer lugar comprobamos si ya existe un usuario con ese nombre
//////
//	List<User> users = board.existsAlready(username);
//
//////		// Inicializamos la variable donde irá el usuario creado
//////
//User user = null;
//////
//////		// Inicializamos la variable que contendra el código de respuesta
//////
////	
//////
//////		// Comprobamos si el usuario encontrado es el que vamos a editar
//////
//	if (users.isEmpty()) {
//	user = board.updateUserWithAdress(id, username, email, country, street, province, zipCode);
//		if (user != null) {
//////				// Si el usuario no es nulo devolvemos el codigo 200 OK
//				 Response.ok(user).build();
//			return Response.ok(user).build();
//////
//		} else {
//////				// Si el usuario no existe devuelve el codigo 404 NOT FOUND.
//		Response.status(Status.NOT_FOUND).build();
//		return Response.status(Status.NOT_FOUND).build();
//	}	}
//	return null;
//	}
//	
	
	
//	
//	//
//	
	
	
	@DELETE
	@Path("{id}")
	public Response deleteWithId(
	
			@PathParam("id") String id) {

		// Declaramos la variable booleana que comprueba el estado de la operacion

		boolean estado;

		estado = board.deleteUser(id);

		// Inicializamos la variable que contendra el código de respuesta

		Response respuesta;

	
			// Si se ha podido borrar el usuario, quiere decir que este existe por tanto se
			// procede a
			// eliminar todos los mensajes relacionados con el.

			if (estado == true) {
				respuesta = Response.ok().build();
			} else {
				respuesta = Response.status(Status.NOT_FOUND).build();
			}
		

		return respuesta;
	}
	
	

	@GET
	@Path("/coordenadas")
			public Response getByCoordenadas(@PathParam("userId") String userId) {

		// * Obtenemos la lista completa de usuarios
		// */
		List<Uid> latitud = board.getByCoordenadas(userId);
		System.out.println("lista de usuarios que estan en estas coordenadas");

		return Response.ok(latitud).build();
	}
	
	
}
