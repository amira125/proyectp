package aosgc.board.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coordenadas {
	private String _id;
	private Double latitud;
	private Double longitud;
	private String userId;
	
	public Coordenadas() {

		this.latitud = latitud;
		this.longitud = longitud;
		this._id="";
		this.userId = "";
	}

	public Coordenadas(Double latitud, Double longitud) {
		
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}


	
	
}
