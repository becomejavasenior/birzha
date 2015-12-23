package database.model;

public class Wallet {
	
	private int id;
	private int currId;
	private int userId;
	
	public Wallet() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurrId() {
		return currId;
	}
	public void setCurrId(int currId) {
		this.currId = currId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
}
