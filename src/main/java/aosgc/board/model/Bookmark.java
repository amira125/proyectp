package aosgc.board.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bookmark {
	private String _id;
	private String name;
	private String description;
	private String date;
	private String userId;
	private String coordenadasId;
	
	
	
	public Bookmark() {
	
		this._id = "";
		this.name = "";
		this.description = "";
		this.date = "";
		this.userId = "";
		this.coordenadasId = "";
		
		
}

	public Bookmark(String name, String description, String date, String userId, String coordenadasId) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
		this.userId = userId;
		this.coordenadasId = coordenadasId;
	
		{
			

		}}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCoordenadasId() {
		return coordenadasId;
	}

	public void setCoordenadasId(String coordenadasId) {
		this.coordenadasId = coordenadasId;
	}

	
	}
	
	




