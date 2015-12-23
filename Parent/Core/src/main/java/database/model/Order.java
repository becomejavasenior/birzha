package database.model;

import java.util.Date;

public class Order {

	private int id;
	private int wallet1;
	private int amount1;
	private int wallet2;
	private int amount2;
	private Date date_creation;
	private Date date_final;
	
	public Order() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWallet1() {
		return wallet1;
	}

	public void setWallet1(int wallet1) {
		this.wallet1 = wallet1;
	}

	public int getAmount1() {
		return amount1;
	}

	public void setAmount1(int amount1) {
		this.amount1 = amount1;
	}

	public int getWallet2() {
		return wallet2;
	}

	public void setWallet2(int wallet2) {
		this.wallet2 = wallet2;
	}

	public int getAmount2() {
		return amount2;
	}

	public void setAmount2(int amount2) {
		this.amount2 = amount2;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_final() {
		return date_final;
	}

	public void setDate_final(Date date_final) {
		this.date_final = date_final;
	}
	
	
	
}
