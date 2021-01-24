package aosgc.board.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Direccion {

	private String _id;
	private String street;
	private String province;
	private String zipCode;
	private String userId;
	
	public Direccion() {
		this._id= "";
		this.street = "";
		this.province = "";
		this.zipCode = "";
		this.userId = "";
	}
	
	public Direccion(String street, String province,String zipCode) {
		this.street = street;
		this.province = province;
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	

	public String getzipCode() {
		return zipCode;
	}

	public void setzipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}


