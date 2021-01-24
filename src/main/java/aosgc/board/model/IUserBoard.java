package aosgc.board.model;

import java.util.Collection;


public interface IUserBoard {
	
	// GESTOR DE USUARIOS
	public User newUser(String username, String email, String country, String street, String province, String zipCode, String date, String name, String description);

	public User getUser(String id);

	public User updateUser(String id, String username, String email, String country, String street, String province, String zipCode, String date, String name, String description);

	public User deleteUser(String id);

	public Collection<User> getUsers();
	
	public Collection<Coordenadas> getByCoordenadas();
	
	public void deleteAllUsers();
}
