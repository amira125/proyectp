package aosgc.board.model;

public class Uid {
	private String userId;

	
	public Uid() {
		
		this.userId = "";
	}
	
	
	public Uid(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
