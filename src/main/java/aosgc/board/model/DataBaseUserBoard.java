package aosgc.board.model;

import java.util.List;

import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DataBaseUserBoard {

	
	private String DB_URL         = "https://proyecto-51c9.restdb.io/";
	private String COLLECTION_URL = DB_URL + "/rest/usuario";
	private String COLLECTION_URL_COORDENADAS = DB_URL + "/rest/coordenadas";
	private String DOCUMENT_URL   = DB_URL + "/rest/usuario/{id}";
	private String DOCUMENT_URL_COORDENADAS   = DB_URL + "/rest/coordenadas/{latitud}";
	private String APIKEY         = "1216fce23ac29a16eeed847b5c7589d5f9c1e";
	
	// Recupera todos los usuarios con GET y los devuelve en una lista
	public List<User> getUsers() {
		List<User> usuario = Unirest.get(COLLECTION_URL)
				.header("x-apikey", APIKEY)  // Envía la clave de autentificación
				.header("cache-control", "no-cache") // Evita que los proxys cache devuelvan una copia no actualizada de los usuarios
				.asObject(new GenericType<List<User>>(){}) // Transforma la respuesta JSON a una lista de usuarios
				.getBody();
		 System.out.println("se ha ejecutado"+usuario);// Devuelve la lista de usuarios
		return usuario;
	}

	// Crea un nuevo mensaje con POST
	// El servidor devuelve el usuario creado incluyendo el identificador
	// La función devuelve el usuario creado o null si ha habido algún error
	
public User newUser(String username, String email, String country, String street, String province, String zipCode, String date, String name, String description) {
		
		User user = new User(username, email, country);
		
		HttpResponse<User> response = Unirest.post(COLLECTION_URL)
				.header("x-apikey", APIKEY)
				.header("cache-control", "no-cache")
				.header("content-type", "application/json")
				.body(user) // El usuario se transforma a JSON y se envía en el cuerpo de la petición
				.asObject(User.class); // Lee el JSON de la respuesta y lo transforma a una instancia de la clase User
		
		// Se ha modificado el codigo original ya que no devolvia el objeto vacio, por tanto obtenemos
		// el elemento HttpResponse que nos devuelva la APIRest y comprobamos su codigo de operacion.
		// Si este es el correspondiente a CREATED 201 convertimos el elemento a un objeto user, en el
		// caso contrario devolvemos un objeto user vacio.
		if(response.getStatus() == 201) {
			user = response.getBody();
		}else {
			user = null;
		}	
		return user;
	}
	
	
	
	
	
	public User newUserWithAddress(String username, String email, String country, String street, String province, String zipCode, String date, String name, String description) {
		//@TODO: With address
		User user = new User(username, email, country);
		
		HttpResponse<User> response = Unirest.post(COLLECTION_URL)
				.header("x-apikey", APIKEY)
				.header("cache-control", "no-cache")
				.header("content-type", "application/json")
				.body(user) // El usuario se transforma a JSON y se envía en el cuerpo de la petición
				.asObject(User.class); // Lee el JSON de la respuesta y lo transforma a una instancia de la clase User
		
		// Se ha modificado el codigo original ya que no devolvia el objeto vacio, por tanto obtenemos
		// el elemento HttpResponse que nos devuelva la APIRest y comprobamos su codigo de operacion.
		// Si este es el correspondiente a CREATED 201 convertimos el elemento a un objeto user, en el
		// caso contrario devolvemos un objeto user vacio.
		if(response.getStatus() == 201) {
			user = response.getBody();
		}else {
			user = null;
		}	
		return user;
	}
	
	public boolean deleteAll() {
		// Una sola peticion que borre todos los usuarios
		HttpResponse response = Unirest.delete(COLLECTION_URL + "/*")
			.header("x-apikey", APIKEY)
			.queryString("q","{}")
			.asEmpty();
		
		
		boolean estado;
		
		if(response.getStatus() == 200) {
			estado = true;
		}else {
			estado = false;
		}
		
		return estado;
			
	}

	public User getUser(String id) {

		User user = Unirest.get(DOCUMENT_URL)
				.header("x-apikey", APIKEY)
				.routeParam("id", id) // Sustituye el parametro id de la URL por el id del parametro
				.asObject(User.class) // recoge el objeto tipo User
				.getBody();	// Coge el objeto que recibimo o un null si no llega nada
		
		return user;
	}

	public User updateUserWithAdress(String id, String username, String email, String country, String street, String province, String zipCode) {
		// Todo with address:
		//
		// En primer lugar declaramos una variable tipo user para almacenar los datos que recibimos
		// por parametro
		User user = new User(username, email, country);
		
		// Inicializamos la variable donde almacenaremos el elemento HttpResponse que nos devuelva la 
		// petición PUT o PATCH
		HttpResponse<User> response;
		
		// Comprobamos que los parametros no vengan vacios ya que en el caso de que alguno lo este usaremos
		// peticiones PATCH para editar solo los datos que tenemos. En el caso de tenerlos todos los editaremos
		// con peticiones PUT
		if((username == null) || (email == null) || (country == null) || (street == null) || (province == null) || (zipCode == null)) {
			response = Unirest.patch(DOCUMENT_URL)
					.header("x-apikey", APIKEY)
					.header("cache-control", "no-cache")
					.header("content-type", "application/json")
					.routeParam("id", id)
					.body(user)
					.asObject(User.class);
		}else {
			response = Unirest.put(DOCUMENT_URL)
					.header("x-apikey", APIKEY)
					.header("cache-control", "no-cache")
					.header("content-type", "application/json")
					.routeParam("id", id)
					.body(user)
					.asObject(User.class);
		}
		
		// Emitimos un codigo de respuesta en funcion al codigo que recibamos de la peticion que 
		// hallamos enviado
		if(response.getStatus() == 200) {
			user = response.getBody();
		}else {
			user = null;
		}	
		return user;
		
		
	}
	public User updateUser(String id, String username, String email, String country) {
		// En primer lugar declaramos una variable tipo user para almacenar los datos que recibimos
		// por parametro
		User user = new User(username, email, country);
		
		// Inicializamos la variable donde almacenaremos el elemento HttpResponse que nos devuelva la 
		// petición PUT o PATCH
		HttpResponse<User> response;
		
		// Comprobamos que los parametros no vengan vacios ya que en el caso de que alguno lo este usaremos
		// peticiones PATCH para editar solo los datos que tenemos. En el caso de tenerlos todos los editaremos
		// con peticiones PUT
		if((username == null) || (email == null) || (country == null)) {
			response = Unirest.patch(DOCUMENT_URL)
					.header("x-apikey", APIKEY)
					.header("cache-control", "no-cache")
					.header("content-type", "application/json")
					.routeParam("id", id)
					.body(user)
					.asObject(User.class);
		}else {
			response = Unirest.put(DOCUMENT_URL)
					.header("x-apikey", APIKEY)
					.header("cache-control", "no-cache")
					.header("content-type", "application/json")
					.routeParam("id", id)
					.body(user)
					.asObject(User.class);
		}
		
		// Emitimos un codigo de respuesta en funcion al codigo que recibamos de la peticion que 
		// hallamos enviado
		if(response.getStatus() == 200) {
			user = response.getBody();
		}else {
			user = null;
		}	
		return user;
	}

	public boolean deleteUser(String id) {
		// Hacemos una peticion para recibir un elemento HttpResponse del cual consultaremos su codigo
		// de operacion para ver si la operacion se ha llevado a cabo de forma correcta
		HttpResponse response = Unirest.delete(DOCUMENT_URL)
				.header("x-apikey", APIKEY)
				.routeParam("id", id)
				.asEmpty();
		
		// Declaramos la variable booleana que recoge el estado de la operacion de borrado
		boolean estado;
		
		if(response.getStatus() == 200) {
			estado = true;
		}else {
			estado = false;
		}
		return estado;
	}
	
	// Recupera todos los usuarios con GET y los devuelve en una lista
		public List<User> existsAlready(String username) {
			// Preparamos la cadena de la query
			
			String query = "{\"username\": \"" + username + "\"}";
			
			List<User> users = Unirest.get(COLLECTION_URL)
					.header("x-apikey", APIKEY)  // Envía la clave de autentificación
					.header("cache-control", "no-cache") // Evita que los proxys cache devuelvan una copia no actualizada de los usuarios
					.queryString("q",query)
					.asObject(new GenericType<List<User>>(){}) // Transforma la respuesta JSON a una lista de usuarios
					.getBody(); // Devuelve la lista de usuarios
			return users;
		}
		
		// nos da todos los usuarios que estan en este rango los devuelve en una lista
				public List<Uid> getByCoordenadas(String userId) {
					// Preparamos la cadena de la query
					
					//String query = "{\"username\": \"" + username + "\"}";
					
					// 
					
					List<Uid> latitud = Unirest.get(COLLECTION_URL_COORDENADAS)
					//List<Coordenadas> longitud = Unirest.get(COLLECTION_URL)
							.header("x-apikey", APIKEY)  // Envía la clave de autentificación
							.header("cache-control", "no-cache") // Evita que los proxys cache devuelvan una copia no actualizada de los usuarios
							.queryString("q","    \"{\\\"latitud\\\": \"\"{\\\"$bt\\\" :\\\"\" \"\\\"[ + latitud + latitud + ]\"\"\\\"}\"       ")
							.asObject(new GenericType<List<Uid>>(){}) // Transforma la respuesta JSON a una lista de usuarios
							.getBody(); // Devuelve la lista de usuarios
					return latitud;
				}
		
		
}
