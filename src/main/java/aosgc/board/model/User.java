package aosgc.board.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private String _id; // El identificador en la base de datos se llama _id
	private String username;
	private String email;
	private String country;
	

	public User() {
		this._id = "";
		this.username = "";
		this.email = "";
		this.country = "";
		
		
	}
	
	public User(String username, String email, String country) {
		this.username = username;
		this.email = email;
		this.country = country;
		
	}
	
	
	
	public String getId() {
		return _id;
	}
	
	public void setId(String id) {
		this._id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
